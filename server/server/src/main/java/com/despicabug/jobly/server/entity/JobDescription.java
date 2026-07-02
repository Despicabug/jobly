package com.despicabug.jobly.server.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "job_descriptions")
@Data
@Getter
@Setter
public class JobDescription {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Lob
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;
}
