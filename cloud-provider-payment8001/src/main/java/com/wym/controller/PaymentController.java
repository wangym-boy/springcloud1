package com.wym.controller;

import com.wym.pojo.CommonResult;
import com.wym.pojo.Payment;
import com.wym.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PaymentController {
    @Autowired
    private PaymentService paymentService;
    @Value("${server.port}")
    private String serverPort;
    @Resource
    private org.springframework.cloud.client.discovery.DiscoveryClient discoveryClient;

    @PostMapping("/payment/create")
    public CommonResult<Payment> create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        log.info("*******插入结果："+result);
        if (result > 0){
            return new CommonResult(200,"插入数据库成功！，端口号："+serverPort,result);
        }else {
            return new CommonResult(500,"插入数据库失败！",null);
        }
    }
    @GetMapping("/payment/getPaymentById/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("*******插入结果："+payment+"我重新配置了热部署！");
        if (null != payment){
            return new CommonResult(200,"查询数据成功！，端口号："+serverPort,payment);
        }else {
            return new CommonResult(404,"无对应记录，查询ID："+id,null);
        }
    }

    @GetMapping(value = "/payment/discover")
    public CommonResult<DiscoveryClient> getDiscover(){
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("======="+service);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info("-------"+instance.getServiceId()+"\t"+instance.getHost()+"\t"+instance.getPort()+"\t"+instance.getUri());
        }
        return new CommonResult<DiscoveryClient>(200,"查询到Discovery",discoveryClient);
    }

    @GetMapping("/payment/lb")
    public String paymentLB(){
        return serverPort;
    }

    @GetMapping("/payment/timeout")
    public String timeOut(){
        try{
            TimeUnit.SECONDS.sleep(3);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return serverPort;
    }
}
