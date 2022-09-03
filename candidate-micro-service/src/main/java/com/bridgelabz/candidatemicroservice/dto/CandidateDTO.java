package com.bridgelabz.candidatemicroservice.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Purpose:Creating Dto for Candidate
 * @author Manoj
 * @Param all the required variables to view in web page
 * Version 1.0
 */
@Data
public class CandidateDTO {
    @NotNull(message = "cic Id Should Not be Null")
    private String cicId;
    @NotNull(message = "Full Name Should Not be Null")
    private String fullName;
    @NotNull(message = "Email Should Not be Null")
    private String email;
    @NotNull(message = "Mobile Number Should Not be Null")
    private String mobileNumber;
    @NotNull(message = "Hire Date Should Not be Null")
    private String hireDate;
    @NotNull(message = "Degree Should Not be Null")
    private String degree;
    @NotNull(message = "Aggregate Percentage Should Not be Null")
    private String aggregatePercentage;
    @NotNull(message = "City Should Not be Null")
    private String city;
    @NotNull(message = "State Should Not be Null")
    private String state;
    @NotNull(message = "Preferred Location Should Not be Null")
    private String preferredJobLocation;
    @NotNull(message = "Status Should Not be Null")
    private String status;
    @NotNull(message = "Pass out Year Should Not be Null")
    private String passedOutYear;
    @NotNull(message = "Creator User Should Not be Null")
    private String creatorUser;
    @NotNull(message = "Candidate Status Should Not be Null")
    private String candidateStatus;
}
