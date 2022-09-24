package com.ccat.ordersys.model;

import com.ccat.ordersys.model.entity.Address;

public class UserResponse {

    private Long id;
    private String email;
    private String firstName;
    private String lastName;

    private Address address;

    //Constructors:
    public UserResponse() {

    }

    public UserResponse(Long id, String email, String firstName, String lastName, Address address) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Address getAddress() {
        return address;
    }
}
