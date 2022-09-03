package com.bridgelabz.candidatemicroservice.service;

import com.bridgelabz.candidatemicroservice.dto.BankDTO;
import com.bridgelabz.candidatemicroservice.model.BankModel;
import com.bridgelabz.candidatemicroservice.util.ResponseClass;

import java.util.List;

/**
 * Purpose:Creating Interface for bankDetails service
 * @author Manoj
 * @Param  all service Methods
 * Version 1.0
 */
public interface IBankService {
    ResponseClass addBankDetails(String token, BankDTO bankModel);

    ResponseClass editBank(String token, Long id, BankDTO bankDTO);

    List<BankModel> viewList(String token);

    ResponseClass removeBank(String token, Long id);
}
