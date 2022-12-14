package com.ccat.ordersys.model.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="items")
public class Item {
    @Id
    private Long id;
    private String name;
    private String description;
    private Long price;

    @ElementCollection
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

    public Item(String name, String description, Long price) {
        this.name = name;
        this.description = description;
        this.price = price;
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
