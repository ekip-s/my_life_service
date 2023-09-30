package com.life.person;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;


@SpringBootApplication
@EntityScan("com.life.model")
public class PersonMaster {
    public static void main(String[] args) {
        SpringApplication.run(PersonMaster.class, args);
    }
}