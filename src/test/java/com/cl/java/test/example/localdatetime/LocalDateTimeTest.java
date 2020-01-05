package com.cl.java.test.example.localdatetime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;

@RunWith(JUnit4.class)
public class LocalDateTimeTest {

    @Test
    public void testLocaDate() {

        LocalDate date = LocalDate.of(1992, 7, 28);
        printDate(date);

        LocalDate nowDate = LocalDate.now();
        printDate(nowDate);
    }

    @Test
    public void testLocalTime() {

        LocalTime time = LocalTime.of(15, 23, 22);
        printTime(time);

    }

    @Test
    public void testParseDateTime() {

        LocalTime time = LocalTime.parse("09:22:45", DateTimeFormatter.ofPattern("HH:mm:ss"));
        LocalDate date = LocalDate.parse("13/09/2021", DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        printTime(time);
        printDate(date);

    }

    public void printTime(LocalTime time) {
        System.out.println("================= TIME PRINT =================");
        int hour = time.getHour();
        int minute = time.getMinute();
        int second = time.getSecond();
        System.out.println("Hour " + hour);
        System.out.println("Minute " + minute);
        System.out.println("Second " + second);
    }

    public void printDate(LocalDate date) {
        System.out.println("================= DATE PRINT =================");
        int year = date.getYear();
        Month month = date.getMonth();
        int day = date.getDayOfMonth();
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        int len = date.lengthOfMonth();
        boolean leap = date.isLeapYear();
        System.out.println("Year " + year);
        System.out.println("Month " + month.toString());
        System.out.println("Day " + day);
        System.out.println("Day of Week " + dayOfWeek);
        System.out.println("Lenght Month " + len);
        System.out.println("Leap Year ? " + leap);
        System.out.println("Temporaly Field : " + date.get(ChronoField.YEAR));
    }

}
