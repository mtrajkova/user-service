package com.bachelor.microservice2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class Microservice2Application {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Microservice2Application.class, args);
    }

}
