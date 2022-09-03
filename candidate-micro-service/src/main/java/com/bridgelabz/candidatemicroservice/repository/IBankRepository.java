package com.bridgelabz.candidatemicroservice.repository;

import com.bridgelabz.candidatemicroservice.model.BankModel;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * Purpose:Creating repository for bankDetails
 * @author Manoj
 * @Param  to save in database
 * Version 1.0
 */
public interface IBankRepository extends JpaRepository<BankModel,Long> {
}
