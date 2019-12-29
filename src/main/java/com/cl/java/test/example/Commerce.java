package com.cl.java.test.example;

import com.cl.java.test.example.domain.Dish;
import com.cl.java.test.example.interfaces.Cart;
import com.cl.java.test.example.interfaces.Paid;

import java.util.List;

public class Commerce implements Cart<Dish>, Paid {

    @Override
    public Boolean process(List<Dish> cart) {
        System.out.println("Processing cart ok ...");
        return true;
    }

    @Override
    public Boolean connect() {
        System.out.println("Connected to runway paiment ...");
        return true;
    }
}
