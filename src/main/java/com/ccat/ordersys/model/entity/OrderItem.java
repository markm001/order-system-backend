package com.ccat.ordersys.model.entity;

import javax.persistence.*;

@Embeddable
public class OrderItem {
    private Long id;
    private Long itemId;
    private Integer quantity;

    //Constructors:
    public OrderItem() {

    }
    public OrderItem(Long id, Long itemId, Integer quantity) {
        this.id = id;
        this.itemId = itemId;
        this.quantity = quantity;
    }

    //Getters & Setters:
    public Long getId() {
        return id;
    }

    public Long getItemId() {
        return itemId;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
