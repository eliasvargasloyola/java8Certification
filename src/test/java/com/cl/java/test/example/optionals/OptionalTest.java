package com.cl.java.test.example.optionals;

import com.cl.java.test.example.domain.Person;
import com.cl.java.test.example.services.PersonFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Optional;

import static org.junit.Assert.assertNotNull;

@RunWith(JUnit4.class)
public class OptionalTest {

    PersonFactory factory = new PersonFactory();

    @Test
    public void testOptionalMap() {
        String name = Optional.ofNullable(factory.getPerson()).map(Person::getName).orElse("EmptyName");
        assertNotNull(name);
        System.out.println(name);
    }

}
