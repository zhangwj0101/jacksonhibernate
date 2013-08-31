package ch.rasc.jacksonhibernate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Start {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(Start.class, args);
	}
}
