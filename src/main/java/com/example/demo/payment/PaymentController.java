package com.example.demo.payment;
import com.stripe.Stripe;
import com.stripe.model.Charge;
import com.stripe.exception.StripeException;

import com.stripe.model.PaymentIntent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping(path = "api/v1/payment")
public class PaymentController {

    @Value("${stripe.secretKey}")
    private String stripeSecretKey;

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(final PaymentService paymentService) {
        this.paymentService = paymentService;
    }

//    @GetMapping
//    public List<Payment> getPayments(){
//        return paymentService.getPayments();
//    }

    @GetMapping
    public List<Payment> getAllPayments() {
        return paymentService.getAllPayments();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable Long id) {
        Payment payment = paymentService.getPaymentById(id).orElseThrow(() -> new RuntimeException("Payment not found"));
        return ResponseEntity.ok(payment);
    }

//    @PostMapping
//    public Payment createPayment(@RequestBody Payment payment) {
//        return paymentService.createPayment(payment);
//    }

    @PostMapping
    public ResponseEntity<String> createPayment(@RequestBody Payment payment) throws StripeException{
        return paymentService.createPayment(payment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Payment> updatePayment(@PathVariable Long id, @RequestBody Payment paymentDetails) {
        Payment updatedPayment = paymentService.updatePayment(id, paymentDetails);
        return ResponseEntity.ok(updatedPayment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deletePayment(@PathVariable Long id) {
        paymentService.deletePayment(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Payment with ID " + id + " deleted successfully");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/all")
    public void deleteAllPayment() {
        paymentService.deleteAllPayment();
    }
}
