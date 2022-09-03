package com.bridgelabz.adminmicroservice.service;


import com.bridgelabz.adminmicroservice.dto.AdminDTO;
import com.bridgelabz.adminmicroservice.exception.CustomExceptions;
import com.bridgelabz.adminmicroservice.model.AdminModel;
import com.bridgelabz.adminmicroservice.repository.IAdminRepository;
import com.bridgelabz.adminmicroservice.util.ResponseClass;
import com.bridgelabz.adminmicroservice.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Purpose:Creating Service for Admin
 * @author Manoj
 * @Param business logic is present here
 * Version 1.0
 */
@Service
public class AdminService implements IAdminService{

    @Autowired
    IAdminRepository iAdminRepository;

    @Autowired
    TokenUtil tokenUtil;

    @Autowired
    MailService mailService;

    /**
     * Purpose:Creating method to add admin
     *
     * @author Manoj
     * @Param AdminDto
     */
    @Override
    public ResponseClass createAdmin(AdminDTO adminDTO) {
        AdminModel adminModel = new AdminModel(adminDTO);
        adminModel.setCreatorStamp(LocalDateTime.now());
        iAdminRepository.save(adminModel);
        String body = "Admin Registration Is Successful with id :-"+adminModel.getId()+"\n"+adminModel;
        String subject = "Admin Registration Success";
        mailService.send(adminDTO.getEmailId(), body,subject);
        return new ResponseClass(200,"success",adminModel);
    }

    /**
     * Purpose:Creating method to Update Admin
     *
     * @author Manoj
     * @Param AdminDto ,id ,token
     */
    @Override
    public ResponseClass editAdmin(Long id, AdminDTO adminDTO, String token) {
        Long adminId = tokenUtil.decodeToken(token);
        Optional<AdminModel> isAdminIdPresent = iAdminRepository.findById(adminId);
        if (isAdminIdPresent.isPresent()) {
            Optional<AdminModel> isAdminPresent = iAdminRepository.findById(id);
            if (isAdminPresent.isPresent()) {
                isAdminPresent.get().setFirstName(adminDTO.getFirstName());
                isAdminPresent.get().setLastName(adminDTO.getLastName());
                isAdminPresent.get().setMobileNumber(adminDTO.getMobileNumber());
                isAdminPresent.get().setEmailId(adminDTO.getEmailId());
                isAdminPresent.get().setPassword(adminDTO.getPassword());
                isAdminPresent.get().setUpdatedStamp(LocalDateTime.now());
                iAdminRepository.save(isAdminPresent.get());
                String body = "Admin Update Is Successful with id :-"+isAdminPresent.get().getId()+"\n"+isAdminPresent.get();
                String subject = "Admin update Success";
                mailService.send(adminDTO.getEmailId(), body,subject);
                return new ResponseClass(200,"success",isAdminPresent.get());
            }
        }
        throw new CustomExceptions(400,"Admin Not Found");
    }

    /**
     * Purpose:Creating method to get List of Admin
     * @author Manoj
     * @Param  token
     */
    @Override
    public List<AdminModel> viewList(String token) {
        Long adminId = tokenUtil.decodeToken(token);
        Optional<AdminModel> isAdmin = iAdminRepository.findById(adminId);
        if (isAdmin.isPresent()) {
            List<AdminModel> isList = iAdminRepository.findAll();
            if (isList.size() > 0) {
                return isList;
            }
        }
        throw new CustomExceptions(400,"No Data Found");
    }

    /**
     * Purpose:Creating method to Delete Admin
     *
     * @author Manoj
     * @Param token, id
     */
    @Override
    public ResponseClass removeAdmin(Long id, String token) {
        Long adminId = tokenUtil.decodeToken(token);
        Optional<AdminModel> isAdminIdPresent = iAdminRepository.findById(adminId);
        if (isAdminIdPresent.isPresent()) {
            Optional<AdminModel> isAdminPresent = iAdminRepository.findById(id);
            if (isAdminPresent.isPresent()) {
                iAdminRepository.delete(isAdminPresent.get());
                String body = "Admin Deletion Is Successful with id :-"+isAdminPresent.get().getId()+"\n"+isAdminPresent.get();
                String subject = "Admin Deletion Success";
                mailService.send(isAdminPresent.get().getEmailId(), body,subject);
                return new ResponseClass(200,"success",isAdminPresent.get());
            }
        }
        throw new CustomExceptions(400,"No Admin Found");
    }

    /**
     * Purpose:Creating method Login to  Admin using unique id called token
     * @author Manoj
     * @Param  email,password
     */
    @Override
    public ResponseClass loginToken(String emailId, String password) {
        Optional<AdminModel> isEmailPresent = iAdminRepository.findByEmailId(emailId);
        if (isEmailPresent.isPresent()){
            if (isEmailPresent.get().getPassword().equals(password)) {
                String token = tokenUtil.createToken(isEmailPresent.get().getId());
                return new ResponseClass(200,"Login Success",token);
            }else {
                throw new CustomExceptions(400,"Password is Wrong");
            }
        }
        throw new CustomExceptions(400,"No Details Matched");
    }

    /**
     * Purpose:Creating method Reset password of Admin
     * @author Manoj
     * @Param  email
     */
    @Override
    public ResponseClass resetAdminPassword(String email) {
        Optional<AdminModel> isEmailPresent = iAdminRepository.findByEmailId(email);
        if (isEmailPresent.isPresent()){
            String token = tokenUtil.createToken(isEmailPresent.get().getId());
            String url = System.getenv("url")+"\n"+token;
            String subject = "Admin reset Success";
            mailService.send(isEmailPresent.get().getEmailId(),url,subject);
        }
        throw new CustomExceptions(400,"Invalid Email");
    }

    /**
     * Purpose:Creating method change password of Admin
     *
     * @author Manoj
     * @Param token, newPassword
     */
    @Override
    public ResponseClass changePassword(String token, String newPassword) {
        Long adminId = tokenUtil.decodeToken(token);
        Optional<AdminModel> isAdminIdPresent = iAdminRepository.findById(adminId);
        if (isAdminIdPresent.isPresent()){
            isAdminIdPresent.get().setPassword(newPassword);
            iAdminRepository.save(isAdminIdPresent.get());
            String body = "Admin Change Password Is Successful with id :-"+isAdminIdPresent.get().getId()+"\n"+isAdminIdPresent.get();
            String subject = "Admin Change Password Success";
            mailService.send(isAdminIdPresent.get().getEmailId(), body,subject);
            return new ResponseClass(200,"success",isAdminIdPresent.get());
        }
        throw new CustomExceptions(400,"Invalid Token");
    }

    /**
     * Purpose:Creating method to add profile path to Admin
     *
     * @author Manoj
     * @Param token, profilePath
     */
    @Override
    public ResponseClass setProfilePath(String token, String profilePath) {
        Long adminId = tokenUtil.decodeToken(token);
        Optional<AdminModel> isAdminPresent = iAdminRepository.findById(adminId);
        if (isAdminPresent.isPresent()){
            isAdminPresent.get().setProfilePath(profilePath);
            iAdminRepository.save(isAdminPresent.get());
            return new ResponseClass(200,"success",isAdminPresent.get());
        }
        throw new CustomExceptions(400,"Invalid Token");
    }

    @Override
    public boolean validateToken(String token) {
        Long userId = tokenUtil.decodeToken(token);
        Optional<AdminModel> isUserPresent = iAdminRepository.findById(userId);
        if (isUserPresent.isPresent()){
            return true;
        }
        return false;
    }
}
