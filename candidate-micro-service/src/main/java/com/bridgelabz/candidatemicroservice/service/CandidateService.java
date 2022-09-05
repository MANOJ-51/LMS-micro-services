package com.bridgelabz.candidatemicroservice.service;

import com.bridgelabz.candidatemicroservice.dto.CandidateDTO;
import com.bridgelabz.candidatemicroservice.exception.CustomExceptions;
import com.bridgelabz.candidatemicroservice.model.CandidateModel;
import com.bridgelabz.candidatemicroservice.repository.ICandidateRepository;
import com.bridgelabz.candidatemicroservice.util.ResponseClass;
import com.bridgelabz.candidatemicroservice.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Purpose:Creating Service for Candidate
 * @author Manoj
 * @Param business logic is present here
 * Version 1.0
 */
@Service
public class CandidateService implements ICandidateService {
    @Autowired
    ICandidateRepository iCandidateRepository;
    @Autowired
    TokenUtil tokenUtil;

    @Autowired
    RestTemplate restTemplate;

    /**
     * Purpose:Creating method to add candidate
     * @author Manoj
     * @Param candidateDto,token,techID
     */
    @Override
    public ResponseClass addCandidate(String token, CandidateDTO candidateDTO) {
    	boolean isUserPresent = restTemplate.getForObject("http://localhost:5666/adminAPIS/validate/"+token, Boolean.class);
        if (isUserPresent) {
            CandidateModel candidateModel = new CandidateModel(candidateDTO);
            candidateModel.setCreatedStamp(LocalDateTime.now());
            iCandidateRepository.save(candidateModel);
            return new ResponseClass(200,"success",candidateModel);
        }
        throw new CustomExceptions(400,"Invalid Token");
    }

    /**
     * Purpose:Creating method to Update Candidate
     * @author Manoj
     * @Param CandidateDto ,id ,token,techID
     */
    @Override
    public ResponseClass editCandidate(String token,Long id, CandidateDTO candidateDTO) {
    	boolean isUserPresent = restTemplate.getForObject("http://localhost:5666/adminAPIS/validate/"+token, Boolean.class);
        if (isUserPresent) {
            Optional<CandidateModel> isIdPresent = iCandidateRepository.findById(id);
            if (isIdPresent.isPresent()){
                isIdPresent.get().setCicId(candidateDTO.getCicId());
                isIdPresent.get().setFullName(candidateDTO.getFullName());
                isIdPresent.get().setEmail(candidateDTO.getEmail());
                isIdPresent.get().setMobileNumber(candidateDTO.getMobileNumber());
                isIdPresent.get().setHireDate(candidateDTO.getHireDate());
                isIdPresent.get().setDegree(candidateDTO.getDegree());
                isIdPresent.get().setAggregatePercentage(candidateDTO.getAggregatePercentage());
                isIdPresent.get().setCity(candidateDTO.getCity());
                isIdPresent.get().setState(candidateDTO.getState());
                isIdPresent.get().setPreferredJobLocation(candidateDTO.getPreferredJobLocation());
                isIdPresent.get().setStatus(candidateDTO.getStatus());
                isIdPresent.get().setPassedOutYear(candidateDTO.getPassedOutYear());
                isIdPresent.get().setCreatorUser(candidateDTO.getCreatorUser());
                isIdPresent.get().setCandidateStatus(candidateDTO.getCandidateStatus());
                isIdPresent.get().setUpdatedStamp(LocalDateTime.now());
                iCandidateRepository.save(isIdPresent.get());
                return new ResponseClass(200,"success",isIdPresent.get());
            }
        }

        throw new CustomExceptions(400,"Invalid Token");
    }

    /**
     * Purpose:Creating method to get List of Candidate
     * @author Manoj
     * @Param  token
     */
    @Override
    public List<CandidateModel> viewList(String token) {
    	boolean isUserPresent = restTemplate.getForObject("http://localhost:5666/adminAPIS/validate/"+token, Boolean.class);
        if (isUserPresent) {
            List<CandidateModel> getList = iCandidateRepository.findAll();
            if (getList.size()>0){
                return getList;
            }
        }
        throw new CustomExceptions(400,"Invalid Token");
    }


    /**
     * Purpose:Creating method to Delete Candidate
     * @author Manoj
     * @Param  token,id
     */
    @Override
    public ResponseClass removeCandidate(String token,Long id) {
    	boolean isUserPresent = restTemplate.getForObject("http://localhost:5666/adminAPIS/validate/"+token, Boolean.class);
        if (isUserPresent) {
            Optional<CandidateModel> isIdPresent = iCandidateRepository.findById(id);
            if (isIdPresent.isPresent()){
                iCandidateRepository.delete(isIdPresent.get());
                return new ResponseClass(200,"success",isIdPresent.get());
            }
        }
        throw new CustomExceptions(400,"Invalid Token");
    }

    /**
     * Purpose:Creating method to get count of Status
     * @author Manoj
     * @Param  token,userChoice
     */
    @Override
    public Long getCount(String userChoice) {

        return iCandidateRepository.statusCount(userChoice);
    }

}
