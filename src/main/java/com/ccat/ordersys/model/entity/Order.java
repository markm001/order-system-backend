package com.ccat.ordersys.model.entity;

import java.time.LocalDateTime;
import java.util.Set;

public class Order {

    private Long id;
    private Long userId;
    private LocalDateTime orderTime;
    private OrderStatus orderStatus;
    private Set<OrderItem> orderList;

    //Constructors:
    public Order() {

    }
    public Order(Long id, Long userId, LocalDateTime orderTime, OrderStatus orderStatus, Set<OrderItem> orderList) {
        this.id = id;
        this.userId = userId;
        this.orderTime = orderTime;
        this.orderStatus = orderStatus;
        this.orderList = orderList;
    }

    //Getters & Setters:
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Set<OrderItem> getOrderList() {
        return orderList;
    }

    public void setOrderList(Set<OrderItem> orderList) {
        this.orderList = orderList;
    }
}
