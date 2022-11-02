package com.cognixia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognixia.model.ProcessedApplication;

@Repository
public interface ProcessedApplicationRepository extends JpaRepository<ProcessedApplication, Integer>{

	
}
