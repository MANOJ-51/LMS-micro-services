package com.bridgelabz.techstackmicroservice.service;

import com.bridgelabz.techstackmicroservice.dto.TechStackDTO;
import com.bridgelabz.techstackmicroservice.model.TechStackModel;
import com.bridgelabz.techstackmicroservice.util.ResponseClass;

import java.util.List;

/**
 * Purpose:Creating Interface for techStack service
 * @author Manoj
 * @Param  all service Methods
 * Version 1.0
 */
public interface ITechStackService {
    ResponseClass addTech(String token, TechStackDTO techStackDTO);

    ResponseClass editTech(String token, Long id, TechStackDTO techStackDTO);

    List<TechStackModel> viewList(String token);

    ResponseClass removeTech(String token, Long id);

    ResponseClass addPath(String token, Long techId, String path);
}