package com.bridgelabz.adminmicroservice.model;

import com.bridgelabz.adminmicroservice.dto.AdminDTO;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Purpose:Creating model for Admin
 * @author Manoj
 * @Param all the required variables to save in repository
 * Version 1.0
 */
@Entity
@Data
@Table(name ="admin_details_micro")
public class AdminModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;
    private String firstName;
    private String lastName;
    private Long mobileNumber;
    private String emailId;
    private String password;
    private String profilePath;
    private LocalDateTime creatorStamp;
    private LocalDateTime updatedStamp;

    public AdminModel(AdminDTO adminDTO) {
        this.firstName = adminDTO.getFirstName();
        this.lastName = adminDTO.getLastName();
        this.mobileNumber = adminDTO.getMobileNumber();
        this.emailId = adminDTO.getEmailId();
        this.password = adminDTO.getPassword();
    }

    public AdminModel() {
    }
}
