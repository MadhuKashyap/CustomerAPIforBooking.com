package com.berti.notes.model;

public class User {
    int id;
    String name;
    int pincode;

    public User(int id, String name, int pincode) {
        this.id = id;
        this.name = name;
        this.pincode = pincode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPincode() {
        return pincode;
    }

    public void setPincode(int pincode) {
        this.pincode = pincode;
    }
}
