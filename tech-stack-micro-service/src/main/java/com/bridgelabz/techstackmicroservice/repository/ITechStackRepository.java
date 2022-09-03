package com.bridgelabz.techstackmicroservice.repository;

import com.bridgelabz.techstackmicroservice.model.TechStackModel;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Purpose:Creating repository for TechStack
 * @author Manoj
 * @Param  to save in database
 * Version 1.0
 */
public interface ITechStackRepository extends JpaRepository<TechStackModel,Long> {
}
