package com.despicabug.jobly.server.service;

import com.despicabug.jobly.server.dto.JobDescriptionDto;
import com.despicabug.jobly.server.entity.JobDescription;
import com.despicabug.jobly.server.repository.JobDescriptionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class JobDescriptionService {
    
    private final JobDescriptionRepository jobDescriptionRepository;
    private final ModelMapper modelMapper;

    public Long createJobDescription(String description) {
        JobDescription jobDescription = new JobDescription();
        jobDescription.setDescription(description);
        
        JobDescription savedJobDescription = jobDescriptionRepository.save(jobDescription);
        return savedJobDescription.getId();
    }
    
    public Optional<JobDescriptionDto> getJobDescription(Long id) {
        return jobDescriptionRepository.findById(id)
                .map(jobDesc -> modelMapper.map(jobDesc, JobDescriptionDto.class));
    }
}
