package com.ccat.ordersys.model.entity;

import java.util.List;
import java.util.Set;

public class OrderList {
    private Long userId;
    private List<OrderItem> orderItems;
    private Long shippingCost;
    private String info;
    private Long total;

    //Constructors:
    public OrderList() {

    }
    public OrderList(Long userId, List<OrderItem> orderItems, Long shippingCost, String info, Long total) {
        this.userId = userId;
        this.orderItems = orderItems;
        this.shippingCost = shippingCost;
        this.info = info;
        this.total = total;
    }

    //Getters & Setters:
    public Long getUserId() {
        return userId;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public Long getShippingCost() {
        return shippingCost;
    }

    public String getInfo() {
        return info;
    }

    public Long getTotal() {
        return total;
    }
}
