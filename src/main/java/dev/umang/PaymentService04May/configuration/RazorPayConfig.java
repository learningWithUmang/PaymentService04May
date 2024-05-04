package dev.umang.PaymentService04May.configuration;

import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RazorPayConfig {
    @Value("${razorpay.key.id}")
    private String razorPayKeyId;
    @Value("${razorpay.key.secret}")
    private String razorPayKeySecret;

    @Bean
    public RazorpayClient createRazorPayClient() throws RazorpayException {
        return new RazorpayClient(razorPayKeyId, razorPayKeySecret);
    } //similar to how we were creating a rest template
}
