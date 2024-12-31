package com.example.paymentservice.Services;

import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    PaymentGateway paymentGateway;

    public PaymentService(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    public String InitiatePayment(){
        return paymentGateway.generatePaymentLink();
    }
}
