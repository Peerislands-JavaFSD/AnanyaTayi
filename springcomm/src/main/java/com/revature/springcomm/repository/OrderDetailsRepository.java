package com.revature.springcomm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.revature.springcomm.entity.OrderDetails;
import com.revature.springcomm.entity.OrderDetailsId;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, OrderDetailsId> {
}