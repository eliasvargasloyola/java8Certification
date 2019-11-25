package com.cl.java.test.example.streams;

import com.cl.java.test.example.domain.CaloricLevel;
import com.cl.java.test.example.domain.Dish;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class GroupingStreamsTest {

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
    public void testGroupingByType() {
        Map<Dish.Type, List<Dish>> dishByType = menu.stream().collect(Collectors.groupingBy(Dish::getType));
        assertEquals(3, dishByType.size());
    }

    @Test
    public void testCustomGrouping() {
        Map<CaloricLevel, List<Dish>> dishByCaloric = menu.stream().collect(Collectors.groupingBy(dish -> {
            if (dish.getCalories() <= 400) return CaloricLevel.DIET;
            else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
            else return CaloricLevel.FAT;
        }));
        assertEquals(3, dishByCaloric.size());
    }

    @Test
    public void testMultiGrouping() {
        Map<Dish.Type, Map<CaloricLevel, List<Dish>>> dishByTypeAndCaloricLevel = menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.groupingBy(dish -> {
            if (dish.getCalories() <= 400) return CaloricLevel.DIET;
            else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
            else return CaloricLevel.FAT;
        })));
        assertEquals(3, dishByTypeAndCaloricLevel.size());
        assertEquals(3, dishByTypeAndCaloricLevel.get(Dish.Type.MEAT).size());
    }

    @Test
    public void testSubGroupGrouping() {

        Map<Dish.Type, Long> countDishTypes = menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.counting()));
        assertEquals(new Long(3), countDishTypes.get(Dish.Type.MEAT));

    }

    @Test
    public void testGroupDifferentCollector() {

        Map<Dish.Type, Dish> mostCaloricByType = menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)), Optional::get)));
        assertEquals(3, mostCaloricByType.size());

    }

    @Test
    public void testPartiotiningGroup() {

        Map<Boolean, List<Dish>> partitionMenu = menu.stream().collect(Collectors.partitioningBy(Dish::isVegetarian));

        assertEquals(2, partitionMenu.size());

    }

    @Test
    public void testPartiotiningGroupMultiCollector() {

        Map<Boolean, Map<Dish.Type, List<Dish>>> partitionMenu = menu.stream().collect(Collectors.partitioningBy(Dish::isVegetarian, Collectors.groupingBy(Dish::getType)));

        assertEquals(2, partitionMenu.size());
        assertEquals(2, partitionMenu.get(false).size());

    }




}
