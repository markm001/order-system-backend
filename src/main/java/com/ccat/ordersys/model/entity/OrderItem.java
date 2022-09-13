package com.ccat.ordersys.model.entity;

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

    public void setId(Long id) {
        this.id = id;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
