package com.revature.springcomm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.revature.springcomm.entity.Customer;
import com.revature.springcomm.repository.CustomerRepository;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerRepository repo;

    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        return repo.save(customer);
    }

    @GetMapping
    public List<Customer> getAllCustomers() {
        return repo.findAll();
    }
}