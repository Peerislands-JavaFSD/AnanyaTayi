package com.revature.springcomm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import com.revature.springcomm.entity.*;
import com.revature.springcomm.repository.*;

import jakarta.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping("/order-details")
public class OrderDetailsController {

    @Autowired
    private OrderDetailsRepository repo;

    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private ProductRepository productRepo;

    // ✅ CREATE — returns 201 Created
    @PostMapping
    public ResponseEntity<OrderDetails> create(@RequestBody @Valid OrderDetails details) {

        Integer orderId = details.getOrder() != null ? details.getOrder().getOrderId() : null;
        Integer productId = details.getProduct() != null ? details.getProduct().getProductId() : null;

        if (orderId == null || productId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "OrderId or ProductId missing!");
        }

        Order order = orderRepo.findById(orderId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found with id: " + orderId));

        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found with id: " + productId));

        OrderDetailsId id = new OrderDetailsId();
        id.setOrderId(orderId);
        id.setProductId(productId);

        OrderDetails newDetails = new OrderDetails();
        newDetails.setId(id);
        newDetails.setOrder(order);
        newDetails.setProduct(product);
        newDetails.setQuantity(details.getQuantity());
        newDetails.setDiscount(details.getDiscount());

        return ResponseEntity.status(HttpStatus.CREATED).body(repo.save(newDetails));
    }

    // ✅ READ ALL — returns 200 OK
    @GetMapping
    public ResponseEntity<List<OrderDetails>> getAll() {
        List<OrderDetails> list = repo.findAll();
        return ResponseEntity.ok(list);
    }

    // ✅ READ BY ID — returns 200 OK or 404
    @GetMapping("/{orderId}/{productId}")
    public ResponseEntity<OrderDetails> getById(@PathVariable Integer orderId,
                                                @PathVariable Integer productId) {

        OrderDetailsId id = new OrderDetailsId();
        id.setOrderId(orderId);
        id.setProductId(productId);

        OrderDetails details = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "OrderDetails not found for orderId: " + orderId + ", productId: " + productId));

        return ResponseEntity.ok(details);
    }

    // ✅ UPDATE — returns 200 OK or 404
    @PutMapping("/{orderId}/{productId}")
    public ResponseEntity<OrderDetails> update(@PathVariable Integer orderId,
                                               @PathVariable Integer productId,
                                               @RequestBody @Valid OrderDetails details) {

        OrderDetailsId id = new OrderDetailsId();
        id.setOrderId(orderId);
        id.setProductId(productId);

        repo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "OrderDetails not found for orderId: " + orderId + ", productId: " + productId));

        Order order = orderRepo.findById(orderId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found with id: " + orderId));

        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found with id: " + productId));

        OrderDetails updatedDetails = new OrderDetails();
        updatedDetails.setId(id);
        updatedDetails.setOrder(order);
        updatedDetails.setProduct(product);
        updatedDetails.setQuantity(details.getQuantity());
        updatedDetails.setDiscount(details.getDiscount());

        return ResponseEntity.ok(repo.save(updatedDetails));
    }

    // ✅ DELETE — returns 204 No Content or 404
    @DeleteMapping("/{orderId}/{productId}")
    public ResponseEntity<Void> delete(@PathVariable Integer orderId,
                                       @PathVariable Integer productId) {

        OrderDetailsId id = new OrderDetailsId();
        id.setOrderId(orderId);
        id.setProductId(productId);

        repo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "OrderDetails not found for orderId: " + orderId + ", productId: " + productId));

        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}