package com.cognixia.batch;

import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.cognixia.service.ApplicationService;

public class SFTPBatchConfig {
	
	@Autowired
	private ApplicationService appService;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	
	
}
