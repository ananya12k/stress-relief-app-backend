package com.example.stressreliefappbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;
@SpringBootApplication
//@ComponentScan(basePackages = {"com.example.jwt_auth"})
public class StressReliefAppBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(StressReliefAppBackendApplication.class, args);
    }

}
