package com.cl.java.test.example.streams;

import com.cl.java.test.example.domain.Dish;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.maxBy;
import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class CollectingStreamsTest {

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
    public void testCounCollection() {
        Long countDishes = menu.stream().collect(Collectors.counting());
        assertEquals(Optional.of(9L).get(), Optional.of(countDishes).get());
    }

    @Test
    public void testMaxValueCollector() {
        Optional<Dish> maxCaloriesDish = menu.stream().collect(maxBy(Comparator.comparing(Dish::getCalories)));
        assertEquals(800, maxCaloriesDish.get().getCalories());
    }

}
