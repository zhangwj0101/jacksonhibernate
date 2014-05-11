package ch.rasc.jacksonhibernate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class Start {

	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
		return objectMapper;
	}

	@Bean
	public Hibernate4Module Hibernate4Module() {
		Hibernate4Module hibernate4Module = new Hibernate4Module();
		hibernate4Module.enable(Hibernate4Module.Feature.FORCE_LAZY_LOADING);
		// hibernate4Module.disable(Hibernate4Module.Feature.FORCE_LAZY_LOADING);
		return hibernate4Module;
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Start.class, args);
	}
}
