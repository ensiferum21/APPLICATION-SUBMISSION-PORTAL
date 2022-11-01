package com.cognixia.batch;

import org.springframework.batch.item.ItemProcessor;

import com.cognixia.model.Application;

public class ApplicationProcessor implements ItemProcessor<Application, Application> {

  //private static final Logger log = LoggerFactory.getLogger(ApplicationProcessor.class);

  @Override
  public Application process(Application application) throws Exception {
	  
	application.setAppStatus("Pending Review");
		
    return application;
  }

}