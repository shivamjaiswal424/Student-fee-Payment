package com.example.esd_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class EsdDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(EsdDemoApplication.class, args);
    }

}
