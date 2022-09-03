package com.bridgelabz.adminmicroservice.controller;

import com.bridgelabz.adminmicroservice.dto.AdminDTO;
import com.bridgelabz.adminmicroservice.model.AdminModel;
import com.bridgelabz.adminmicroservice.service.IAdminService;
import com.bridgelabz.adminmicroservice.util.ResponseClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Purpose:Creating apis for Admin Controller
 * @author Manoj
 * @Param Http METHODS
 * Version 1.0
 */
@RestController
@RequestMapping("/adminAPIS")
public class AdminController {
    @Autowired
    IAdminService iAdminService;

    /**
     * Purpose:Creating method to add admin
     * @author Manoj
     * @Param AdminDto
     */
    @PostMapping("/createAdmin")
    public ResponseEntity<ResponseClass> createModel (@RequestBody @Valid AdminDTO adminDTO){
        ResponseClass responseClass = iAdminService.createAdmin(adminDTO);
        return new ResponseEntity<>(responseClass, HttpStatus.OK);
    }

    /**
     * Purpose:Creating method to Update Admin
     * @author Manoj
     * @Param AdminDto ,id ,token
     */
    @PutMapping("/updateAdmin/{id}")
    public ResponseEntity<ResponseClass> updateAdmin (@RequestHeader String token,@PathVariable Long id,@RequestBody @Valid AdminDTO adminDTO){
        ResponseClass responseClass = iAdminService.editAdmin(id,adminDTO,token);
        return new ResponseEntity<>(responseClass, HttpStatus.OK);
    }

    /**
     * Purpose:Creating method to get List of Admin
     * @author Manoj
     * @Param  token
     */
    @GetMapping("/adminList")
    public ResponseEntity<List<?>> getList (@RequestHeader String token){
        List<AdminModel> responseClass = iAdminService.viewList(token);
        return new ResponseEntity<>(responseClass, HttpStatus.OK);
    }

    /**
     * Purpose:Creating method to Delete Admin
     * @author Manoj
     * @Param  token,id
     */
    @DeleteMapping("/deleteAdmin/{id}")
    public ResponseEntity<ResponseClass> deleteAdmin(@RequestHeader String token,@PathVariable Long id){
        ResponseClass responseClass = iAdminService.removeAdmin(id,token);
        return new ResponseEntity<>(responseClass, HttpStatus.OK);
    }

    /**
     * Purpose:Creating method Login to  Admin using unique id called token
     * @author Manoj
     * @Param  email,password
     */
    @PostMapping("/loginToken")
    public ResponseEntity<ResponseClass> login(@RequestParam String emailId ,@RequestParam String password){
        ResponseClass responseClass = iAdminService.loginToken(emailId,password);
        return new ResponseEntity<>(responseClass, HttpStatus.OK);
    }

    /**
     * Purpose:Creating method Reset password of Admin
     * @author Manoj
     * @Param  email
     */
    @PostMapping("/resetAdminPassword")
    public ResponseEntity<ResponseClass> resetPassword(@RequestParam String email){
        ResponseClass responseClass = iAdminService.resetAdminPassword(email);
        return new ResponseEntity<>(responseClass, HttpStatus.OK);
    }

    /**
     * Purpose:Creating method change password of Admin
     * @author Manoj
     * @Param  token,newPassword
     */
    @PutMapping("/changeAdminPassword")
    public ResponseEntity<ResponseClass> changePassword(@RequestHeader String token , @RequestParam String newPassword){
        ResponseClass responseClass = iAdminService.changePassword(token,newPassword);
        return new ResponseEntity<>(responseClass, HttpStatus.OK);
    }

    /**
     * Purpose:Creating method to add profile path to Admin
     * @author Manoj
     * @Param  token,profilePath
     */
    @PutMapping("/addProfilePath")
    public ResponseEntity<ResponseClass> addProfilePath(@RequestHeader String token, @RequestParam String profilePath){
        ResponseClass responseClass = iAdminService.setProfilePath(token,profilePath);
        return new ResponseEntity<>(responseClass, HttpStatus.OK);
    }

    /**
     * Purpose:Creating method to validate user using token
     * @author Manoj
     * @Param  token
     */
    @GetMapping("/validate/{token}")
    public boolean validate(@PathVariable String token){
        return iAdminService.validateToken(token);
    }
}
