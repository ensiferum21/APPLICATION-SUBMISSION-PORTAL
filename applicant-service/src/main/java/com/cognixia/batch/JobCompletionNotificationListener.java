package com.cognixia.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.cognixia.model.Application;

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

	private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public JobCompletionNotificationListener(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

//	@Override
//	public void afterJob(JobExecution jobExecution) {
//		if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
//			log.info("!!! JOB FINISHED! Time to verify the results");
//
//			jdbcTemplate.query("SELECT series_reference,period,data_value,suppressed,status,units,magnitude,subject,group_name,series_title_1,series_title_2,series_title_3,series_title_4,series_title_5 FROM electronic_records",
//				(rs, row) -> new Application(
//					rs.getString(1),
//					rs.getString(2),
//					rs.getString(3),
//					rs.getString(4),
//					rs.getString(5),
//					rs.getString(6),
//					rs.getString(7),
//					rs.getString(8),
//					rs.getString(9),
//					rs.getString(10),
//					rs.getString(11),
//					rs.getString(12),
//					rs.getString(13),
//					rs.getString(14))
//			).forEach(record -> log.info("Found <" + record + "> in the database."));
//		}
//	}
}