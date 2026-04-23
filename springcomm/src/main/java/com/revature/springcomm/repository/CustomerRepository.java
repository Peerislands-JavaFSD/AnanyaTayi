package com.revature.springcomm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.revature.springcomm.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, String> {
}