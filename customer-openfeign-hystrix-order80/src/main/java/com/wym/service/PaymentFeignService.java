package com.wym.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.wym.pojo.CommonResult;
import com.wym.pojo.Payment;
import com.wym.service.impl.PaymentFeignServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-HYSTRIX-PAYMENT-SERVICE",fallback = PaymentFeignServiceImpl.class)
public interface PaymentFeignService {
    @GetMapping("/payment/info_ok/{id}")
    CommonResult<String> getPayment(@PathVariable("id") Integer id);

    @GetMapping("/payment/timeout/{id}")
    CommonResult<String> timeOut(@PathVariable("id") Integer id);
}
