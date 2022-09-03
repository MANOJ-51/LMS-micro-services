package com.bridgelabz.adminmicroservice.dto;


import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Purpose:Creating Dto for Admin
 * @author Manoj
 * @Param all the required variables to view in web page
 * Version 1.0
 */
@Data
public class AdminDTO {
    @NotNull(message = "First Name Should Not be Null")
    private String firstName;
    @NotNull(message = "Last Name Should Not be Null")
    private String lastName;
    @NotNull(message = "Mobile Number Should Not be Null")
    private Long mobileNumber;
    @NotNull(message = "Email id  Should Not be Null")
    private String emailId;
    @NotNull(message = "Password Should Not be Null")
    private String password;
}
