package com.wym.controller;

import com.wym.pojo.CommonResult;
import com.wym.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentHystrixController {

    @Resource
    private PaymentService paymentService;

    @GetMapping("/payment/info_ok/{id}")
    public CommonResult<String> paymentInfoOK(@PathVariable("id") Integer id){
        String result = paymentService.paymentInfo_OK(id);
        CommonResult<String> commonResult = new CommonResult<String>(200,"成功！",result);
        if (null != result){
            return commonResult;
        }else {
            commonResult.setData(null);
            return commonResult;
        }
    }

    @GetMapping("/payment/timeout/{id}")
    public CommonResult<String> paymentTimeOut(@PathVariable("id") Integer id){
        CommonResult<String> commonResult = paymentService.timeout(id);
        if (null != commonResult){
            return commonResult;
        }else {
            commonResult.setData(null);
            return commonResult;
        }
    }
    @GetMapping("/payment/circuit/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        String result = paymentService.paymentCircuitBreaker(id);
        return result;
    }
}
