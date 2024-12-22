package com.example.rent.service;

import com.example.rent.model.Product;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class RentService {

    public BigDecimal calculateTotalAmount(Product product, int days) {
        return product.getPrice().multiply(BigDecimal.valueOf(days)); // Use BigDecimal for precision
    }
}
