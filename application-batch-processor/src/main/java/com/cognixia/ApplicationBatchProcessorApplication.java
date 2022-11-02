package com.cognixia;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ApplicationBatchProcessorApplication {

	public static void main(String[] args) throws IOException {
		downloadJSONFile();
		SpringApplication.run(ApplicationBatchProcessorApplication.class, args);		
	}
	
	//download json file from SFTP server to resource folder
		public static void downloadJSONFile() throws IOException {

	        File source = new File("C:\\tmp\\ftp\\upload\\application.json");
	        File dest = new File("C:\\Users\\Kevin\\Documents\\workspace-spring-tool-suite-4-4.16.0.RELEASE\\application-batch-processor\\src\\main\\resources\\SFTPreceiveApplications\\receivedApplicationsSFTP.json");

	        InputStream is = null;
	        OutputStream os = null;
	        try {
	            is = new FileInputStream(source);
	            os = new FileOutputStream(dest);
	            byte[] buffer = new byte[1024];
	            int length;
	            while ((length = is.read(buffer)) > 0) {
	                os.write(buffer, 0, length);
	            }
	        } finally {
	            is.close();
	            os.close();
	        }
	    }

}
