package com.example.rent.service;

import com.example.rent.model.Product;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    public boolean processPayment(Product product, String paymentDetails) {
        // Mock payment success for simplicity
        System.out.println("Processing payment for: " + product.getName());
        System.out.println("Payment details: " + paymentDetails);
        return true; // Always return success
    }
}
