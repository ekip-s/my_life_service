package com.life.courses;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.life.client", "com.life.courses"})
@EntityScan("com.life.model")
public class CoursesMaster {
    public static void main(String[] args) {
        SpringApplication.run(CoursesMaster.class, args);
    }
}