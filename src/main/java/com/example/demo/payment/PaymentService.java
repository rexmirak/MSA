package com.example.demo.payment;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.*;
@Service
@Component

public class PaymentService {
    @Value("${stripe.secretKey}")
    private String stripeSecretKey;

    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }


    public List<Payment> getAllPayments() {

        System.out.println("All Payments");
        return paymentRepository.findAll();
    }

    public Optional<Payment> getPaymentById(Long id) {
        System.out.println("Payment by ID");

        return paymentRepository.findById(id);
    }

    public ResponseEntity<String> createPayment(Payment payment) throws StripeException {
        System.out.println("Creating new payment");
        // Set your secret key. Remember to switch to your live secret key in production!
        Stripe.apiKey = stripeSecretKey;
        // Create a charge object

        payment.setPaymentDate(LocalDateTime.now());
        Map<String, Object> params = new HashMap<>();
        params.put("amount", (int) (payment.getAmount() * 100)); // Stripe uses cents
        params.put("currency", "usd");
        //params.put("source", payment.getStripeToken()); // Token representing the payment source

        paymentRepository.save(payment);
        PaymentIntent pay = PaymentIntent.create(params);
        String paymentStr = pay.toJson();

        return new ResponseEntity<>(paymentStr, HttpStatus.OK);
    }

    public void deletePayment(Long id) {
        System.out.println("delete a payment");

        paymentRepository.deleteById(id);
    }

    public void deleteAllPayment() {
        System.out.println("delete all Payments");

        paymentRepository.deleteAll();
    }

    public Payment updatePayment(Long id, Payment paymentDetails) {
        System.out.println("update a payment");
        Payment payment = paymentRepository.findById(id).orElseThrow(() -> new RuntimeException("Payment not found"));
        payment.setUserId(paymentDetails.getUserId());
        payment.setCampaignId(paymentDetails.getCampaignId());
        payment.setAmount(paymentDetails.getAmount());
        return paymentRepository.save(payment);
    }

    public ResponseEntity<String> stripePayment(Long id) throws Exception {
        System.out.println("Stripe Payment");
        Payment payment = paymentRepository.findById(id).orElseThrow(() -> new RuntimeException("Payment not found"));;

        if (payment == null) {
            throw new Exception("Payment information is missing");
        }
        payment.setAmount(00.00);
        paymentRepository.save(payment);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}











