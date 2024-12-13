package com.example.rent.service.Implement;

import com.example.rent.model.Customer;
import com.example.rent.repository.CustomerRepository;
import com.example.rent.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImplement implements CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerServiceImplement(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> findByCustomerAddress(String name) {
        return customerRepository.findAllByAddress(name);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> createCustomers(List<Customer> customers) {
        return customerRepository.saveAll(customers);
    }

    @Override
    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public Customer updateCustomer(Customer customer) {

        Customer findCustomer = customerRepository.findById(customer.getId()).orElse(null);

        findCustomer.setName(customer.getName());
        findCustomer.setEmail(customer.getEmail());
        findCustomer.setPhone(customer.getPhone());
        findCustomer.setAddress(customer.getAddress());
        findCustomer.setCity_region(customer.getCity_region());
        findCustomer.setCc_number(customer.getCc_number());
        return customerRepository.save(findCustomer);
    }

    @Override
    public String deleteCustomerById(Long id) {
        customerRepository.deleteById(id);
        return id + "-тай хэрэглэгчийн мэдээлэл устлаа";
    }

    @Override
    public List<Customer> deleteCustomers(List<Customer> customers) {
        return List.of();
    }


}
