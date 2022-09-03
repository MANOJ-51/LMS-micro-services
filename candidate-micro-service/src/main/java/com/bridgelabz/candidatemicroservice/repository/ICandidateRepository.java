package com.bridgelabz.candidatemicroservice.repository;

import com.bridgelabz.candidatemicroservice.model.CandidateModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Purpose:Creating repository for Candidate
 * @author Manoj
 * @Param  to save in database
 * Version 1.0
 */
public interface ICandidateRepository extends JpaRepository<CandidateModel,Long> {

    @Query( value = "select count(status) from candidate_details  where status =:name",nativeQuery = true)
        //countByStatus
    Long statusCount(String name);
}
