package com.cognixia;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
	
	@Override
	public void run(String... args) throws Exception {
		//1. write data from application table to JSON file
		appService.writeToJSON();
		//2. upload JSON file to SFTP
		gateway.upload(new File("C:\\Users\\Kevin\\OneDrive\\Documents\\GitHub\\APPLICATION-SUBMISSION-PORTAL\\applicant-service\\src\\main\\resources\\tempJSON\\application.json"));
		//3. purge data in application table
		appService.purgeData();
	}

}

//docker run --name my-nginx-1 -d -v /c/tmp/html:/usr/share/nginx/html -p 8085:80 nginx
//docker run -v /c/tmp/ftp/upload:/home/foo/upload -p 22:22 -d atmoz/sftp foo:password:1001
