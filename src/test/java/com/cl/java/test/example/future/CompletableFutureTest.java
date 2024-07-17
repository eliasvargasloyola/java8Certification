package com.cl.java.test.example.future;

import com.cl.java.test.example.domain.Quote;
import com.cl.java.test.example.services.Discount;
import com.cl.java.test.example.services.Shop;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

@RunWith(JUnit4.class)
public class CompletableFutureTest {

    Shop shop = new Shop("Paris");

    List<Shop> shops = Arrays.asList(new Shop("Paris"), new Shop("Falabella"), new Shop("Ripley"), new Shop("ABC"), new Shop("Casa Royal"), new Shop("Dijon"), new Shop("Hites"), new Shop("Fashion Park"));

    @Test
    public void getPriceAsync() {
        long start = System.nanoTime();
        Future<Double> futurePrice = shop.getPriceAsync("IPHONE");
        long invocation = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Invocation return after " + invocation);

        try {
            double price = futurePrice.get();
            System.out.printf("The price is %.2f%n", price);
        } catch (Exception e) {
            e.printStackTrace();
        }

        long retrieval = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Product price return after " + retrieval);
    }

    @Test(expected = RuntimeException.class)
    public void getPriceAsyncError() {
        long start = System.nanoTime();
        Future<Double> futurePrice = shop.getPriceAsync("A");
        long invocation = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Invocation return after " + invocation);

        try {
            double price = futurePrice.get();
            System.out.printf("The price is %.2f%n", price);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Product not available.");
        }

        long retrieval = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Product price return after " + retrieval);
    }

    @Test
    public void testListOfPrices() {
        long start = System.nanoTime();
        String product = "GALAXY s11";
        List<String> prices = shops.stream().map(sp -> String.format("%s price is %.2f", sp.getName(), sp.calculatePrice(product))).collect(Collectors.toList());
        System.out.println(prices.toString());
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " msecs");
    }

    @Test
    public void testListOfPricesParallel() {
        long start = System.nanoTime();
        String product = "GALAXY s11";
        List<String> prices = shops.parallelStream().map(sp -> String.format("%s price is %.2f", sp.getName(), sp.calculatePrice(product))).collect(Collectors.toList());
        System.out.println(prices.toString());
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " msecs");
    }

    @Test
    public void testAsyncListOfPricesParallel() {
        long start = System.nanoTime();
        String product = "GALAXY s11";
        List<CompletableFuture<String>> pricesFutures = shops.parallelStream().map(sp -> CompletableFuture.supplyAsync(() -> String.format("%s price is %.2f", sp.getName(), sp.calculatePrice(product)))).collect(Collectors.toList());
        List<String> prices = pricesFutures.stream().map(CompletableFuture::join).collect(Collectors.toList());
        System.out.println(prices.toString());
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " msecs");
    }

    @Test
    public void testExecutorAsyncListOfPricesParallel() {

        ExecutorService exe = Executors.newFixedThreadPool(Math.min(shops.size(), 100), r -> {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        });

        long start = System.nanoTime();
        String product = "GALAXY s11";
        List<CompletableFuture<String>> pricesFutures = shops.parallelStream().map(sp -> CompletableFuture.supplyAsync(() -> String.format("%s price is %.2f", sp.getName(), sp.calculatePrice(product)), exe)).collect(Collectors.toList());
        List<String> prices = pricesFutures.stream().map(CompletableFuture::join).collect(Collectors.toList());
        exe.shutdown();
        System.out.println(prices.toString());
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " msecs");
    }

    @Test
    public void testDiscounntExecutorAsyncListOfPricesParallel() {
        Executor exe = Executors.newFixedThreadPool(Math.min(shops.size(), 100), r -> {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        });

        long start = System.nanoTime();
        String product = "GALAXY s11";
        List<CompletableFuture<String>> pricesFutures = shops.parallelStream().map(sp -> CompletableFuture.supplyAsync(() -> {
            Discount.Code code = Discount.Code.values()[new Random().nextInt(Discount.Code.values().length)];
            return String.format("%s:%.2f:%s", sp.getName(), sp.calculatePrice(product), code);
        }, exe)).collect(Collectors.toList());
        List<String> prices = pricesFutures.stream().map(CompletableFuture::join).collect(Collectors.toList());

        System.out.println(prices.toString());
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " msecs");
    }

    @Test
    public void testApplyDiscounntExecutorAsyncListOfPricesParallel() {
        Executor exe = Executors.newFixedThreadPool(Math.min(shops.size(), 100), r -> {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        });

        long start = System.nanoTime();
        String product = "GALAXY s11";
        List<CompletableFuture<String>> pricesFutures = shops.parallelStream().map(sp -> CompletableFuture.supplyAsync(() -> {
            Discount.Code code = Discount.Code.values()[new Random().nextInt(Discount.Code.values().length)];
            return String.format("%s:%.2f:%s", sp.getName(), sp.calculatePrice(product), code);
        }, exe)).map(future -> future.thenApply(Quote::parse))
                .map(future -> future.thenCompose(quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), exe)))
                .collect(Collectors.toList());
        CompletableFuture[] newFutures = pricesFutures.toArray(new CompletableFuture[0]);

        CompletableFuture.allOf(newFutures).thenAccept(aVoid -> System.out.println("ALV :v"));

        List<String> prices = pricesFutures.stream().map(CompletableFuture::join).collect(Collectors.toList());

        System.out.println(prices.toString());
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " msecs");
    }

}
