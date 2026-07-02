package com.despicabug.jobly.server.controller;

import com.despicabug.jobly.server.dto.ResumeDto;
import com.despicabug.jobly.server.dto.ResumeUploadResponse;
import com.despicabug.jobly.server.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/api/resumes")
@RequiredArgsConstructor
public class ResumeController {
    
    private static final long MAX_FILE_SIZE = 5 * 1024 * 1024; // 5MB
    private static final String PDF_CONTENT_TYPE = "application/pdf";
    
    private final ResumeService resumeService;
    
    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<?> uploadResume(@RequestParam("file") MultipartFile file) {
        try {
            if (file == null || file.isEmpty()) {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body("File cannot be empty");
            }
            
            if (file.getSize() > MAX_FILE_SIZE) {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body("File size exceeds 5MB limit");
            }
            
            if (!PDF_CONTENT_TYPE.equals(file.getContentType())) {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body("Only PDF files are allowed");
            }
            
            Long resumeId = resumeService.uploadResume(file);
            
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new ResumeUploadResponse(resumeId, "Resume uploaded successfully"));
                    
        } catch (IOException e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error uploading file: " + e.getMessage());
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> downloadResume(@PathVariable Long id) {
        Optional<ResumeDto> resumeDto = resumeService.getResume(id);
        
        if (resumeDto.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Resume not found");
        }
        
        ResumeDto resume = resumeDto.get();
        
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, 
                        "attachment; filename=\"" + resume.getFilename() + "\"")
                .header(HttpHeaders.CONTENT_LENGTH, String.valueOf(resume.getFileSize()))
                .body(resume.getFileContent());
    }
}

