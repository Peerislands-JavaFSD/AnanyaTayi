package com.revature.springcomm.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.revature.springcomm.entity.*;
import com.revature.springcomm.repository.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private CustomerRepository customerRepo;

    // CREATE ORDER (IMPORTANT)
    @PostMapping
    public Order create(@RequestBody Order order) {

        // attach existing customer
        if (order.getCustomer() != null) {
            String custId = order.getCustomer().getCustomerId();
            Customer customer = customerRepo.findById(custId).orElse(null);
            order.setCustomer(customer);
        }

        return orderRepo.save(order);
    }

    // READ ALL
    @GetMapping
    public List<Order> getAll() {
        return orderRepo.findAll();
    }

    // READ ONE
    @GetMapping("/{id}")
    public Order getOne(@PathVariable Integer id) {
        return orderRepo.findById(id).orElse(null);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        orderRepo.deleteById(id);
    }
}