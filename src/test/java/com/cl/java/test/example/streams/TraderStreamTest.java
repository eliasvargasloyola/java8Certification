package com.cl.java.test.example.streams;

import com.cl.java.test.example.domain.Trader;
import com.cl.java.test.example.domain.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class TraderStreamTest {

    List<Trader> traders = Arrays.asList(new Trader("Adrian", "USA", "Misisipi",
                    Arrays.asList(new Transaction(500, LocalDateTime.of(2019, Month.JULY, 22, 0, 0)),
                            new Transaction(22, LocalDateTime.of(2019, Month.JANUARY, 12, 0, 0)),
                            new Transaction(123, LocalDateTime.of(2011, Month.MAY, 4, 0, 0)),
                            new Transaction(523, LocalDateTime.of(2012, Month.OCTOBER, 15, 0, 0)),
                            new Transaction(623, LocalDateTime.of(2015, Month.NOVEMBER, 6, 0, 0)),
                            new Transaction(235, LocalDateTime.of(2014, Month.APRIL, 23, 0, 0)))),
            new Trader("Jhon", "ING", "Cambridge",
                    Arrays.asList(new Transaction(523, LocalDateTime.of(2019, Month.JULY, 22, 0, 0)),
                            new Transaction(163, LocalDateTime.of(2014, Month.JANUARY, 12, 0, 0)),
                            new Transaction(74, LocalDateTime.of(2015, Month.MAY, 4, 0, 0)),
                            new Transaction(252, LocalDateTime.of(2011, Month.OCTOBER, 15, 0, 0)),
                            new Transaction(33, LocalDateTime.of(2019, Month.NOVEMBER, 6, 0, 0)),
                            new Transaction(25, LocalDateTime.of(2011, Month.APRIL, 23, 0, 0)))),
            new Trader("Mike", "IT", "Milan",
                    Arrays.asList(new Transaction(765, LocalDateTime.of(2019, Month.JULY, 22, 0, 0)),
                            new Transaction(22, LocalDateTime.of(2019, Month.JANUARY, 12, 0, 0)),
                            new Transaction(11, LocalDateTime.of(2015, Month.JULY, 4, 0, 0)),
                            new Transaction(243, LocalDateTime.of(2019, Month.JANUARY, 15, 0, 0)),
                            new Transaction(473, LocalDateTime.of(2014, Month.JULY, 6, 0, 0)),
                            new Transaction(373, LocalDateTime.of(2011, Month.MAY, 23, 0, 0)))),
            new Trader("Frank", "IT", "Milan",
                    Arrays.asList(new Transaction(86, LocalDateTime.of(2019, Month.JULY, 22, 0, 0)),
                            new Transaction(12, LocalDateTime.of(2015, Month.JANUARY, 12, 0, 0)),
                            new Transaction(352, LocalDateTime.of(2019, Month.MAY, 4, 0, 0)),
                            new Transaction(754, LocalDateTime.of(2011, Month.JULY, 15, 0, 0)),
                            new Transaction(421, LocalDateTime.of(2019, Month.MAY, 6, 0, 0)),
                            new Transaction(125, LocalDateTime.of(2015, Month.MAY, 23, 0, 0)))),
            new Trader("Alison", "ING", "Cambridge",
                    Arrays.asList(new Transaction(235, LocalDateTime.of(2019, Month.JULY, 22, 0, 0)),
                            new Transaction(234, LocalDateTime.of(2011, Month.JANUARY, 12, 0, 0)),
                            new Transaction(263, LocalDateTime.of(2015, Month.MAY, 4, 0, 0)),
                            new Transaction(347, LocalDateTime.of(2014, Month.JULY, 15, 0, 0)),
                            new Transaction(587, LocalDateTime.of(2015, Month.NOVEMBER, 6, 0, 0)),
                            new Transaction(879, LocalDateTime.of(2011, Month.JULY, 23, 0, 0)))));

    @Test
    public void testGetAllTx2011() {
        List<Transaction> txs = traders.stream().map(Trader::getOperations).flatMap(Collection::stream).filter(tx -> tx.getOperationDay().getYear() == 2011).sorted(Comparator.comparing(Transaction::getAmount).reversed()).collect(Collectors.toList());
        Optional<Transaction> fisrt = txs.stream().findFirst();
        Optional<Transaction> last = Optional.ofNullable(txs.get(txs.size() - 1));
        assertTrue(fisrt.get().getAmount() > last.get().getAmount());
    }

    @Test
    public void testGetAllCitiesTraders() {
        List<String> cities = traders.stream().map(Trader::getCity).distinct().collect(Collectors.toList());
        assertEquals(cities.size(), 3);
        System.out.println("Cities are " + cities.toString());
    }

    @Test
    public void testGetAllTradersName() {
        List<String> names = traders.stream().map(Trader::getName).sorted().collect(Collectors.toList());
        assertEquals(names.size(), 5);
        assertEquals(names.get(0), "Adrian");
    }

    @Test
    public void testAreTradersInMilan() {
        Boolean milan = traders.stream().map(Trader::getCity).anyMatch(s -> s.equalsIgnoreCase("Milan"));
        assertTrue(milan);
    }

    @Test
    public void testGetAllTxCambridge() {
        List<Transaction> txs = traders.stream().filter(t -> t.getCity().equalsIgnoreCase("Cambridge")).map(Trader::getOperations).flatMap(Collection::stream).collect(Collectors.toList());
        assertEquals(txs.size(), 12);
        System.out.println(txs);
    }

    @Test
    public void testGetMaxTxOperation() {
        List<Transaction> txs = traders.stream().map(Trader::getOperations).flatMap(Collection::stream).sorted(Comparator.comparing(Transaction::getAmount).reversed()).collect(Collectors.toList());
        Optional<Transaction> first = txs.stream().findFirst();
        Optional<Integer> maxValue = txs.stream().map(Transaction::getAmount).reduce(Integer::max);
        assertTrue(maxValue.isPresent());
        assertEquals(first.get().getAmount(), maxValue.get());
    }

    @Test
    public void testGetMinTx() {
        List<Transaction> txs = traders.stream().map(Trader::getOperations).flatMap(Collection::stream).sorted(Comparator.comparing(Transaction::getAmount)).collect(Collectors.toList());
        Optional<Transaction> first = txs.stream().findFirst();
        Optional<Transaction> min = txs.stream().reduce((t1, t2) -> t1.getAmount() < t2.getAmount() ? t1 : t2);
        assertTrue(min.isPresent());
        assertEquals(first.get().getAmount(), min.get().getAmount());
    }

}
