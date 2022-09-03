package com.bridgelabz.techstackmicroservice.model;

import com.bridgelabz.techstackmicroservice.dto.TechStackDTO;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Purpose:Creating model for TechStack
 * @author Manoj
 * @Param all the required variables to save in repository
 * Version 1.0
 */
@Entity
@Data
@Table(name = "tech_stack")

public class TechStackModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    private String techName;
    private String status;
    private String imagePath;

    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public TechStackModel(TechStackDTO techStackDTO) {
        this.techName = techStackDTO.getTechName();
        this.status = techStackDTO.getStatus();
    }

    public TechStackModel() {
    }
}
