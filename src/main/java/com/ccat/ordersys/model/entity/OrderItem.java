package com.ccat.ordersys.model.entity;

public class OrderItem {
    private Long id;
    private Long orderId;
    private Long itemId;
    private Integer quantity;

    //Constructors:
    public OrderItem() {

    }
    public OrderItem(Long id,Long orderId, Long itemId, Integer quantity) {
        this.id = id;
        this.orderId = orderId;
        this.itemId = itemId;
        this.quantity = quantity;
    }

    //Getters & Setters:
    public Long getId() {
        return id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Long getItemId() {
        return itemId;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
