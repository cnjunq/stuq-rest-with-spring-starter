package io.junq.examples.usercenter.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan({ "io.junq.examples.usercenter.web" })
@EnableWebMvc
public class UserCenterWebConfiguration {

	public UserCenterWebConfiguration() {
		super();
	}
	
}
