package dev.umang.PaymentService04May.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InitiatePaymentRequest {
    private String email;
    private String phoneNo;
    private Long amount;
    private String orderId;
}
