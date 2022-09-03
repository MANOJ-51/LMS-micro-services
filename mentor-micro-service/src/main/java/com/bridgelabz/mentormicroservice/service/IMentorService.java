package com.bridgelabz.mentormicroservice.service;

import com.bridgelabz.mentormicroservice.dto.MentorDTO;
import com.bridgelabz.mentormicroservice.model.MentorModel;
import com.bridgelabz.mentormicroservice.util.ResponseClass;

import java.util.List;

/**
 * Purpose:Creating Interface for Mentor service
 * @author Manoj
 * @Param  all service Methods
 * Version 1.0
 */
public interface IMentorService {
    ResponseClass addMentor(String token, MentorDTO mentorDTO);

    ResponseClass editMentor(String token, Long id, MentorDTO mentorDTO);

    List<MentorModel> viewList(String token);

    ResponseClass removeMentor(String token, Long id);

    ResponseClass addPath(String token, Long id, String profilePath);

    ResponseClass getListId(String token, Long id);

    ResponseClass getCountByType(String userChoice, String token);

}
