package com.ccat.ordersys.model.entity;

public class Address {
    private String street; //streetname + street + number
    private String city;
    private String zipcode;
    private String country;

    //CONSTRUCTOR:
    public Address() {

    }

    public Address(String street, String city, String zipcode, String country) {
        this.street = street;
        this.city = city;
        this.zipcode = zipcode;
        this.country = country;
    }

    //GETTER:
    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public String getCountry() {
        return country;
    }
}
