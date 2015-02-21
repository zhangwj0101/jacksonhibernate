package ch.rasc.jacksonhibernate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.converter.json.SpringHandlerInstantiator;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.ObjectIdResolver;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.Annotated;

@SpringBootApplication
public class Start {

	@Bean
	public ObjectMapper objectMapper(ApplicationContext ctx) {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

		objectMapper.setHandlerInstantiator(new SpringHandlerInstantiator(ctx
				.getAutowireCapableBeanFactory()) {
			@Override
			public ObjectIdResolver resolverIdGeneratorInstance(MapperConfig<?> config,
					Annotated annotated, Class<?> implClass) {
				if (AnnotationUtils.findAnnotation(implClass, Component.class) != null) {
					return (ObjectIdResolver) ctx.getAutowireCapableBeanFactory()
							.createBean(implClass);
				}
				return null;
			}

		});

		return objectMapper;
	}

	// @Bean
	// public Hibernate4Module Hibernate4Module() {
	// Hibernate4Module hibernate4Module = new Hibernate4Module();
	// hibernate4Module.enable(Hibernate4Module.Feature.FORCE_LAZY_LOADING);
	// // hibernate4Module.disable(Hibernate4Module.Feature.FORCE_LAZY_LOADING);
	// return hibernate4Module;
	// }

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Start.class, args);
	}
}
