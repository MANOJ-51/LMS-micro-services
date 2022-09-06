package com.bridgelabz.techstackmicroservice.service;

import com.bridgelabz.techstackmicroservice.dto.TechStackDTO;
import com.bridgelabz.techstackmicroservice.exception.CustomExceptions;
import com.bridgelabz.techstackmicroservice.model.TechStackModel;
import com.bridgelabz.techstackmicroservice.repository.ITechStackRepository;
import com.bridgelabz.techstackmicroservice.util.ResponseClass;
import com.bridgelabz.techstackmicroservice.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Purpose:Creating Service for TechStack
 * @author Manoj
 * @Param business logic is present here
 * Version 1.0
 */
@Service
public class TechStackService implements ITechStackService {

    @Autowired
    ITechStackRepository iTechStackRepository;

    @Autowired
    TokenUtil tokenUtil;

    @Autowired
    RestTemplate restTemplate;

    /**
     * Purpose:Creating method to add tech
     *
     * @author Manoj
     * @Param techStackDto, token
     */
    @Override
    public ResponseClass addTech(String token, TechStackDTO techStackDTO) {
        boolean isUserPresent = restTemplate.getForObject("http://admin-service/adminAPIS/validate/"+token, Boolean.class);
        if (isUserPresent) {
            TechStackModel techStackModel = new TechStackModel(techStackDTO);
            techStackModel.setCreatedDate(LocalDateTime.now());
            iTechStackRepository.save(techStackModel);
            return new ResponseClass(200,"success",techStackModel);
        }
        throw new CustomExceptions(400,"token not valid");
    }


    /**
     * Purpose:Creating method to Update tech
     *
     * @author Manoj
     * @Param techStackDto ,id ,token
     */
    @Override
    public ResponseClass editTech(String token, Long id, TechStackDTO techStackDTO) {
    	boolean isUserPresent = restTemplate.getForObject("http://admin-service/adminAPIS/validate/"+token, Boolean.class);
        if (isUserPresent) {
            Optional<TechStackModel> isTechPresent = iTechStackRepository.findById(id);
            if (isTechPresent.isPresent()){
                isTechPresent.get().setTechName(techStackDTO.getTechName());
                isTechPresent.get().setStatus(techStackDTO.getStatus());
                isTechPresent.get().setUpdatedDate(LocalDateTime.now());
                iTechStackRepository.save(isTechPresent.get());
                return new ResponseClass(200,"success",isTechPresent.get());
            }
        }
        throw new CustomExceptions(400,"Invalid Token");
    }

    /**
     * Purpose:Creating method to get List of techStack
     * @author Manoj
     * @Param  token
     */
    @Override
    public List<TechStackModel> viewList(String token) {
    	boolean isUserPresent = restTemplate.getForObject("http://admin-service/adminAPIS/validate/"+token, Boolean.class);
        if (isUserPresent) {
            List<TechStackModel> isTechPresent = iTechStackRepository.findAll();
            if (isTechPresent.size()>0){
                return isTechPresent;
            }
        }
        throw new CustomExceptions(400,"Invalid Token");
    }

    /**
     * Purpose:Creating method to Delete techStack
     *
     * @author Manoj
     * @Param token, id
     */
    @Override
    public ResponseClass removeTech(String token, Long id) {
    	boolean isUserPresent = restTemplate.getForObject("http://admin-service/adminAPIS/validate/"+token, Boolean.class);
        if (isUserPresent) {
            Optional<TechStackModel> isTechPresent = iTechStackRepository.findById(id);
            if (isTechPresent.isPresent()){
                iTechStackRepository.delete(isTechPresent.get());
                return new ResponseClass(200,"success",isTechPresent.get());
            }
        }
        throw new CustomExceptions(400,"Invalid Token");
    }

    /**
     * Purpose:Creating method to add profile path to techStack
     *
     * @author Manoj
     * @Param token, profilePath, techID
     */
    @Override
    public ResponseClass addPath(String token, Long techId, String path) {
    	boolean isUserPresent = restTemplate.getForObject("http://admin-service/adminAPIS/validate/"+token, Boolean.class);
        if (isUserPresent) {
            Optional<TechStackModel> isTechPresent = iTechStackRepository.findById(techId);
            if (isTechPresent.isPresent()){
                isTechPresent.get().setImagePath(path);
                iTechStackRepository.save(isTechPresent.get());
                return new ResponseClass(200,"success",isTechPresent.get());
            }
        }

        throw new CustomExceptions(400,"Invalid Token");
    }
}
