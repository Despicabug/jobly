package com.despicabug.jobly.server.dto;

import lombok.Data;

@Data
public class ResumeDto {
    private Long id;
    private String filename;
    private byte[] fileContent;
    private Long fileSize;
}
