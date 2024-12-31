package com.example.paymentservice.Controller;

import com.example.paymentservice.Services.PaymentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    /* Dependency ingestion of payment service */
    PaymentService paymentService;

    /* Contructor */
    PaymentController(PaymentService paymentService){
        this.paymentService = paymentService;
    }

    /* Payment API */
    @PostMapping("/payment")
    private String initiatePayment(){
        return paymentService.InitiatePayment();
    }
}
