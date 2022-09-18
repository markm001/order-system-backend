package com.ccat.ordersys.model.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name="orders")
public class Order {
    @Id
    private Long id;

    private Long userId;
    private LocalDateTime orderTime;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @ElementCollection
    @CollectionTable(name="ORDER_ITEMS",
            joinColumns={@JoinColumn(name="orderId", referencedColumnName="ID")})
    private Set<OrderItem> orderItems;


    //Constructors:
    public Order() {

    }

    public Order(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Order(Long id, Long userId, LocalDateTime orderTime, OrderStatus orderStatus, Set<OrderItem> orderItems) {
        this.id = id;
        this.userId = userId;
        this.orderTime = orderTime;
        this.orderStatus = orderStatus;
        this.orderItems = orderItems;
    }

    //Getters & Setters:
    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public Set<OrderItem> getOrderItems() {
        return orderItems;
    }
}
