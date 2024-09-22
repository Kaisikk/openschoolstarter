package com.kaisik.openschoolstarter.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "starter-openschool")
public class StarterProperties {

    private String author;

}
