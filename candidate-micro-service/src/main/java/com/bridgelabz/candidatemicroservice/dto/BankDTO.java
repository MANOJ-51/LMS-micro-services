package com.bridgelabz.candidatemicroservice.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Purpose:Creating Dto for BankDetails
 * @author Manoj
 * @Param all the required variables to view in web page
 * Version 1.0
 */
@Data
public class BankDTO {
    @NotNull(message = "Account Number Should Not be Null")
    private String accountNumber;
    @NotNull(message = "Ifsc Code Should Not be Null")
    private String ifscCode;
    @NotNull(message = "Mobile Number Should Not be Null")
    private Long mobileNumber;
    @NotNull(message = "Branch Should Not be Null")
    private String branch;
    @NotNull(message = "Account Holder Name Should Not be Null")
    private String accountHolderName;
}
