package com.bridgelabz.techstackmicroservice.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Purpose:Creating Dto for TechStack
 * @author Manoj
 * @Param all the required variables to view in web page
 * Version 1.0
 */
@Data
public class TechStackDTO {
    @NotNull(message = "not null")
    private String techName;
    @NotNull(message = "not null")
    private String status;
}
