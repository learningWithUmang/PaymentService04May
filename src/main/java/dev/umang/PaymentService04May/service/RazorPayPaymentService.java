package dev.umang.PaymentService04May.service;

import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service("razorpay")
public class RazorPayPaymentService implements PaymentService{

    private RazorpayClient razorpayClient;
    public RazorPayPaymentService(RazorpayClient razorpayClient){
        this.razorpayClient = razorpayClient;
    }
    @Override
    public String doPayment(String email, String phoneNo, Long amount, String orderId) throws RazorpayException {
        try {
            JSONObject paymentLinkRequest = new JSONObject();
            paymentLinkRequest.put("amount", amount);
            paymentLinkRequest.put("currency", "INR");
            paymentLinkRequest.put("receipt", orderId);

            JSONObject customer = new JSONObject();
            customer.put("email", email);
            customer.put("phone", phoneNo);
            paymentLinkRequest.put("customer", customer);

            JSONObject notify = new JSONObject();
            notify.put("sms", true);
            notify.put("email", true);
            paymentLinkRequest.put("notify", notify);

            paymentLinkRequest.put("callback_url", "https://xyz.com/razorWebHook");
            paymentLinkRequest.put("callback_method", "post");

            PaymentLink response = razorpayClient.paymentLink.create(paymentLinkRequest);
            return response.toString();
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
