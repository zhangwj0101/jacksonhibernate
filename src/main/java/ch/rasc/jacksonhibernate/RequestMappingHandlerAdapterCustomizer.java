package ch.rasc.jacksonhibernate;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;

@Component
public class RequestMappingHandlerAdapterCustomizer implements BeanPostProcessor {

	@Bean
	ObjectMapper objectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

		Hibernate4Module hibernate4Module = new Hibernate4Module();
		hibernate4Module.enable(Hibernate4Module.Feature.FORCE_LAZY_LOADING);
		//hibernate4Module.disable(Hibernate4Module.Feature.FORCE_LAZY_LOADING);
		objectMapper.registerModule(hibernate4Module);
		return objectMapper;
	}
	
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if (bean instanceof RequestMappingHandlerAdapter) {
			RequestMappingHandlerAdapter adapter = (RequestMappingHandlerAdapter) bean;

			for (HttpMessageConverter<?> converter : adapter.getMessageConverters()) {
				if (converter instanceof MappingJackson2HttpMessageConverter) {
					((MappingJackson2HttpMessageConverter)converter).setObjectMapper(objectMapper());
				}
			}			
		}
		return bean;
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

}