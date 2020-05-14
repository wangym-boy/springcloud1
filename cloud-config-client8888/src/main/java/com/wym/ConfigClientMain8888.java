package com.wym;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ConfigClientMain8888 {
    public static void main(String[] args) {
        SpringApplication.run(ConfigClientMain8888.class,args);
    }
}
