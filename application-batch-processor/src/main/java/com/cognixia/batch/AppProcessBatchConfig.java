package com.cognixia.batch;

import java.io.IOException;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowJobBuilder;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.builder.SimpleStepBuilder;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.batch.item.json.builder.JsonItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.cognixia.model.ProcessedApplication;

@Configuration
@EnableBatchProcessing
public class AppProcessBatchConfig {

	@Autowired
	private DataSource dataSource;
	
	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	
	//ClassPathResource: just put name of file from resource folder
    public JsonItemReader<ProcessedApplication> jsonItemReader() throws IOException {
        return new JsonItemReaderBuilder<ProcessedApplication>()
                .jsonObjectReader(new JacksonJsonObjectReader<>(ProcessedApplication.class))
                .resource(new ClassPathResource("SFTPreceiveApplications/receivedApplicationsSFTP.json"))
                .name("applicationJsonItemReader")
                .build();   
    }

    @Bean
    public ApplicationProcessor processor() {
        return new ApplicationProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<ProcessedApplication> writer() {
        JdbcBatchItemWriter<ProcessedApplication> writer = new JdbcBatchItemWriter<>();
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        writer.setSql("INSERT INTO processed_application (applicationid, app_status, app_submission_date, country_of_birth, covid_vacc_status, dob, name, race) VALUES (:applicationID, :appStatus, :appSubmissionDate, :countryOfBirth, :covidVaccStatus, :dob, :name, :race)");
        writer.setDataSource(dataSource);
        return writer;
    }

    @Bean
    public Job writeApplicationIntoSqlDb() throws IOException {
        JobBuilder jobBuilder = jobBuilderFactory.get("APPLICATION_JOB");
        jobBuilder.incrementer(new RunIdIncrementer());
        FlowJobBuilder flowJobBuilder = jobBuilder.flow(getFirstStep()).end();
        Job job = flowJobBuilder.build();
        return job;
    }

    @Bean
    public Step getFirstStep() throws IOException {
        StepBuilder stepBuilder = stepBuilderFactory.get("getFirstStep");
        SimpleStepBuilder<ProcessedApplication, ProcessedApplication> simpleStepBuilder = stepBuilder.chunk(1);
        return simpleStepBuilder.reader(jsonItemReader()).processor(processor()).writer(writer()).build();
    }
}