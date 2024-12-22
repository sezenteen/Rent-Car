package com.example.rent.controller;

import com.example.rent.model.Category;
import com.example.rent.model.Product;
import com.example.rent.service.CategoryService;
import com.example.rent.service.PaymentService;
import com.example.rent.service.ProductService;
import com.example.rent.service.RentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Optional;

@Controller
class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;
    private PaymentService paymentService;
    private RentService rentService;

    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("/products")
    public ModelAndView listProducts() {
        ModelAndView modelAndView = new ModelAndView("/product/products");
        modelAndView.addObject("products", productService.getAllProducts());
        return modelAndView;
    }

//    @GetMapping("/products/{id}")
//    public String productByCategory(@PathVariable("id") Long id, Model model) {
//        Optional<Optional<Category>> category = Optional.ofNullable(categoryService.getCategoryById(id));
//        if (category.isEmpty()) {
//            return "redirect:/products?error=CategoryNotFound";
//        }
//        model.addAttribute("select_category", category.get());
//        model.addAttribute("categories", categoryService.getAllCategories());
//        model.addAttribute("products", productService.findByCategoryId(id));
//        return "/category/category";
//    }

    @GetMapping("/products/{id}")
    public String productsByCategory(@PathVariable("id") Long id, Model model) {
        Optional<Category> category = categoryService.getCategoryById(id);
        if (category.isEmpty()) {
            System.out.println("Category not found for ID: " + id);
            return "redirect:/products?error=CategoryNotFound";
        }
        System.out.println("Category found: " + category.get().getName());
        model.addAttribute("select_category", category.get());
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("products", productService.findByCategoryId(id));
        return "/category/products_by_category";
    }

    @GetMapping("/products/new")
    public String createProductForm(Model model) {
        if (!model.containsAttribute("product")) {
            model.addAttribute("product", new Product());
        }
        model.addAttribute("categories", categoryService.getAllCategories());
        return "/product/product_form";
    }

    @PostMapping("/product")
    public String saveProduct(@Valid @ModelAttribute("product") Product product, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        System.out.println("Product received: " + product);
        if (bindingResult.hasErrors()) {
            System.out.println("Validation errors: " + bindingResult.getAllErrors());
            redirectAttributes.addFlashAttribute("product", product)
                    .addFlashAttribute("org.springframework.validation.BindingResult.product", bindingResult);
            return "redirect:/products/new";
        }
        productService.createProduct(product);
        redirectAttributes.addFlashAttribute("success", "Product created successfully!");
        return "redirect:/products";
    }



    @GetMapping("/products/edit/{id}")
    public String editProductForm(@PathVariable Long id, Model model) {
        Optional<Product> product = productService.getProductById(id);
        if (product.isEmpty()) {
            return "redirect:/products?error=ProductNotFound";
        }
        model.addAttribute("product", product.get());
        model.addAttribute("categories", categoryService.getAllCategories()); // Pass list of categories
        return "/product/product_edit";
    }


    @PostMapping("/product/{id}")
    public String updateProduct(@PathVariable Long id,
                                @Valid @ModelAttribute("product") Product product,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("product", product)
                    .addFlashAttribute("org.springframework.validation.BindingResult.product", bindingResult);
            return "redirect:/products/edit/" + id;
        }
        Optional<Product> existingProduct = productService.getProductById(id);
        if (existingProduct.isEmpty()) {
            return "redirect:/products?error=ProductNotFound";
        }

        // Update existing product
        Product updatedProduct = existingProduct.get();
        updatedProduct.setName(product.getName());
        updatedProduct.setPrice(product.getPrice());
        updatedProduct.setDescription(product.getDescription());
        updatedProduct.setCategory(product.getCategory());

        productService.createProduct(updatedProduct); // Save updated product
        redirectAttributes.addFlashAttribute("success", "Product updated successfully!");
        return "redirect:/products";
    }


//    @PostMapping("/products/{id}")
//    public String updateProduct(@PathVariable Long id, @Valid @ModelAttribute("product") Product product, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
//        if (bindingResult.hasErrors()) {
//            redirectAttributes.addFlashAttribute("product", product).addFlashAttribute("org.springframework.validation.BindingResult.product", bindingResult);
//            return "redirect:/product/products/edit/" + id;
//        }
//        Optional<Product> existingProduct = productService.getProductById(id);
//        if (existingProduct.isEmpty()) {
//            return "redirect:/products?error=ProductNotFound";
//        }
//        Product updatedProduct = existingProduct.get();
//        updatedProduct.setName(product.getName());
//        updatedProduct.setPrice(product.getPrice());
//        updatedProduct.setDescription(product.getDescription());
//        updatedProduct.setCategory(product.getCategory());
//        productService.createProduct(updatedProduct);
//        redirectAttributes.addFlashAttribute("success", "Product updated successfully!");
//        return "redirect:/product/products";
//    }

    @GetMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        if (productService.getProductById(id).isPresent()) {
            productService.deleteProductById(id);
            redirectAttributes.addFlashAttribute("success", "Product deleted successfully!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Product not found!");
        }
        return "redirect:/product/products";
    }

    @PostMapping("/{productId}/pay")
    public String processPayment(@PathVariable Long productId,
                                 @RequestParam("paymentDetails") String paymentDetails,
                                 @RequestParam("rentalDays") int rentalDays,
                                 Model model) {
        Optional<Product> product = productService.getProductById(productId);
        if (product.isEmpty()) {
            model.addAttribute("message", "Product not found.");
            return "error-page";
        }

        // Calculate total rental cost
        BigDecimal totalAmount = rentService.calculateTotalAmount(product.get(), rentalDays);
        model.addAttribute("totalAmount", totalAmount);

        // Mock payment processing
        boolean paymentSuccess = paymentService.processPayment(product.get(), paymentDetails);

        if (paymentSuccess) {
            model.addAttribute("message", "Payment successful! Enjoy your rental.");
            return "success-page";
        } else {
            model.addAttribute("message", "Payment failed. Please try again.");
            return "error-page";
        }
    }

}