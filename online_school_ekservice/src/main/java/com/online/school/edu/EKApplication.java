package com.online.school.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EKApplication {
    public static void main(String[] args) {
        SpringApplication.run(EKApplication.class,args);
    }
}
