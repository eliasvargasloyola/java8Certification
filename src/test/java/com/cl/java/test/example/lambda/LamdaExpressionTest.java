package com.cl.java.test.example.lambda;

import com.cl.java.test.example.domain.Fruit;
import com.cl.java.test.example.domain.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class LamdaExpressionTest {

    @Test
    public void testComparatorExpression() {

        Comparator<Person> asYounger = new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getAge().compareTo(o2.getAge());
            }
        };

        Comparator<Person> asYoungerLambda = (Person p1, Person p2) -> p1.getAge().compareTo(p2.getAge());

        Person p1 = new Person();
        p1.setAge(27);
        p1.setLastName("Vargas");
        p1.setName("Elias");

        Person p2 = new Person();
        p2.setName("Alonso");
        p2.setLastName("Vargas");
        p2.setAge(8);

        int isYounger = asYounger.compare(p1, p2);

        int isYoungerLambda = asYoungerLambda.compare(p1, p2);

        assertEquals(isYounger, isYoungerLambda);
    }

    @Test
    public void testFuncionalInterfaceRun() {

        Runnable r1 = () -> System.out.println("Run the process one");

        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Run the process two");
            }
        };

        process(r1);
        process(r2);
        //Paso el comportamiento como parametro
        process(() -> System.out.println("Run the thirty process"));

    }

    public static void process(Runnable r) {
        r.run();
    }

    @Test
    public void testFuncionalInterfacePredicate() {

        Predicate<Person> asCountry = (p) -> p.getCountry().equalsIgnoreCase("CL");

        Person p = new Person("Elias", "Vargas", 27, "CL");

        Boolean asChilean = asCountry.test(p);

        assertTrue(asChilean);
    }

    @Test
    public void testFunctionConsumer() {

        Consumer<Person> printPerson = (p) -> System.out.println("Welcome ".concat(p.getName()).concat(" you have " + p.getAge()).concat(" years old."));

        Person p = new Person("Elias", "Vargas", 27, "CL");

        printPerson.accept(p);
    }

    @Test
    public void testFunction() {

        Function<Person, String> thisIsMe = (p -> "Welcome ".concat(p.getName()).concat(" you have " + p.getAge()).concat(" years old."));

        Person p = new Person("Elias", "Vargas", 27, "CL");

        System.out.println(thisIsMe.apply(p));

    }

    @Test
    public void testComparatorMethodReference() {

        Comparator<Person> asYounger = new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getAge().compareTo(o2.getAge());
            }
        };

        Comparator<Person> asYoungerMethod = Comparator.comparing(Person::getAge);

        Function<String, Integer> parsear = (str) -> {
            if (str != null) {
                return Integer.parseInt(str);
            } else {
                return 0;
            }
        };

        Person p1 = new Person();
        p1.setAge(27);
        p1.setLastName("Vargas");
        p1.setName("Elias");

        Person p2 = new Person();
        p2.setName("Alonso");
        p2.setLastName("Vargas");
        p2.setAge(8);

        int isYounger = asYounger.compare(p1, p2);

        int isYoungerLambda = asYoungerMethod.compare(p1, p2);

        assertEquals(isYounger, isYoungerLambda);
    }

    @Test
    public void testConstructorReference() {

        Map<String, Function<Integer, Fruit>> mapFruits = new HashMap<>();
        mapFruits.put("apple", Fruit::new);

        Fruit apple = mapFruits.get("apple").apply(102);

        assertEquals(new Integer(102), apple.getWeight());

    }

    @Test
    public void testApplyComparatorMethodReference() {

        Comparator<Person> asYounger = Comparator.comparing(Person::getAge);

        Person papa = new Person();
        papa.setAge(27);
        papa.setLastName("Vargas");
        papa.setName("Elias");

        Person alo = new Person();
        alo.setName("Alonso");
        alo.setLastName("Vargas");
        alo.setAge(8);

        Person vicky = new Person();
        vicky.setName("Victoria");
        vicky.setLastName("Vargas");
        vicky.setAge(6);

        ArrayList<Person> familia = new ArrayList<>(Arrays.asList(papa, alo, vicky));

        // Ordena desencidente por edad
        familia.sort(Comparator.comparing(Person::getAge).reversed());

        assertEquals("Papá es el mayor", papa.getName(), familia.get(0).getName());

        // Ordena ascendete por edad
        familia.sort(Comparator.comparing(Person::getAge));

        assertEquals("Victoria es la menor", vicky.getName(), familia.get(0).getName());

    }

    @Test
    public void testMultiComparations() {

        Fruit f1 = new Fruit("Manzana", "Verde", 5);
        Fruit f2 = new Fruit("Manzana", "Verde", 3);
        Fruit f3 = new Fruit("Manzana", "Verde", 4);
        Fruit f4 = new Fruit("Manzana", "Verde", 2);

        ArrayList<Fruit> manzanas = new ArrayList<>(Arrays.asList(f1, f2, f3, f4));

        // Con el thenComparing, puedo agregar mas formas de comparar en caso de que quiera ordenar de mejor manera
        manzanas.sort(Comparator.comparing(Fruit::getName).thenComparing(Fruit::getWeight));

        assertEquals("Primero las mas livianas", f4.getWeight(), manzanas.get(0).getWeight());

        manzanas.sort(Comparator.comparing(Fruit::getName).thenComparing(Fruit::getWeight).reversed());

        assertEquals("Primero las mas pesadas", f1.getWeight(), manzanas.get(0).getWeight());
    }

    @Test
    public void testComposingFunctions() {

        Function<Integer, Integer> suma = (x) -> x + 1;
        Function<Integer, Integer> multiplica = (x) -> x * 2;
        Function<Integer, Integer> sumaYmultiplica = suma.andThen(multiplica);

        Integer resultado = sumaYmultiplica.apply(1);

        assertEquals("Mismo valor", new Integer(4), resultado);

        Function<Integer, Integer> multiplicaYsuma = suma.compose(multiplica);

        Integer response = multiplicaYsuma.apply(1);

        assertEquals("Mismo valor", new Integer(3), response);

    }

}
