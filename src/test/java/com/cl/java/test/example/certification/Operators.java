package com.cl.java.test.example.certification;

import com.cl.java.test.example.domain.Dish;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class Operators {

    @Test
    public void testUnaryOperators(){
        int x = 3;
        System.out.println("Post "+x++);
        System.out.println(x);
        System.out.println("Pre "+(++x));
        System.out.println(x);
        System.out.println("Post "+x--);
        System.out.println(x);
        System.out.println("Pre "+--x);
        System.out.println(x);

        int j = ++x * 5 / x-- + --x;

        System.out.println(j);
    }

}
