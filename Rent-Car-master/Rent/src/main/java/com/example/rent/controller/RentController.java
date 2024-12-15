package com.example.rent.controller;

import com.example.rent.model.Product;
import com.example.rent.service.PaymentService;
import com.example.rent.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/rent")
public class RentController {

    @Autowired
    private ProductService productService; // Service to fetch product details

    @Autowired
    private PaymentService paymentService; // Service to handle payments

    // Step 1: Show Rent Page with Product Details
    @GetMapping("/{productId}")
    public String showRentPage(@PathVariable Long productId, Model model) {
        Optional<Product> product = productService.getProductById(productId);
        model.addAttribute("product", product);
        return "rent-page"; // Render rent-page.html
    }

    // Step 2: Process Payment
    @PostMapping("/{productId}/pay")
    public String processPayment(@PathVariable Long productId, @RequestParam("paymentDetails") String paymentDetails, Model model) {
        Optional<Product> product = productService.getProductById(productId);

        // Process payment (mocked for simplicity)
        boolean paymentSuccess = paymentService.processPayment(product, paymentDetails);

        if (paymentSuccess) {
            model.addAttribute("message", "Payment successful! Enjoy your rental.");
            return "success-page"; // Render success page
        } else {
            model.addAttribute("message", "Payment failed. Please try again.");
            return "error-page"; // Render error page
        }
    }
}

