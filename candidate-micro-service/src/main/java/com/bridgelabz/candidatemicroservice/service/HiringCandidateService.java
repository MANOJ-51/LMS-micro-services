package com.bridgelabz.candidatemicroservice.service;

import com.bridgelabz.candidatemicroservice.dto.HiringCandidateDTO;
import com.bridgelabz.candidatemicroservice.exception.CustomExceptions;
import com.bridgelabz.candidatemicroservice.model.BankModel;
import com.bridgelabz.candidatemicroservice.model.HiringCandidateModel;
import com.bridgelabz.candidatemicroservice.repository.IBankRepository;
import com.bridgelabz.candidatemicroservice.repository.IHiringCandidateRepository;
import com.bridgelabz.candidatemicroservice.util.ResponseClass;
import com.bridgelabz.candidatemicroservice.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Purpose:Creating Service for HiringCandidate
 * @author Manoj
 * @Param business logic is present here
 * Version 1.0
 */
@Service
public class HiringCandidateService implements IHiringCandidateService{
    @Autowired
    IHiringCandidateRepository iHiringCandidateRepository;

    @Autowired
    TokenUtil tokenUtil;

    @Autowired
    IBankRepository iBankRepository;
    
    @Autowired
    RestTemplate restTemplate;

    /**
     * Purpose:Creating method to add hiring candidate
     * @author Manoj
     * @Param hiringCandidateDto,token,bankID,
     */
    @Override
    public ResponseClass addHiringCandidate(String token,Long bankId, HiringCandidateDTO hiringCandidateDTO) {
    	boolean isUserPresent = restTemplate.getForObject("http://localhost:5666/adminAPIS/validate/"+token, Boolean.class);
        if (isUserPresent) {
            HiringCandidateModel hiringCandidateModel = new HiringCandidateModel(hiringCandidateDTO);
            hiringCandidateModel.setCreatedStamp(LocalDateTime.now());
            Optional<BankModel> isBankPresent = iBankRepository.findById(bankId);
            hiringCandidateModel.setBankModel(isBankPresent.get());
            iHiringCandidateRepository.save(hiringCandidateModel);
            return new ResponseClass(200,"success",hiringCandidateModel);
        }
        throw new CustomExceptions(400,"Invalid Token");
    }

    /**
     * Purpose:Creating method to Update Hiring candidate
     * @author Manoj
     * @Param HiringCandidateDto ,id ,token,bankID
     */
    @Override
    public ResponseClass editHiringCandidate(String token,Long id, Long bankId, HiringCandidateDTO hiringCandidateDTO) {
    	boolean isUserPresent = restTemplate.getForObject("http://localhost:5666/adminAPIS/validate/"+token, Boolean.class);
        if (isUserPresent) {
            Optional<HiringCandidateModel> isIdPresent = iHiringCandidateRepository.findById(id);
            if (isIdPresent.isPresent()){
                isIdPresent.get().setCicId(hiringCandidateDTO.getCicId());
                isIdPresent.get().setFullName(hiringCandidateDTO.getFullName());
                isIdPresent.get().setEmail(hiringCandidateDTO.getEmail());
                isIdPresent.get().setMobileNumber(hiringCandidateDTO.getMobileNumber());
                isIdPresent.get().setHireDate(hiringCandidateDTO.getHireDate());
                isIdPresent.get().setDegree(hiringCandidateDTO.getDegree());
                isIdPresent.get().setAggregatePercentage(hiringCandidateDTO.getAggregatePercentage());
                isIdPresent.get().setCity(hiringCandidateDTO.getCity());
                isIdPresent.get().setState(hiringCandidateDTO.getState());
                isIdPresent.get().setJobLocation(hiringCandidateDTO.getJobLocation());
                isIdPresent.get().setStatus(hiringCandidateDTO.getStatus());
                isIdPresent.get().setPassedOutYear(hiringCandidateDTO.getPassedOutYear());
                isIdPresent.get().setCreatorUser(hiringCandidateDTO.getCreatorUser());
                isIdPresent.get().setCandidateStatus(hiringCandidateDTO.getCandidateStatus());
                isIdPresent.get().setUpdatedStamp(LocalDateTime.now());
                Optional<BankModel> isBankPresent = iBankRepository.findById(bankId);
                isIdPresent.get().setBankModel(isBankPresent.get());
                iHiringCandidateRepository.save(isIdPresent.get());
                return new ResponseClass(200,"success",isIdPresent.get());
            }
        }
        throw new CustomExceptions(400,"Invalid Token");
    }

    /**
     * Purpose:Creating method to get List of Hiring candidate
     * @author Manoj
     * @Param  token
     */
    @Override
    public List<HiringCandidateModel> viewList(String token) {
    	boolean isUserPresent = restTemplate.getForObject("http://localhost:5666/adminAPIS/validate/"+token, Boolean.class);
        if (isUserPresent) {
            List<HiringCandidateModel> getList = iHiringCandidateRepository.findAll();
            if (getList.size()>0){
                return getList;
            }
        }
        throw new CustomExceptions(400,"Invalid Token");
    }

    /**
     * Purpose:Creating method to Delete Hiring Candidate
     * @author Manoj
     * @Param  token,id
     */
    @Override
    public ResponseClass removeHiringCandidate(String token,Long id) {
    	boolean isUserPresent = restTemplate.getForObject("http://localhost:5666/adminAPIS/validate/"+token, Boolean.class);
        if (isUserPresent) {
            Optional<HiringCandidateModel> isIdPresent = iHiringCandidateRepository.findById(id);
            if (isIdPresent.isPresent()){
                iHiringCandidateRepository.delete(isIdPresent.get());
                return new ResponseClass(200,"success",isIdPresent.get());
            }
        }
        throw new CustomExceptions(400,"Invalid Token");
    }
}
