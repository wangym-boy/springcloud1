package com.wym.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
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
//@DefaultProperties(defaultFallback = "gloabalFallBack")
public class OrderOpenFeignController {
    @Resource
    private PaymentFeignService paymentFeignService;


    @GetMapping("/constomer/payment/info_ok/{id}")
    public CommonResult<String> getPaymentById(@PathVariable("id") Integer id){
        return paymentFeignService.getPayment(id);
    }
    @GetMapping("/constomer/payment/timeout/{id}")
//    @HystrixCommand(fallbackMethod = "timeoutHandler",commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "1500")
//    })
//    @HystrixCommand
    public CommonResult<String> timeOut(@PathVariable("id") Integer id){
        return paymentFeignService.timeOut(id);
    }

//    public CommonResult<String> timeoutHandler(@PathVariable("id") Integer id){
//        return new CommonResult<String>(500,"服务器出差了！","支付系统服务器繁忙，请等待10秒钟后再试或自己运行出错，请检查！/(ㄒoㄒ)/~~");
//    }
//
//    //下面是全局fallback
//    public CommonResult<String> gloabalFallBack(){
//        return new CommonResult<String>(500,"服务器出差了！","系统出现异常处理信息，请稍后再试！");
//    }
}
