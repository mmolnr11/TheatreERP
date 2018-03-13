package com.project.model;

public class Customer {
    private String id;

    public Customer(){};
    public Customer(String id){
        this.id = id;
    }
     
    // firstname
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    // me
}