package com.ccat.ordersys.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AddressResponse {
        private String status;
        private int code;
        private int total;
        private List<HashMap<String, String>> data;

    //CONSTRUCTORS:
    public AddressResponse() {
    }
    public AddressResponse(String status, int code, int total, List<HashMap<String, String>> data) {
        this.status = status;
        this.code = code;
        this.total = total;
        this.data = data;
    }

    //GETTERS & SETTERS:
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<HashMap<String, String>> getData() {
        return data;
    }

    public void setData(List<HashMap<String, String>> data) {
        this.data = data;
    }
}
