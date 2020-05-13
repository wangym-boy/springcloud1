package com.wym.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MyLB implements LoadBalance{
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public final int getAndIncrement(){
        int curr ;
        int next ;
        do {
            curr = this.atomicInteger.get();
            next = curr >= 2147483647 ? 0 : curr + 1;
        }while (!this.atomicInteger.compareAndSet(curr,next));
        System.out.println("=============下一次请求次数："+next);
        return next;
    }

    @Override
    public ServiceInstance instance(List<ServiceInstance> serviceInstances) {
        int index = getAndIncrement() % serviceInstances.size();

        return serviceInstances.get(index);
    }
}
