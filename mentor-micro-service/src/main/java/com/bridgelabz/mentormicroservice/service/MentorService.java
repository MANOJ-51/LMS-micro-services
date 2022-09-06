package com.bridgelabz.mentormicroservice.service;

import com.bridgelabz.mentormicroservice.dto.MentorDTO;
import com.bridgelabz.mentormicroservice.exception.CustomExceptions;
import com.bridgelabz.mentormicroservice.model.MentorModel;
import com.bridgelabz.mentormicroservice.repository.IMentorRepository;
import com.bridgelabz.mentormicroservice.util.ResponseClass;
import com.bridgelabz.mentormicroservice.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Purpose:Creating Service for Mentor
 * @author Manoj
 * @Param business logic is present here
 * Version 1.0
 */
@Service
public class MentorService implements IMentorService {
    @Autowired
    IMentorRepository iMentorRepository;

    @Autowired
    TokenUtil tokenUtil;
    
    @Autowired
    RestTemplate restTemplate;


    /**
     * Purpose:Creating method to add mentor
     * @author Manoj
     * @Param mentorDto,token
     */
    @Override
    public ResponseClass addMentor(String token,MentorDTO mentorDTO) {
    	boolean isUserPresent = restTemplate.getForObject("http://admin-service/adminAPIS/validate/"+token, Boolean.class);
        if (isUserPresent) {
        MentorModel mentorModel = new MentorModel(mentorDTO);
        mentorModel.setCreatedDate(LocalDateTime.now());
        iMentorRepository.save(mentorModel);
        return  new ResponseClass(200,"success",mentorModel);
        }
        throw new CustomExceptions(400,"token not valid");
    }

    /**
     * Purpose:Creating method to Update mentor
     * @author Manoj
     * @Param MentorDto ,id ,token
     */
    @Override
    public ResponseClass editMentor(String token,Long id, MentorDTO mentorDTO) {
    	boolean isUserPresent = restTemplate.getForObject("http://admin-service/adminAPIS/validate/"+token, Boolean.class);
        if (isUserPresent) {
            Optional<MentorModel> isMentorPresent = iMentorRepository.findById(id);
            if (isMentorPresent.isPresent()){
                isMentorPresent.get().setEmployeeId(mentorDTO.getEmployeeId());
                isMentorPresent.get().setFirstName(mentorDTO.getFirstName());
                isMentorPresent.get().setLastName(mentorDTO.getLastName());
                isMentorPresent.get().setMentorType(mentorDTO.getMentorType());
                isMentorPresent.get().setMentorRole(mentorDTO.getMentorRole());
                isMentorPresent.get().setMobileNumber(mentorDTO.getMobileNumber());
                isMentorPresent.get().setEmailId(mentorDTO.getEmailId());
                isMentorPresent.get().setYearsOfExperience(mentorDTO.getYearsOfExperience());
                isMentorPresent.get().setPreferredTime(mentorDTO.getPreferredTime());
                isMentorPresent.get().setStartDate(mentorDTO.getStartDate());
                isMentorPresent.get().setStatus(mentorDTO.getStatus());
                isMentorPresent.get().setMentorDescription(mentorDTO.getMentorDescription());
                isMentorPresent.get().setCreatorUser(mentorDTO.getCreatorUser());
                isMentorPresent.get().setSupervisorId(mentorDTO.getSupervisorId());
                isMentorPresent.get().setUpdatedDate(LocalDateTime.now());
                iMentorRepository.save(isMentorPresent.get());
                return new ResponseClass(200,"success",isMentorPresent.get());
            }
        }

        throw new CustomExceptions(400,"Invalid Token");
    }

    /**
     * Purpose:Creating method to get List of mentor
     * @author Manoj
     * @Param  token
     */
    @Override
    public List<MentorModel> viewList(String token) {
    	boolean isUserPresent = restTemplate.getForObject("http://admin-service/adminAPIS/validate/"+token, Boolean.class);
        if (isUserPresent) {
            List<MentorModel> getList = iMentorRepository.findAll();
            if (getList.size()>0){
                return getList;
            }
        }
        throw new CustomExceptions(400,"Invalid Token");
    }

    /**
     * Purpose:Creating method to Delete mentor
     * @author Manoj
     * @Param  token,id
     */
    @Override
    public ResponseClass removeMentor(String token,Long id) {
    	boolean isUserPresent = restTemplate.getForObject("http://admin-service/adminAPIS/validate/"+token, Boolean.class);
        if (isUserPresent) {
            Optional<MentorModel> isMentorPresent = iMentorRepository.findById(id);
            if (isMentorPresent.isPresent()){
                iMentorRepository.delete(isMentorPresent.get());
                return new ResponseClass(200,"success",isMentorPresent.get());
            }
        }

        throw new CustomExceptions(400,"Invalid Token");
    }

    /**
     * Purpose:Creating method to add profile path to mentor
     * @author Manoj
     * @Param  token,profilePath,id
     */
    @Override
    public ResponseClass addPath(String token,Long id, String profilePath) {
    	boolean isUserPresent = restTemplate.getForObject("http://admin-service/adminAPIS/validate/"+token, Boolean.class);
        if (isUserPresent) {
            Optional<MentorModel> isMentorPresent = iMentorRepository.findById(id);
            isMentorPresent.get().setProfilePath(profilePath);
            iMentorRepository.save(isMentorPresent.get());
            return new ResponseClass(200,"success",isMentorPresent.get());
        }
        throw new CustomExceptions(400,"Invalid Token");
    }


    /**
     * Purpose:Creating method to get list by id of mentor
     * @author Manoj
     * @Param  token,id
     */
    @Override
    public ResponseClass getListId(String token,Long id) {
    	boolean isUserPresent = restTemplate.getForObject("http://admin-service/adminAPIS/validate/"+token, Boolean.class);
        if (isUserPresent) {
        return new ResponseClass(200,"success",iMentorRepository.getMentorModelById(id));
        }
        throw new CustomExceptions(400,"Invalid Token");
    }
    
    /**
     * Purpose:Creating method to get count of mentorType
     * @author Manoj
     * @Param  token,userChoice
     */
    @Override
    public ResponseClass getCountByType(String token,String userChoice) {
    	boolean isUserPresent = restTemplate.getForObject("http://admin-service/adminAPIS/validate/"+token, Boolean.class);
        if (isUserPresent) {
        return new ResponseClass(200,"success",iMentorRepository.countByMentorType(userChoice));
       }
        throw new CustomExceptions(400,"Invalid Token");
    }
}