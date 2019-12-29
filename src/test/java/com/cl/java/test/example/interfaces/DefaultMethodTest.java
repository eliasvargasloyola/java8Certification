package com.cl.java.test.example.interfaces;

import com.cl.java.test.example.Commerce;
import com.cl.java.test.example.domain.Dish;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class DefaultMethodTest {

    Commerce commerce = new Commerce();

    @Test
    public void testDefaultMethod() {
        ArrayList<Dish> list = new ArrayList<>(Arrays.asList(new Dish("pork", false, 800, Dish.Type.MEAT)));
        commerce.connect();
        Boolean added = commerce.add(list, new Dish("beef", false, 700, Dish.Type.MEAT));
        Boolean removed = commerce.delete(list, new Dish("rice", true, 350, Dish.Type.OTHER));
        commerce.process(list);
        commerce.reset(list);
        assertTrue(added);
        assertFalse(removed);
        assertTrue(list.isEmpty());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testDefaultMethodUnsoported() {
        commerce.connect();
        commerce.paid();
    }

}
