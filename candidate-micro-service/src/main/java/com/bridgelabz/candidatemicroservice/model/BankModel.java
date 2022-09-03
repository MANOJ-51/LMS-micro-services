package com.bridgelabz.candidatemicroservice.model;

import com.bridgelabz.candidatemicroservice.dto.BankDTO;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Purpose:Creating model for BankDetails
 * @author Manoj
 * @Param all the required variables to save in repository
 * Version 1.0
 */
@Entity
@Data
@Table(name = "bank_micro_details")
public class BankModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private String accountNumber;
    private String ifscCode;
    private Long mobileNumber;
    private String branch;
    private String accountHolderName;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

    public BankModel(BankDTO bankDTO) {
        this.accountHolderName = bankDTO.getAccountHolderName();
        this.accountNumber = bankDTO.getAccountNumber();
        this.branch = bankDTO.getBranch();
        this.ifscCode = bankDTO.getIfscCode();
        this.mobileNumber = bankDTO.getMobileNumber();
    }

    public BankModel() {
    }
}