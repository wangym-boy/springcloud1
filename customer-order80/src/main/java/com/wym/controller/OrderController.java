package com.wym.controller;

import com.wym.lb.LoadBalance;
import com.wym.lb.MyLB;
import com.wym.pojo.CommonResult;
import com.wym.pojo.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

@RestController
@Slf4j
public class OrderController {
    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private LoadBalance loadBalance;

    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping("/constomer/payment/create")
    public CommonResult<Payment> create(Payment payment){
        log.info("=======================消费者往数据库插入："+payment);
        return restTemplate.postForObject(PAYMENT_URL+"/payment/create",payment,CommonResult.class);
    }

    @GetMapping("/constomer/payment/getPaymentById/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id){
        log.info("=======================消费者查询的ID："+id);
        return restTemplate.getForObject(PAYMENT_URL+"/payment/getPaymentById/"+id,CommonResult.class);
    }

    @GetMapping("/constomer/payment/lb")
    public String paymentLB(){
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if (null == serviceInstances || serviceInstances.size() <= 0){
            return null;
        }
        ServiceInstance serviceInstance = loadBalance.instance(serviceInstances);
        URI uri = serviceInstance.getUri();
        return restTemplate.getForObject(uri+"payment/lb",String.class);
    }
}
