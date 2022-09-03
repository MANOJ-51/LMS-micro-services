package com.bridgelabz.techstackmicroservice.controller;

import com.bridgelabz.techstackmicroservice.dto.TechStackDTO;
import com.bridgelabz.techstackmicroservice.model.TechStackModel;
import com.bridgelabz.techstackmicroservice.service.ITechStackService;
import com.bridgelabz.techstackmicroservice.util.ResponseClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Purpose:Creating apis for techStack Controller
 * @author Manoj
 * @Param Http METHODS
 * Version 1.0
 */
@RestController
@RequestMapping("/techStackControllerApis")
public class TechStackController {

    @Autowired
    ITechStackService iTechStackService;

    /**
     * Purpose:Creating method to add tech
     * @author Manoj
     * @Param techStackDto,token
     */
    @PostMapping("/createTechStack")
    public ResponseEntity<ResponseClass> createTechStack (@RequestHeader String token, @RequestBody @Valid TechStackDTO techStackDTO){
        ResponseClass responseClass = iTechStackService.addTech(token,techStackDTO);
        return new ResponseEntity<>(responseClass, HttpStatus.OK);
    }

    /**
     * Purpose:Creating method to Update tech
     * @author Manoj
     * @Param techStackDto ,id ,token
     */
    @PutMapping("/updateTechStack/{id}")
    public ResponseEntity<ResponseClass> updateTech (@RequestHeader String token,@PathVariable Long id ,
                                      @RequestBody @Valid TechStackDTO techStackDTO){
        ResponseClass responseClass =iTechStackService.editTech(token,id,techStackDTO);
        return new ResponseEntity<>(responseClass, HttpStatus.OK);
    }

    /**
     * Purpose:Creating method to get List of techStack
     * @author Manoj
     * @Param  token
     */
    @GetMapping("/getTechList")
    public ResponseEntity<List<?>> getList (@RequestHeader String token){
        List<TechStackModel>responseClass =iTechStackService.viewList (token);
        return new ResponseEntity<>(responseClass, HttpStatus.OK);
    }

    /**
     * Purpose:Creating method to Delete techStack
     * @author Manoj
     * @Param  token,id
     */
    @DeleteMapping("/deleteTech/{id}")
    public ResponseEntity<ResponseClass> deleteTech (@RequestHeader String token,@PathVariable Long id){
        ResponseClass responseClass =iTechStackService.removeTech(token,id);
        return new ResponseEntity<>(responseClass, HttpStatus.OK);
    }

    /**
     * Purpose:Creating method to add profile path to techStack
     * @author Manoj
     * @Param  token,profilePath,techID
     */
    @PostMapping("addProfilePath")
    public ResponseEntity<ResponseClass> setPath (@RequestHeader String token,@RequestParam Long techId , @RequestParam String path){
        ResponseClass responseClass =iTechStackService.addPath(token,techId,path);
        return new ResponseEntity<>(responseClass, HttpStatus.OK);
    }

}