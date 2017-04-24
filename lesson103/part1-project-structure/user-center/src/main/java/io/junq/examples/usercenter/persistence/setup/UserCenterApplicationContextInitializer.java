package io.junq.examples.usercenter.persistence.setup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

public class UserCenterApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    public UserCenterApplicationContextInitializer() {
        super();
    }

    //

    /**
     * 设置当前激活的profile
     */
    @Override
    public void initialize(final ConfigurableApplicationContext applicationContext) {
        final ConfigurableEnvironment environment = applicationContext.getEnvironment();
        final String activeProfiles = environment.getProperty("spring.profiles.active");
        
        LOGGER.info("The active profiles are: {}", activeProfiles);

        if (activeProfiles != null) {
            environment.setActiveProfiles(activeProfiles.split(","));
        }
    }

}
