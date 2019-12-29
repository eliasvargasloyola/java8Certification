package com.cl.java.test.example.services;

import com.cl.java.test.example.domain.Person;

import java.util.Random;

public class PersonFactory {

    public Person getPerson() {
        int number = new Random().nextInt(10 + 1);
        if (number >= 7) {
            return new Person("Raul", "Fernandez", 22, "ARG");
        }
        return null;
    }
}
