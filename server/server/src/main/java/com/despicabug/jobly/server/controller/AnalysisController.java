package com.despicabug.jobly.server.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/analysis")
@RequiredArgsConstructor
public class AnalysisController {
    
    @PostMapping("/basic-match")
    public ResponseEntity<?> basicMatch() {
        return ResponseEntity.ok("Basic match endpoint");
    }
    
    private String extractTextFromResume(Long resumeId) {
        return null;
    }
    
    private java.util.List<String> extractKeywords(String text) {
        return null;
    }
}
