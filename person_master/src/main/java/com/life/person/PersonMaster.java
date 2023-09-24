package com.life.person;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"com.life"})
@EntityScan("com.life.model")
@EnableJpaRepositories("com.life.repository")
public class PersonMaster {
    public static void main(String[] args) {
        SpringApplication.run(PersonMaster.class, args);
    }
}