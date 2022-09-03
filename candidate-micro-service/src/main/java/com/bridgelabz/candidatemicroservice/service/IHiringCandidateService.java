package com.bridgelabz.candidatemicroservice.service;

import com.bridgelabz.candidatemicroservice.dto.HiringCandidateDTO;
import com.bridgelabz.candidatemicroservice.model.HiringCandidateModel;
import com.bridgelabz.candidatemicroservice.util.ResponseClass;

import java.util.List;

/**
 * Purpose:Creating Interface for HiringCandidate service
 * @author Manoj
 * @Param  all service Methods
 * Version 1.0
 */
public interface IHiringCandidateService {
    ResponseClass addHiringCandidate(String token,Long bankId, HiringCandidateDTO hiringCandidateDTO);

    ResponseClass editHiringCandidate(String token,Long id, Long bankId, HiringCandidateDTO hiringCandidateDTO);

    List<HiringCandidateModel> viewList(String token);

    ResponseClass removeHiringCandidate(String token,Long id);
}
