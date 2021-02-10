package com.ups.tx.microservice.batch;



import java.util.List;

import javax.sql.DataSource;

import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.context.annotation.Bean;

import com.ups.tx.microservice.model.Person;

/*
 * public class BatchItemWriter implements ItemWriter<String> {
 * 
 * //@Override public void write(List<? extends String> messages) throws
 * Exception { for (String msg : messages) {
 * System.out.println("Writing the data using batch writer: " + msg); } }
 * 
 * }
 */

public class BatchItemWriter extends JdbcBatchItemWriter<Person> {
	public JdbcBatchItemWriter<Person> writer(DataSource dataSource) {
		return new JdbcBatchItemWriterBuilder<Person>()
				.itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
				.sql("INSERT INTO people (first_name, last_name) VALUES (:firstName, :lastName)").dataSource(dataSource)
				.build();
	}
}
