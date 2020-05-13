package com.wym.service;

import com.wym.pojo.Payment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PaymentService {
    public int create(Payment payment);

    public Payment getPaymentById(@Param("id") Long id);

    public List<Payment> queryPayment();
}
