package com.ccat.ordersys.model;

public class UserResponse {

    private Long id;
    private String email;
    private String firstName;
    private String lastName;

    //Constructors:
    public UserResponse() {

    }

    public UserResponse(Long id, String email, String firstName, String lastName) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
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
}
