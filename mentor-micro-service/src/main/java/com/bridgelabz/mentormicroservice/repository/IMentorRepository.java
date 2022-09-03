package com.bridgelabz.mentormicroservice.repository;

import com.bridgelabz.mentormicroservice.model.MentorModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMentorRepository extends JpaRepository<MentorModel,Long> {
    //@Query(value = "select count(id) from mentor_details ",nativeQuery = true)
    MentorModel getMentorModelById(Long id);
    long countByMentorType(String userChoice);
}
