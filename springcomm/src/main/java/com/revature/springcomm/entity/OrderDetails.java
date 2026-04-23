package com.revature.springcomm.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "order_details")
public class OrderDetails {

    @EmbeddedId
    private OrderDetailsId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("orderId")
    @JoinColumn(name = "order_id")
    @JsonIgnoreProperties({"orderDetails", "hibernateLazyInitializer", "handler"})
    @NotNull(message = "Order must not be null")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    @JsonIgnoreProperties({"orderDetails", "hibernateLazyInitializer", "handler"})
    @NotNull(message = "Product must not be null")
    private Product product;

    @Column(name = "quantity", nullable = false)
    @Min(value = 1, message = "Quantity must be at least 1")
    private int quantity;

    @Column(name = "discount")
    private double discount;

    // ✅ Default constructor (required by JPA)
    public OrderDetails() {}

    // ✅ Full constructor
    public OrderDetails(OrderDetailsId id, Order order, Product product, int quantity, double discount) {
        this.id = id;
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.discount = discount;
    }

    // ✅ Getters & Setters
    public OrderDetailsId getId() {
        return id;
    }

    public void setId(OrderDetailsId id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    // ✅ toString for debugging
    @Override
    public String toString() {
        return "OrderDetails{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", discount=" + discount +
                '}';
    }
}