package io.junq.examples.usercenter.spring;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;

@Configuration
@ComponentScan({ "io.junq.examples.usercenter.web", "io.junq.examples.common.web" })
@EnableWebMvc
public class UserCenterWebConfiguration extends WebMvcConfigurerAdapter {

	public UserCenterWebConfiguration() {
		super();
	}
	
	public void extendMessageConverters(final List<HttpMessageConverter<?>> converters) {
		final Optional<HttpMessageConverter<?>> converterFound = converters.stream().
				filter(c -> c instanceof AbstractJackson2HttpMessageConverter).findFirst();
		if (converterFound.isPresent()) {
			final AbstractJackson2HttpMessageConverter converter = (AbstractJackson2HttpMessageConverter) converterFound.get();
			converter.getObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
			converter.getObjectMapper().enable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		}
	}
	
}
