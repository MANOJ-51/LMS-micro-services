package com.bridgelabz.mentormicroservice.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Purpose:Creating Dto for Mentor
 * @author Manoj
 * @Param all the required variables to view in web page
 * Version 1.0
 */
@Data
public class MentorDTO {
    @NotNull(message = "EmployeeId should not be null")
    private String employeeId;
    @NotNull(message = "First Name should not be null")
    private String firstName;
    @NotNull(message = "Last Name should not be null")
    private String lastName;
    @NotNull(message = "Mentor Type should not be null")
    private String mentorType;
    @NotNull(message = "Mentor Role should not be null")
    private String mentorRole;
    @NotNull(message = "Mobile Number should not be null")
    private String mobileNumber;
    @NotNull(message = "EmailId should not be null")
    private String emailId;
    @NotNull(message = "Year of Experience should not be null")
    private String yearsOfExperience;
    @NotNull(message = " Preferred Time should not be null")
    private String preferredTime;
    @NotNull(message = "should not be null")
    private LocalDateTime startDate;
    @NotNull(message = "should not be null")
    private String status;
    @NotNull(message = "should not be null")
    private String mentorDescription;
    @NotNull(message = "should not be null")
    private String creatorUser;
    @NotNull(message = "should not be null")
    private String supervisorId;
}