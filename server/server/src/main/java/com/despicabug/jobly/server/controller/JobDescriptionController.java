package com.despicabug.jobly.server.controller;

import com.despicabug.jobly.server.dto.JobDescriptionDto;
import com.despicabug.jobly.server.service.JobDescriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/job-descriptions")
@RequiredArgsConstructor
public class JobDescriptionController {
    
    private final JobDescriptionService jobDescriptionService;
    
    @PostMapping
    public ResponseEntity<?> createJobDescription(@RequestBody JobDescriptionDto request) {
        if (request.getDescription() == null || request.getDescription().trim().isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Description cannot be empty");
        }
        
        Long jobDescriptionId = jobDescriptionService.createJobDescription(request.getDescription());
        
        Map<String, Long> response = new HashMap<>();
        response.put("id", jobDescriptionId);
        
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getJobDescription(@PathVariable Long id) {
        Optional<JobDescriptionDto> jobDescription = jobDescriptionService.getJobDescription(id);
        
        if (jobDescription.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Job description not found");
        }
        
        return ResponseEntity.ok(jobDescription.get());
    }
}
