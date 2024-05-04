package dev.umang.PaymentService04May.service;

import com.razorpay.RazorpayException;
import org.springframework.stereotype.Service;

public interface PaymentService {
    String doPayment(String email,
                     String phoneNo,
                     Long amount,
                     String orderId) throws RazorpayException;
}
