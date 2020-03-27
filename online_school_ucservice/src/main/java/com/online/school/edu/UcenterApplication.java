package com.online.school.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class UcenterApplication {

    public static void main(String[] args){
        SpringApplication.run(UcenterApplication.class,args);
    }
}