package dev.umang.PaymentService04May.controller;

import com.razorpay.RazorpayException;
import dev.umang.PaymentService04May.dto.InitiatePaymentRequest;
import dev.umang.PaymentService04May.service.PaymentService;
import dev.umang.PaymentService04May.service.RazorPayPaymentService;
import dev.umang.PaymentService04May.service.StripePaymentService;
import dev.umang.PaymentService04May.strategy.PaymentGateWaySelectionStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class PaymentController {
    private PaymentService razorPayPaymentService;
    private PaymentService stripePaymentService;
    private PaymentGateWaySelectionStrategy paymentGateWaySelectionStrategy;

    public PaymentController(
            @Qualifier("razorpay") PaymentService razorPaymentService,
            @Qualifier("stripe") PaymentService stripePaymentService,
            PaymentGateWaySelectionStrategy paymentGateWaySelectionStrategy){
        this.razorPayPaymentService = razorPaymentService;
        this.stripePaymentService = stripePaymentService;
        this.paymentGateWaySelectionStrategy = paymentGateWaySelectionStrategy;
    }
    @PostMapping("/payment")
    public String initiatePayment(@RequestBody InitiatePaymentRequest requestDto) throws RazorpayException {
        //which payment gateway to use
        int paymentGateWayOption = choosePaymentGateWay();
        switch (paymentGateWayOption){
            case 1 :
                return razorPayPaymentService.doPayment(requestDto.getEmail(),
                        requestDto.getPhoneNo(),
                        requestDto.getAmount(),
                        requestDto.getOrderId());
            case 2:
                return stripePaymentService.doPayment(requestDto.getEmail(),
                        requestDto.getPhoneNo(),
                        requestDto.getAmount(),
                        requestDto.getOrderId());
        }
        return null;
    }

    private int choosePaymentGateWay(){
        return paymentGateWaySelectionStrategy.choosePaymentGatWay();
    }
}

/*
Razorpay - 1.7% - but you'll have atleast 1k transactions
Stripe - 1.8%

Out of 10 transactions, 7 to razorpay
3 to Stripe

 */