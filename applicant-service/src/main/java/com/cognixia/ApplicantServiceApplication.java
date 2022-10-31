package com.cognixia;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;

import com.cognixia.batch.SFTPBatchConfig.UploadGateway;
import com.cognixia.service.ApplicationService;

@IntegrationComponentScan
@EnableIntegration
@SpringBootApplication
@EnableEurekaClient
public class ApplicantServiceApplication implements CommandLineRunner{
	
	//implements CommandLineRunner for SFTP

	@Autowired
	private UploadGateway gateway;
	
	@Autowired
	private ApplicationService appService;
	
	public static void main(String[] args) {
		SpringApplication.run(ApplicantServiceApplication.class, args);
	}
	
    public void generateJsonFile() {
    	appService.writeToJSON();
    }
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		generateJsonFile();
		gateway.upload(new File("C:\\Users\\Kevin\\OneDrive\\Documents\\GitHub\\APPLICATION-SUBMISSION-PORTAL\\applicant-service\\applicationJSON\\application.json"));
	}

}
