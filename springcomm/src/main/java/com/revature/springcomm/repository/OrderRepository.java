package com.revature.springcomm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.revature.springcomm.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}