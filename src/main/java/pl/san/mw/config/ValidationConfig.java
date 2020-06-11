package pl.san.mw.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.SpringConstraintValidatorFactory;

@Configuration
public class ValidationConfig {

    @Autowired
    private AutowireCapableBeanFactory autowireCapableBeanFactory;

    @Bean
    public SpringConstraintValidatorFactory springConstraintValidatorFactory(){

        return new SpringConstraintValidatorFactory(autowireCapableBeanFactory);

    }



}
