package com.bridgelabz.candidatemicroservice.service;

import com.bridgelabz.candidatemicroservice.dto.CandidateDTO;
import com.bridgelabz.candidatemicroservice.model.CandidateModel;
import com.bridgelabz.candidatemicroservice.util.ResponseClass;

import java.util.List;

/**
 * Purpose:Creating Interface for Candidate service
 * @author Manoj
 * @Param  all service Methods
 * Version 1.0
 */
public interface ICandidateService {
    ResponseClass addCandidate(String token, CandidateDTO candidateDTO);

    ResponseClass editCandidate(String token, Long techId, CandidateDTO candidateDTO);

    List<CandidateModel> viewList(String token);

    ResponseClass removeCandidate(String token,Long id);

    Long getCount(String userChoice);
}
