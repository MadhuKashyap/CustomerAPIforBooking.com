package com.berti.notes.model;

public class Address {
    int pincode;
    String city;
    String country;

    public Address(int pincode, String city, String country) {
        this.pincode = pincode;
        this.city = city;
        this.country = country;
    }

    public int getPincode() {
        return pincode;
    }

    public void setPincode(int pincode) {
        this.pincode = pincode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
