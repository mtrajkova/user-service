package com.bachelor.microservice2.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
public class ConsumerControllerClient {

    @Autowired
    private DiscoveryClient discoveryClient;

    public void getEmployee() {
        List<ServiceInstance> instances = discoveryClient.getInstances("employee-service");
        ServiceInstance serviceInstance = instances.get(0);

        String baseUrl = serviceInstance.getUri().toString();
        baseUrl += "/employee/message";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = null;

        try {
            response = restTemplate.exchange(baseUrl, HttpMethod.GET, getHeaders(), String.class);
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }

        System.out.println(response.getBody());
    }

    private static HttpEntity<?> getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.set("first-request", "first-request-value");
        return new HttpEntity<>(headers);
    }
}
