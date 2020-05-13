package com.wym.controller;

import com.wym.pojo.CommonResult;
import com.wym.pojo.Payment;
import com.wym.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderOpenFeignController {
    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping("/constomer/payment/getPaymentById/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        return paymentFeignService.getPayment(id);
    }
    @GetMapping("/constomer/payment/timeout")
    public String timeOut(){
        return paymentFeignService.timeOut();
    }
}
