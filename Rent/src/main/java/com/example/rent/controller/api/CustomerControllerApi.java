package com.example.rent.controller.api;

import com.example.rent.model.Customer;
import com.example.rent.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CustomerControllerApi {
    CustomerService customerService;

    public CustomerControllerApi(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/api/customers")
    public List<Customer> getCustomers() {
        return customerService.getAllCustomers();
    }

    @PostMapping("/api/customer")
    public Customer addCustomer(@RequestBody Customer customer){
        return customerService.createCustomer(customer);
    }

    @PostMapping("/api/customers")
    public List<Customer> addCustomers(@RequestBody List<Customer> customers){
        return customerService.createCustomers(customers);
    }

    @GetMapping("/api/customers/{id}")
    public Optional<Customer> getCustomerById(@PathVariable Long id){
        return customerService.getCustomerById(id);
    }

    @PutMapping("/api/customer")
    public Customer editCustomer(@RequestBody Customer customer){
        return customerService.updateCustomer(customer);
    }

    @DeleteMapping("/api/customer/{id}")
    public String deleteCustomerById(@PathVariable Long id){
        return customerService.deleteCustomerById(id);
    }

    @DeleteMapping("/api/customers")
    public String deleteCustomers(@RequestBody List<Customer> customers){
        return customerService.deleteCustomers(customers).toString();
    }
}
