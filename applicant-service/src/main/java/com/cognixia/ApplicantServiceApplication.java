package com.cognixia;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;

import com.cognixia.batch.SFTPBatchConfig.UploadGateway;
import com.cognixia.model.Application;
import com.cognixia.service.ApplicationService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@IntegrationComponentScan
@EnableIntegration
@SpringBootApplication
@EnableEurekaClient
@ComponentScan(basePackages = "com.cognixia.*")
public class ApplicantServiceApplication implements CommandLineRunner{

	@Autowired
	private UploadGateway gateway;
	
	@Autowired
	private ApplicationService appService;
	
	public static void main(String[] args) {
		SpringApplication.run(ApplicantServiceApplication.class, args);
	}
	
    public void generateFile() {
    	//appService.writeToCSV();
    	appService.writeToJSON();
    }
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		generateFile();
		gateway.upload(new File("C:\\Users\\Kevin\\OneDrive\\Documents\\GitHub\\APPLICATION-SUBMISSION-PORTAL\\applicant-service\\applicationJSON\\application.json"));
	}
	
//	@Bean
//	CommandLineRunner runner(ApplicationService appService){
//	    return args -> {
//			// read JSON and load json
//			ObjectMapper mapper = new ObjectMapper();
//			TypeReference<List<Application>> typeReference = new TypeReference<List<Application>>(){};
//			InputStream inputStream = TypeReference.class.getResourceAsStream("/applicant-service/src/main/resources/application.json");
//			try {
//				List<Application> applications = mapper.readValue(inputStream,typeReference);
//				appService.save(applications);
//				System.out.println("applications Saved!");
//			} catch (IOException e){
//				System.out.println("Unable to save users: " + e.getMessage());
//			}
//	    };
//	}
}

//docker run --name my-nginx-1 -d -v /c/tmp/html:/usr/share/nginx/html -p 8085:80 nginx
//docker run -v /c/tmp/ftp/upload:/home/foo/upload -p 22:22 -d atmoz/sftp foo:password:1001
