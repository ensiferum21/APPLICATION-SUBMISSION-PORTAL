package com.cognixia.batch;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.cognixia.model.Application;

@Configuration
@EnableBatchProcessing
public class AppProcessBatchConfig {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	// end::setup[]

	// tag::readerwriterprocessor[]
	@Bean
	public FlatFileItemReader<Application> reader() {
		return new FlatFileItemReaderBuilder<Application>()
			.name("applicationItemReader")
			.resource(new ClassPathResource("C:\\Users\\Kevin\\OneDrive\\Documents\\GitHub\\APPLICATION-SUBMISSION-PORTAL\\applicant-service\\applicationJSON\\application.json"))
			.delimited()
			.names(new String[]{"applicationid", "app_status", "app_submission_date", "country_of_birth", "covid_vacc_status", "dob", "name", "race"})
			.fieldSetMapper(new BeanWrapperFieldSetMapper<Application>() {{
				setTargetType(Application.class);
			}})
			.build();
	}

	@Bean
	public ApplicationProcessor processor() {
		return new ApplicationProcessor();
	}

	@Bean
	public JdbcBatchItemWriter<Application> writer(DataSource dataSource) {
		return new JdbcBatchItemWriterBuilder<Application>()
			.itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
			.sql("INSERT INTO application_db.app_processed (applicationid, app_status, app_submission_date, country_of_birth, covid_vacc_status, dob, name, race) VALUES (:applicationid, :app_status, :app_submission_date, :country_of_birth, :covid_vacc_status, :dob, :name, :race")
			.dataSource(dataSource)
			.build();
	}
	// end::readerwriterprocessor[]

	// tag::jobstep[]
	@Bean
	public Job importUserJob(JobCompletionNotificationListener listener, Step step1) {
		return jobBuilderFactory.get("importUserJob")
			.incrementer(new RunIdIncrementer())
			.listener(listener)
			.flow(step1)
			.end()
			.build();
	}

	@Bean
	public Step step1(JdbcBatchItemWriter<Application> writer) {
		return stepBuilderFactory.get("step1")
			.<Application, Application> chunk(10)
			.reader(reader())
			.processor(processor())
			.writer(writer)
			.build();
	}
	// end::jobstep[]
}