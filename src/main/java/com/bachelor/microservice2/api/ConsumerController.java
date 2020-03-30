package com.bachelor.microservice2.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    @GetMapping("/message")
    public String test(@RequestHeader("second-request") String header) {
        System.out.println(header);
        return "Hello microservice 2***";
    }
}