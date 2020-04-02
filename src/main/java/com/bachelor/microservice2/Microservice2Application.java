package com.bachelor.microservice2;

import com.bachelor.microservice2.client.ConsumerControllerClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Microservice2Application {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Microservice2Application.class, args);
        ConsumerControllerClient consumerControllerClient = context.getBean(ConsumerControllerClient.class);
        System.out.println(consumerControllerClient);
        consumerControllerClient.getEmployee();
    }

    @Bean
    public ConsumerControllerClient consumerControllerClient() {
        return new ConsumerControllerClient();
    }

}
