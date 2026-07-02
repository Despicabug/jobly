package com.despicabug.jobly.server.service;

import com.despicabug.jobly.server.dto.ResumeDto;
import com.despicabug.jobly.server.entity.Resume;
import com.despicabug.jobly.server.repository.ResumeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ResumeService {
    
    private final ResumeRepository resumeRepository;
    private final ModelMapper modelMapper;

    public Long uploadResume(MultipartFile file) throws IOException {
        Resume resume = new Resume();
        resume.setFilename(file.getOriginalFilename());
        resume.setFileContent(file.getBytes());
        resume.setFileSize(file.getSize());
        resume.setUploadedAt(LocalDateTime.now());
        
        Resume savedResume = resumeRepository.save(resume);
        return savedResume.getId();
    }
    
    public Optional<ResumeDto> getResume(Long id) {
        return resumeRepository.findById(id).map(resume -> modelMapper.map(resume, ResumeDto.class));
    }
}
