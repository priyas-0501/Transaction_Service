package com.ups.tx.microservice.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.ups.tx.microservice.model.Person;

@Component
public class BatchJobCompletionListener extends JobExecutionListenerSupport {

	private static final Logger log = LoggerFactory.getLogger(BatchJobCompletionListener.class);

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public BatchJobCompletionListener(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
			log.info("!!! JOB FINISHED! Time to verify the results");

			jdbcTemplate.query("SELECT first_name, last_name FROM people",
				(rs, row) -> new Person(
					rs.getString(1),
					rs.getString(2))
			).forEach(person -> log.info("Found <" + person + "> in the database."));
		}
	}
}
