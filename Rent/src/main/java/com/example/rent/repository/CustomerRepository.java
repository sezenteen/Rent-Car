package com.example.rent.repository;

import com.example.rent.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findAllByAddress(String name);

//    List<Customer> findAllBy();
}
