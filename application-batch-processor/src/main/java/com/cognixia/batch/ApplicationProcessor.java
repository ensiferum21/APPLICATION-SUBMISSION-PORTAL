package com.cognixia.batch;

import org.springframework.batch.item.ItemProcessor;

import com.cognixia.model.ProcessedApplication;

public class ApplicationProcessor implements ItemProcessor<ProcessedApplication, ProcessedApplication> {

  //private static final Logger log = LoggerFactory.getLogger(ApplicationProcessor.class);

  @Override
  public ProcessedApplication process(ProcessedApplication application) throws Exception {
	  
	application.setAppStatus("Pending Review");
		
    return application;
  }

}