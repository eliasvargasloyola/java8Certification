package com.cl.java.test.example.streams;

import com.cl.java.test.example.domain.Dish;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.averagingInt;
import static java.util.stream.Collectors.maxBy;
import static java.util.stream.Collectors.reducing;
import static java.util.stream.Collectors.summarizingInt;
import static java.util.stream.Collectors.summingInt;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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

    @Test
    public void testSumCollector(){
        int totalCalories = menu.stream().collect(summingInt(Dish::getCalories));
        assertEquals(4200, totalCalories);
    }

    @Test
    public void testAverageCollector(){
        Double avgCalories = menu.stream().collect(averagingInt(Dish::getCalories));
        assertTrue(avgCalories > 400);
    }

    @Test
    public void testStaticsCollector(){
        IntSummaryStatistics menuStatics = menu.stream().collect(summarizingInt(Dish::getCalories));
        assertEquals(4200, menuStatics.getSum());
        assertEquals(800, menuStatics.getMax());
        assertEquals(9, menuStatics.getCount());
    }

    @Test
    public void testJoinStringDelimiterCollector(){
        String delimiter = ";";
        String menuNames = menu.stream().map(Dish::getName).collect(Collectors.joining(delimiter));
        long count = Arrays.asList(menuNames.split(delimiter)).stream().count();
        assertEquals(menu.stream().count(), count);
    }

    @Test
    public void testReducingCollector(){
        int totalCalories = menu.stream().collect(reducing(0, Dish::getCalories, (i, j) -> i+j));
        assertEquals(4200, totalCalories);
    }

}
