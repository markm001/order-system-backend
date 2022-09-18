package com.ccat.ordersys.model;

import com.ccat.ordersys.model.entity.OrderItem;
import com.ccat.ordersys.model.entity.OrderStatus;
import com.ccat.ordersys.model.entity.User;

import java.time.LocalDateTime;
import java.util.Set;

public class OrderResponse {
    private Long id;
    private User user;
    private LocalDateTime orderTime;
    private OrderStatus orderStatus;
    private Set<OrderItem> orderItems;

    public OrderResponse() {

    }

    public OrderResponse(Long id, User user, LocalDateTime orderTime, OrderStatus orderStatus, Set<OrderItem> orderItems) {
        this.id = id;
        this.user = user;
        this.orderTime = orderTime;
        this.orderStatus = orderStatus;
        this.orderItems = orderItems;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
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
