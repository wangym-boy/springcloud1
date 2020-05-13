package com.wym.controller;

import com.wym.pojo.CommonResult;
import com.wym.pojo.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderController {
    public static final String PAYMENT_URL = "http://cloud-payment-service";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/constomer/payment/zk")
    public String getzk(){
        String result = restTemplate.getForObject(PAYMENT_URL + "/payment/zk", String.class);
        return result;
    }

}
