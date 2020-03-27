package com.online.school.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@EnableEurekaClient
@SpringBootApplication
public class StatisticApplication {

    public static void main(String[] args){
        SpringApplication.run(StatisticApplication.class,args);
    }
}
