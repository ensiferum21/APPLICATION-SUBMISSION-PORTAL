package com.cognixia.batch;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.batch.item.json.builder.JsonItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.cognixia.model.Application;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@EnableBatchProcessing
public class AppProcessBatchConfig {

	@Autowired
	private DataSource dataSource;
	
	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	
	private static final Logger log = LoggerFactory.getLogger(ApplicationProcessor.class);
	// end::setup[]

	// tag::readerwriterprocessor[]
//	@Bean
//	public FlatFileItemReader<Application> reader() {
//		return new FlatFileItemReaderBuilder<Application>()
//			.name("applicationItemReader")
//			.resource(new ClassPathResource("C:\\tmp\\ftp\\upload\\application.json"))
//			.delimited()
//			.names(new String[]{"applicationid", "app_status", "app_submission_date", "country_of_birth", "covid_vacc_status", "dob", "name", "race"})
//			.fieldSetMapper(new BeanWrapperFieldSetMapper<Application>() {{
//				setTargetType(Application.class);
//			}})
//			.build();
//	}
//	@Bean
//    public JsonItemReader<Application> jsonItemReader() {
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        JacksonJsonObjectReader<Application> jsonObjectReader = new JacksonJsonObjectReader<>(Application.class);
//
//        jsonObjectReader.setMapper(objectMapper);
//
//        return new JsonItemReaderBuilder<Application>()
//                .jsonObjectReader(jsonObjectReader)
//                .resource(new ClassPathResource("C:\\tmp\\ftp\\upload\\application.json"))
//                .name("applicationItemReader")
//                .build();
//    }
//
//	@Bean
//	public ApplicationProcessor processor() {
//		return new ApplicationProcessor();
//	}
//
//	@Bean
//	public JdbcBatchItemWriter<Application> writer(DataSource dataSource) {
//		return new JdbcBatchItemWriterBuilder<Application>()
//			.itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
//			.sql("INSERT INTO app_processed (applicationid, app_status, app_submission_date, country_of_birth, covid_vacc_status, dob, name, race) VALUES (:applicationid, :app_status, :app_submission_date, :country_of_birth, :covid_vacc_status, :dob, :name, :race)")
//			.dataSource(dataSource)
//			.build();
//	}
//	// end::readerwriterprocessor[]
//
//	// tag::jobstep[]
//	@Bean
//	public Job importAppJob(JobCompletionNotificationListener listener, Step step1) {
//		return jobBuilderFactory.get("importAppJob")
//			.incrementer(new RunIdIncrementer())
//			.listener(listener)
//			.flow(step1)
//			.end()
//			.build();
//	}
//
//	@Bean
//	public Step step1(JdbcBatchItemWriter<Application> writer) {
//		return stepBuilderFactory.get("step1")
//			.<Application, Application> chunk(10)
//			.reader(jsonItemReader())
//			.processor(processor())
//			.writer(writer)
//			.build();
//	}
//	// end::jobstep[]
//==============================================[TESTING]============================================================
	
	//ClassPathResource: just put name of file from resource folder
    public JsonItemReader<Application> jsonItemReader() {
        return new JsonItemReaderBuilder<Application>()
                .jsonObjectReader(new JacksonJsonObjectReader<>(Application.class))
                .resource(new ClassPathResource("tempJSON/application.json"))
                .name("applicationJsonItemReader")
                .build();   
    }

    @Bean
    public ApplicationProcessor processor() {
        return new ApplicationProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<Application> writer() {
        JdbcBatchItemWriter<Application> writer = new JdbcBatchItemWriter<>();
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        writer.setSql("INSERT INTO app_processed (applicationid, app_status, app_submission_date, country_of_birth, covid_vacc_status, dob, name, race) VALUES (:applicationID, :appStatus, :appSubmissionDate, :countryOfBirth, :covidVaccStatus, :dob, :name, :race)");
        writer.setDataSource(dataSource);
        return writer;
    }

    @Bean
    public Job writeStudentDataIntoSqlDb() {
        JobBuilder jobBuilder = jobBuilderFactory.get("APPLICATION_JOB");
        jobBuilder.incrementer(new RunIdIncrementer());
        FlowJobBuilder flowJobBuilder = jobBuilder.flow(getFirstStep()).end();
        Job job = flowJobBuilder.build();
        return job;
    }

    @Bean
    public Step getFirstStep() {
        StepBuilder stepBuilder = stepBuilderFactory.get("getFirstStep");
        SimpleStepBuilder<Application, Application> simpleStepBuilder = stepBuilder.chunk(1);
        return simpleStepBuilder.reader(jsonItemReader()).processor(processor()).writer(writer()).build();
    }
}