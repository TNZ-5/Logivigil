package com.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class LogVigilApplication {

    public static void main(String[] args) {
        SpringApplication.run(LogVigilApplication.class, args);
    }
}
