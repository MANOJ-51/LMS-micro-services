package com.bridgelabz.candidatemicroservice.service;

import com.bridgelabz.candidatemicroservice.dto.BankDTO;
import com.bridgelabz.candidatemicroservice.exception.CustomExceptions;
import com.bridgelabz.candidatemicroservice.model.BankModel;
import com.bridgelabz.candidatemicroservice.repository.IBankRepository;
import com.bridgelabz.candidatemicroservice.util.ResponseClass;
import com.bridgelabz.candidatemicroservice.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Purpose:Creating Service for bankDetails
 * @author Manoj
 * @Param business logic is present here
 * Version 1.0
 */
@Service
public class BankService implements IBankService{
    @Autowired
    IBankRepository iBankRepository;

    @Autowired
    TokenUtil tokenUtil;
    
    @Autowired
    RestTemplate restTemplate;


    /**
     * Purpose:Creating method to add bank
     * @author Manoj
     * @Param bankDto,token
     */
    @Override
    public ResponseClass addBankDetails(String token,BankDTO bankDTO) {
    	boolean isUserPresent = restTemplate.getForObject("http://admin-service/adminAPIS/validate/"+token, Boolean.class);
        if (isUserPresent) {
            BankModel bankModel = new BankModel(bankDTO);
            bankModel.setCreatedDate(LocalDateTime.now());
            iBankRepository.save(bankModel);
            return new ResponseClass(200,"success",bankModel);
        }
        throw new CustomExceptions(400,"Invalid Token");
    }

    /**
     * Purpose:Creating method to Update Bank
     * @author Manoj
     * @Param BankDto ,id ,token
     */
    @Override
    public ResponseClass editBank(String token,Long id, BankDTO bankDetailsDTO) {
    	boolean isUserPresent = restTemplate.getForObject("http://admin-service/adminAPIS/validate/"+token, Boolean.class);
        if (isUserPresent) {
            Optional<BankModel> isBankPresent = iBankRepository.findById(id);
            if (isBankPresent.isPresent()){
                isBankPresent.get().setAccountHolderName(bankDetailsDTO.getAccountHolderName());
                isBankPresent.get().setAccountNumber(bankDetailsDTO.getAccountNumber());
                isBankPresent.get().setIfscCode(bankDetailsDTO.getIfscCode());
                isBankPresent.get().setBranch(bankDetailsDTO.getBranch());
                isBankPresent.get().setMobileNumber(bankDetailsDTO.getMobileNumber());
                iBankRepository.save(isBankPresent.get());
                return new ResponseClass(200,"success",isBankPresent.get());
            }
        }

        throw new CustomExceptions(400,"Invalid Token");
    }

    /**
     * Purpose:Creating method to get List of Bank
     * @author Manoj
     * @Param  token
     */
    @Override
    public List<BankModel> viewList(String token) {
    	boolean isUserPresent = restTemplate.getForObject("http://admin-service/adminAPIS/validate/"+token, Boolean.class);
        if (isUserPresent) {
        List<BankModel> getList = iBankRepository.findAll();
            if (getList.size()>0){
                return getList;
            }
        }
        throw new CustomExceptions(400,"Invalid Token");
    }

    /**
     * Purpose:Creating method to Delete Bank
     * @author Manoj
     * @Param  token,id
     */
    @Override
    public ResponseClass removeBank(String token,Long id) {
    	boolean isUserPresent = restTemplate.getForObject("http://admin-service/adminAPIS/validate/"+token, Boolean.class);
        if (isUserPresent) {
            Optional<BankModel> isBankPresent = iBankRepository.findById(id);
            if (isBankPresent.isPresent()){
                iBankRepository.delete(isBankPresent.get());
                return new ResponseClass(200,"success",isBankPresent.get());
            }
        }

        throw new CustomExceptions(400,"Invalid Token");
    }
}
