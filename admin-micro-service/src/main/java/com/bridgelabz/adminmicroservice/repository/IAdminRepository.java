package com.bridgelabz.adminmicroservice.repository;

import com.bridgelabz.adminmicroservice.model.AdminModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Purpose:Creating repository for Admin
 * @author Manoj
 * @Param  to save in database
 * Version 1.0
 */
public interface IAdminRepository extends JpaRepository<AdminModel,Long> {
    Optional<AdminModel> findByEmailId(String emailId);
}
