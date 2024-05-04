package dev.umang.PaymentService04May.service;

import org.springframework.stereotype.Service;

@Service("stripe")
public class StripePaymentService implements PaymentService{
    @Override
    public String doPayment(String email, String phoneNo, Long amount, String orderId) {
        return null;
    }
}
