package com.kaisik.openschoolstarter.config;

import com.kaisik.openschoolstarter.service.impl.HttpRequestHandler;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(StarterProperties.class)
public class OpenSchoolStarterConfig {

}
