package com.ups.tx.microservice.batch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.core.io.ClassPathResource;

import com.ups.tx.microservice.model.Person;
/*
 * public class BatchItemReader implements ItemReader<String> {
 * 
 * private static final Logger log =
 * LoggerFactory.getLogger(BatchItemReader.class); private String[] messages = {
 * "Hello World", "Welcome to Spring Batch using Spring boot Example",
 * "H2 Database has been used in this example" }; private int count = 0;
 * 
 * @Override public String read() throws Exception, UnexpectedInputException,
 * ParseException, NonTransientResourceException { // TODO Auto-generated method
 * stub if (count < messages.length) { return messages[count++]; } else { count
 * = 0; } return null; }
 * 
 * 
 * 
 * }
 */

public class BatchItemReader extends FlatFileItemReader<Person> {
	 public FlatFileItemReader<Person> reader() {
		return new FlatFileItemReaderBuilder<Person>().name("batchItemReader")
				.resource(new ClassPathResource("sample-data.csv")).delimited()
				.names(new String[] { "firstName", "lastName" })
				.fieldSetMapper(new BeanWrapperFieldSetMapper<Person>() {
					{
						setTargetType(Person.class);
					}
				}).build();
	}
}