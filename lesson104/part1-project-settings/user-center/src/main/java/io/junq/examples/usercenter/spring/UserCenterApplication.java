package io.junq.examples.usercenter.spring;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.DispatcherServlet;

@SpringBootApplication
@Import({
	UserCenterContextConfiguration.class,
	UserCenterPersistenceJpaConfiguration.class,
	UserCenterServiceConfiguration.class,
	UserCenterWebConfiguration.class
})
public class UserCenterApplication extends SpringBootServletInitializer {

	@Bean
	public DispatcherServlet dispatcherServlet() {
		return new DispatcherServlet();
	}
	
	@Bean
	public ServletRegistrationBean dispatcherServletRegistration() {
		final ServletRegistrationBean registration = new ServletRegistrationBean(dispatcherServlet(), "/api/*");

		final Map<String, String> params = new HashMap<String, String>();
		params.put("contextClass", "org.springframework.web.context.support.AnnotationConfigWebApplicationContext");
		params.put("contextConfigLocation", "org.spring.sec2.spring");
		params.put("dispatchOptionsRequest", "true");
		
		registration.setInitParameters(params);
		registration.setLoadOnStartup(1);
		
		return registration;
	}
	
	protected SpringApplicationBuilder configure(final SpringApplicationBuilder builder) {
		return builder.sources(UserCenterApplication.class);
	}
	
	public static void main(final String[] args) {
		SpringApplication.run(UserCenterApplication.class, args);
	}
	
}
