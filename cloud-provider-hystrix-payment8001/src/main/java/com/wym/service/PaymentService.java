package com.wym.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.wym.pojo.CommonResult;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {
    /**
     * 服务降级
     * @param id
     * @return
     */
    public String paymentInfo_OK(Integer id){
        return "线程池："+Thread.currentThread().getName() + "\t" + "paymentInfo_OK,id："+id+"O(∩_∩)O哈哈";
    }

//    @HystrixCommand(fallbackMethod = "timeoutHandler",commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")
//    })
    public CommonResult<String> timeout(Integer id){
        int time = 0;
       try{
           TimeUnit.SECONDS.sleep(time);
       }catch (InterruptedException e){
           e.printStackTrace();
       }
       String data = "线程池："+Thread.currentThread().getName() + "\t" + "paymentInfo_TimeOut,id："+id+"O(∩_∩)O哈哈"+"\t耗时"+time+"秒钟~";
       return new CommonResult<>(200,"成功！",data);
    }

    public CommonResult<String> timeoutHandler(Integer id){
        String data = "线程池："+Thread.currentThread().getName() + "\t" + "paymentInfo_TimeoutHandler,id："+id+"/(ㄒoㄒ)/~~超时了"+"\t";
        return new CommonResult<String>(500,"超时！",data);
    }

    /**
     * 服务熔断
     *
     */
    @HystrixCommand(fallbackMethod = "paymentCircuitBreakerFallBack",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),//是否开启服务熔断
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),//请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),//时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60")//失败率达到多少跳闸
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        if (id < 0){
            throw new RuntimeException("*******id不能为负数！");
        }
        String serial = IdUtil.simpleUUID();
        return Thread.currentThread().getName()+"\t"+"调用成功，流水号："+serial;
    }

    public String paymentCircuitBreakerFallBack(@PathVariable("id") Integer id){
        return "id 不能为负数，请稍后尝试！~~~///(^v^)///~~~ , id："+id;
    }
}
