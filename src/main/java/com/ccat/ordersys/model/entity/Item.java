package com.ccat.ordersys.model.entity;

import java.util.Set;
import java.util.UUID;

public class Item {

    private Long id;
    private String name;
    private String description;
    private Long price;
    private Set<String> tags;

    //Constructors:
    public Item() {

    }

    public Item(String name, String description, Long price, Set<String> tags) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.tags = tags;
    }

    public Item(Long id, String name, String description, Long price, Set<String> tags) {
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

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Long getPrice() {
        return price;
    }

    public Set<String> getTags() {
        return tags;
    }
}
