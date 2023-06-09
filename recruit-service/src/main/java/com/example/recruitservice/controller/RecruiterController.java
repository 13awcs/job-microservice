package com.example.recruitservice.controller;

import com.example.recruitservice.common.ResponseObject;
import com.example.recruitservice.dto.inputDto.RecruiterInput;
import com.example.recruitservice.modal.dto.LoginRequest;
import com.example.recruitservice.modal.entity.Recruiter;
import com.example.recruitservice.repository.RecruiterRepository;
import com.example.recruitservice.service.RecruiterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/recruiter")
@RequiredArgsConstructor
public class RecruiterController {
    private final RecruiterService recruiterService;
    private final RecruiterRepository recruiterRepository;

    @GetMapping("/profile")
    public ResponseEntity<Recruiter> getProfile(@RequestParam String username){
        return ResponseEntity.status(HttpStatus.OK).body(recruiterService.loadRecruiterByUsername(username));
    }

    @PostMapping("/profile/edit")
    public ResponseEntity<Recruiter> editProfile(@RequestParam Long id, @RequestBody RecruiterInput recruiterInput){
        return ResponseEntity.status(HttpStatus.OK).body(recruiterService.editProfile(id,recruiterInput));
    }

    @PostMapping("login")
    public ResponseEntity<ResponseObject> login(@RequestBody LoginRequest request){
        List<Recruiter> recruiters = recruiterRepository.findAll();
        for (Recruiter recruiter : recruiters) {
            if (recruiter.getUsername().equalsIgnoreCase(request.getUsername()) && request.getPassword().equals(recruiter.getPassword())) {
                if(recruiter.getDisable().equals(new String("false"))) {
                    return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Login successfully !",
                            recruiterService.loadRecruiterByUsername(request.getUsername())));
                }
                else {
                    return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Your account is disable !"));
                }
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject("Username or password is wrong !"));
    }

    @GetMapping("/admin/recruiter/{jobId}")
    public ResponseEntity<ResponseObject> getRecruiterByJobId(@PathVariable Long  jobId){
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(recruiterService.getRecruiterByJobId(jobId)));
    }

    @GetMapping("/admin/get-top")
    public ResponseEntity<ResponseObject> getTopRecruiter(){
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(recruiterService.getTopRecruiter()));
    }

}
