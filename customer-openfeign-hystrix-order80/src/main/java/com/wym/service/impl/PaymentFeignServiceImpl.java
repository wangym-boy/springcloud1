package com.wym.service.impl;

import com.wym.pojo.CommonResult;
import com.wym.service.PaymentFeignService;
import org.springframework.stereotype.Component;

@Component
public class PaymentFeignServiceImpl implements PaymentFeignService {
    @Override
    public CommonResult<String> getPayment(Integer id) {
        return new CommonResult<String>(500,"payment-ok！","系统出现异常处理信息，请稍后再试！");
    }

    @Override
    public CommonResult<String> timeOut(Integer id) {
        return new CommonResult<String>(500,"time-out！","系统出现异常处理信息，请稍后再试！");
    }
}
