package com.bridgelabz.candidatemicroservice.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.candidatemicroservice.dto.BankDTO;
import com.bridgelabz.candidatemicroservice.model.BankModel;
import com.bridgelabz.candidatemicroservice.service.IBankService;
import com.bridgelabz.candidatemicroservice.util.ResponseClass;

/**
 * Purpose:Creating apis for Bank Controller
 * @author Manoj
 * @Param Http METHODS
 * Version 1.0
 */
@RestController
@RequestMapping("/bankDetailsApis")
public class BankController {

    @Autowired
    IBankService iBankService;

    /**
     * Purpose:Creating method to add bank
     * @author Manoj
     * @Param bankDto,token
     */
    @PostMapping("/addBankDetails")
    public ResponseEntity<ResponseClass> addBank ( @RequestHeader String token,@RequestBody @Valid BankDTO bankDTO){
    	ResponseClass responseClass =iBankService.addBankDetails(token,bankDTO);
    	return new ResponseEntity<>(responseClass,HttpStatus.OK);
    }

    /**
     * Purpose:Creating method to Update Bank
     * @author Manoj
     * @Param BankDto ,id ,token
     */
    @PutMapping("/updateBankDetails/{id}")
    public ResponseEntity<ResponseClass> updateBank (@RequestHeader String token,@PathVariable Long id , @RequestBody @Valid BankDTO bankDTO){
    	ResponseClass responseClass =iBankService.editBank(token,id,bankDTO);
    	return new ResponseEntity<>(responseClass,HttpStatus.OK);
    }

    /**
     * Purpose:Creating method to get List of Bank
     * @author Manoj
     * @Param  token
     */
    @GetMapping("/getBankList")
    public ResponseEntity<List<?>> getList(@RequestHeader String token){
    	List<BankModel> responseClass =iBankService.viewList(token);
    	return new ResponseEntity<>(responseClass,HttpStatus.OK);
    }

    /**
     * Purpose:Creating method to Delete Bank
     * @author Manoj
     * @Param  token,id
     */
    @DeleteMapping("/deleteBank/{id}")
    public ResponseEntity<ResponseClass> deleteBank (@RequestHeader String token,@PathVariable Long id){
    	ResponseClass responseClass =iBankService.removeBank(token,id);
    	return new ResponseEntity<>(responseClass,HttpStatus.OK);
    }
}
