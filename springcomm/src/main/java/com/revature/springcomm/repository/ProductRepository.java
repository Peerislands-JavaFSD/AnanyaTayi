package com.revature.springcomm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.revature.springcomm.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}