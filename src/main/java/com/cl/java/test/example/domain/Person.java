package com.cl.java.test.example.domain;

import java.util.Objects;

public class Person {

    private String name;
    private String lastName;
    private Integer age;
    private String country;

    public Person(String name, String lastName, Integer age, String country) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.country = country;
    }

    public Person() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name) &&
                Objects.equals(lastName, person.lastName) &&
                Objects.equals(age, person.age) &&
                Objects.equals(country, person.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lastName, age, country);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", country='" + country + '\'' +
                '}';
    }

}
