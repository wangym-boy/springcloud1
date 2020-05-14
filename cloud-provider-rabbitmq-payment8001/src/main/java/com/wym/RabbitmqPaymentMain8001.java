package com.wym;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class RabbitmqPaymentMain8001 {
    public static void main(String[] args) {
        SpringApplication.run(RabbitmqPaymentMain8001.class,args);
    }
}
