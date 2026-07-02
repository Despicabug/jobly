package com.despicabug.jobly.server.repository;

import com.despicabug.jobly.server.entity.JobDescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobDescriptionRepository extends JpaRepository<JobDescription, Long> {
}
