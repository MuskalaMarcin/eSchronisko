package com.eschronisko.config;

import com.eschronisko.Application;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * Class for e-Schronisko web application configuration.
 *
 * @author Marcin Muskala
 * @since 07.11.2016
 */
@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan(basePackageClasses = Application.class)
public class ApplicationConfig {
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
