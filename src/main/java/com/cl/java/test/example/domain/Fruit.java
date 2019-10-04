package com.cl.java.test.example.domain;


public class Fruit {

    private String name;
    private String color;
    private Integer weight;

    public Fruit(Integer weight) {
        this.weight = weight;
    }

    public Fruit(String name, String color, Integer weight) {
        this.name = name;
        this.color = color;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }
}
