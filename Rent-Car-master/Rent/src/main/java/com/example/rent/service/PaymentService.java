package com.example.rent.service;

import com.example.rent.model.Product;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentService {

    public boolean processPayment(Optional<Product> product, String paymentDetails) {
        // Mock payment logic: Always returns true for simplicity
        System.out.println("Processing payment for product: " + product.get());
        System.out.println("Payment details: " + paymentDetails);
        return true;
    }
}


