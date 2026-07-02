package com.despicabug.jobly.server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResumeUploadResponse {
    private Long id;
    private String message;
}
