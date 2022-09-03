package com.bridgelabz.candidatemicroservice.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.candidatemicroservice.dto.CandidateDTO;
import com.bridgelabz.candidatemicroservice.model.CandidateModel;
import com.bridgelabz.candidatemicroservice.service.ICandidateService;
import com.bridgelabz.candidatemicroservice.util.ResponseClass;

/**
 * Purpose:Creating apis for Candidate Controller
 * @author Manoj
 * @Param Http METHODS
 * Version 1.0
 */
@RestController
@RequestMapping("/candidateApis")
public class CandidateController {
    @Autowired
    ICandidateService iCandidateService;

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job job;

    /**
     * Purpose:Creating method to add candidate
     * @author Manoj
     * @Param candidateDto,token,techID
     */
    @PostMapping("/createCandidate")
    public ResponseEntity<ResponseClass>  createCandidate(@RequestHeader String token,
                                          @RequestBody @Valid CandidateDTO candidateDTO){
    	ResponseClass responseClass =iCandidateService.addCandidate(token,candidateDTO);
        return new ResponseEntity<>(responseClass,HttpStatus.OK);
    }

    /**
     * Purpose:Creating method to Update Candidate
     * @author Manoj
     * @Param CandidateDto ,id ,token,techID
     */
    @PutMapping("/updateCandidate/{id}")
    public ResponseEntity<ResponseClass>  updateCandidate(@RequestHeader String token,@PathVariable Long id ,
                                          @RequestBody @Valid CandidateDTO candidateDTO){
    	ResponseClass responseClass =iCandidateService.editCandidate(token,id,candidateDTO);
        return new ResponseEntity<>(responseClass,HttpStatus.OK);
    }

    /**
     * Purpose:Creating method to get List of Candidate
     * @author Manoj
     * @Param  token
     */
    @GetMapping("/listCandidate")
    public ResponseEntity<List<?>> getList (@RequestHeader String token){
    	List<CandidateModel> responseClass =iCandidateService.viewList(token);
        return new ResponseEntity<>(responseClass,HttpStatus.OK);
    }

    /**
     * Purpose:Creating method to Delete Candidate
     * @author Manoj
     * @Param  token,id
     */
    @DeleteMapping("/deleteCandidate/{id}")
    public ResponseEntity<ResponseClass>  deleteCandidate(@RequestHeader String token,@PathVariable Long id){
    	ResponseClass responseClass =iCandidateService.removeCandidate(token,id);
        return new ResponseEntity<>(responseClass,HttpStatus.OK);
    }

    /**
     * Purpose:Creating method to get count of Status
     * @author Manoj
     * @Param  token,userChoice
     */
    @GetMapping("/countOfStatus")
    public Long countOfStatus (@RequestHeader String token,@RequestParam String userChoice){
        return iCandidateService.getCount(userChoice);
    }

    /**
     * Purpose:Creating method to add csv data into the database
     * @author Manoj
     * @Param
     */
    @PostMapping("/importCandidates")
    public void importCsvToDbJob(){
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("startAt",System.currentTimeMillis()).toJobParameters();
        try {
            jobLauncher.run(job,jobParameters);
        }catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException |
                JobParametersInvalidException exception){
            exception.printStackTrace();
        }
    }
}
