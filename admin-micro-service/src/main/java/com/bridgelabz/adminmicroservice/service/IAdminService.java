package com.bridgelabz.adminmicroservice.service;

import com.bridgelabz.adminmicroservice.dto.AdminDTO;
import com.bridgelabz.adminmicroservice.model.AdminModel;
import com.bridgelabz.adminmicroservice.util.ResponseClass;

import java.util.List;

/**
 * Purpose:Creating Interface for Admin service
 * @author Manoj
 * @Param  all service Methods
 * Version 1.0
 */
public interface IAdminService {
    ResponseClass editAdmin(Long id, AdminDTO adminDTO, String token);
    ResponseClass createAdmin(AdminDTO adminDTO);

    List<AdminModel> viewList(String token);

    ResponseClass removeAdmin(Long id, String token);

    ResponseClass loginToken(String emailId, String password);

    ResponseClass resetAdminPassword(String email);

    ResponseClass changePassword(String token, String newPassword);

    ResponseClass setProfilePath(String token, String profilePath);

    boolean validateToken(String token);
}
