package com.example.beday6;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BeDay6Application {

    public static void main(String[] args) {
        SpringApplication.run(BeDay6Application.class, args);
    }

}
