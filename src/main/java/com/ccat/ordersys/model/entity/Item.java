package com.ccat.ordersys.model.entity;

import java.util.Set;

public class Item {

    private Long id;
    private String name;
    private String description;
    private Integer price;
    private Set<String> tags;

    //Constructors:
    public Item() {

    }

    public Item(Long id, String name, String description, Integer price, Set<String> tags) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.tags = tags;
    }

    //Getters & Setters:
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }
}
