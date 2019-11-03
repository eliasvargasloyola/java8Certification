package com.cl.java.test.example.domain;

import java.util.ArrayList;
import java.util.List;

public class Trader {

    private String name;
    private String country;
    private String city;
    private List<Transaction> operations = new ArrayList<>();

    public Trader(String name, String country, String city, List<Transaction> operations) {
        this.name = name;
        this.country = country;
        this.city = city;
        this.operations = operations;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<Transaction> getOperations() {
        return operations;
    }

    public void setOperations(List<Transaction> operations) {
        this.operations = operations;
    }
}
