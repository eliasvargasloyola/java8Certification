package com.cl.java.test.example.interfaces;

import java.util.List;

public interface Cart<T> {

    Boolean process(List<T> cart);

    default Boolean add(List<T> cart, T t) {
        return cart.add(t);
    }

    default Boolean delete(List<T> cart, T t) {
        return cart.remove(t);
    }

    default void reset(List<T> cart) {
        cart.clear();
    }

    default void paid(){
        throw new UnsupportedOperationException("Cannot paid for now.");
    }

}
