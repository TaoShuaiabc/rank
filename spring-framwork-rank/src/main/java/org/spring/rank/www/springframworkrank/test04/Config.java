package org.spring.rank.www.springframworkrank.test04;

import org.spring.rank.www.springframworkrank.test03.spring.ComponentScan;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;

@ComponentScan("org.spring.rank")
public class Config {

    @Bean
    public MessageSource messageSource(){
        ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();
        resourceBundleMessageSource.setBasename("message");
        return resourceBundleMessageSource;
    }
}
