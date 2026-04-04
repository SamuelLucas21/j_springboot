package com.dataj.j_demo.config;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class InternationConfig{

    @Bean
    public 
    ReloadableResourceBundleMessageSource
     messageSource(){
        ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource = new ReloadableResourceBundleMessageSource();
        
        reloadableResourceBundleMessageSource
        .setBasename("classpath:message"); 
        reloadableResourceBundleMessageSource
        .setDefaultEncoding("ISO-8859-1");
        reloadableResourceBundleMessageSource.setDefaultLocale(Locale.getDefault());

        return reloadableResourceBundleMessageSource;
    }


    @Bean
    public LocalValidatorFactoryBean validatorFactoryBean(){
        LocalValidatorFactoryBean local = new LocalValidatorFactoryBean();
        local.setValidationMessageSource(this.messageSource());
        
        return local;
    }

}