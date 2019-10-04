package com.cl.java.test.example.streams;

import com.cl.java.test.example.domain.Dish;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(JUnit4.class)
public class StreamsApiTest {

    List<Dish> menu = Arrays.asList(
            new Dish("pork", false, 800, Dish.Type.MEAT),
            new Dish("beef", false, 700, Dish.Type.MEAT),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french fries", true, 530, Dish.Type.OTHER),
            new Dish("rice", true, 350, Dish.Type.OTHER),
            new Dish("season fruit", true, 120, Dish.Type.OTHER),
            new Dish("pizza", true, 550, Dish.Type.OTHER),
            new Dish("prawns", false, 300, Dish.Type.FISH),
            new Dish("salmon", false, 450, Dish.Type.FISH));

    @Test
    public void testStreamIteration() {
        // Obtengo solo los nombres, en una lista
        List<String> platos = menu.stream().map(Dish::getName).collect(toList());

        //Imprimo los platos
        platos.forEach(System.out::println);

        platos.stream().distinct();
    }

    @Test
    public void testVegetarianDish() {

        List<Dish> vegetarian = new ArrayList<>();

        for (Dish dish : menu) {
            if (dish.isVegetarian()) {
                vegetarian.add(dish);
            }
        }

        List<Dish> vegetarianStream = menu.stream().filter(Dish::isVegetarian).collect(toList());

        assertEquals(vegetarian.size(), vegetarianStream.size());

    }

    @Test
    public void testDistinctStream() {
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        List<Integer> distinctNumbers = numbers.stream()
                .filter(i -> i % 2 == 0)
                .distinct().collect(toList());

        assertEquals(2, distinctNumbers.size());
    }

    @Test
    public void testTruncateStream() {
        List<Dish> truncateDish = menu.stream().filter(dish -> dish.getCalories() > 300).collect(toList());
        Integer size1 = truncateDish.size();
        truncateDish = truncateDish.stream().limit(3).collect(toList());
        assertEquals(3, truncateDish.size());
        assertNotEquals(size1, new Integer(truncateDish.size()));
    }

    @Test
    public void testSkipStream() {
        List<Dish> truncateDish = menu.stream().filter(dish -> dish.getCalories() > 300).collect(toList());
        Integer size1 = truncateDish.size();
        truncateDish = truncateDish.stream().skip(3).collect(toList());
        assertEquals(size1 - 3, truncateDish.size());
        assertNotEquals(size1, new Integer(truncateDish.size()));
    }

    @Test
    public void testMappingStream() {
        List<String> dishNames = menu.stream()
                .map(Dish::getName)
                .collect(toList());
    }

    @Test
    public void testSquareNumbers() {

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        List<Integer> squareNums = numbers.stream().map(i -> i * i).collect(Collectors.toList());

        assertEquals(squareNums.get(2), new Integer(9));

    }

    @Test
    public void testPairNumbers() {

        List<Integer> numbers01 = Arrays.asList(1, 2, 3);
        List<Integer> numbers02 = Arrays.asList(4, 5);

        List<int[]> pairs = numbers01.stream().flatMap(i -> numbers02.stream().map(j -> new int[]{i, j})).collect(toList());

        pairs = pairs.stream().filter(ints -> (ints[0] + ints[1]) % 3 == 0).collect(toList());

        pairs.stream().forEach(ints -> {System.out.println("("+ints[0]+","+ints[1]+")");});

    }


}
