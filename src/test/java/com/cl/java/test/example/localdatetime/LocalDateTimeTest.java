package com.cl.java.test.example.localdatetime;

import com.cl.java.test.example.date.NextWorkingDay;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;

import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;
import static java.time.temporal.TemporalAdjusters.nextOrSame;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

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

    @Test
    public void durationAndPeriod() {

        LocalTime time = LocalTime.of(15, 23, 22);
        LocalTime time2 = LocalTime.of(9, 2, 33);

        Duration d1 = Duration.between(time, time2);

        System.out.println(d1.toHours());

        LocalDate date = LocalDate.of(1992, 7, 28);
        LocalDate date2 = LocalDate.of(2012, 11, 17);

        Period period = Period.between(date, date2);

        System.out.println(period.getYears());

        Duration hoursByDay = Duration.ofDays(1);
        System.out.println(hoursByDay.toHours());

        Period weeks = Period.ofWeeks(2);
        System.out.println(weeks.getDays());

    }

    @Test
    public void manipulatingLocalDateTime() {

        LocalDate date1 = LocalDate.of(2019, 2, 12);
        System.out.println(date1);
        LocalDate date2 = date1.plusWeeks(2);
        System.out.println(date2);
        LocalDate date3 = date2.minusYears(2);
        System.out.println(date3);
        LocalDate date4 = date3.plusMonths(6);
        System.out.println(date4);

        LocalDate date = LocalDate.of(2014, 3, 18);
        date = date.with(ChronoField.MONTH_OF_YEAR, 9);
        date = date.plusYears(2).minusDays(10);
        date.withYear(2011);
        assertEquals(date.toString(), "2016-09-08");
    }

    @Test
    public void customTemporalAdjuster() {
        LocalDate date = LocalDate.now();
        date.with(new NextWorkingDay());
        assertNotEquals(DayOfWeek.SATURDAY, date.getDayOfWeek());
    }

    @Test
    public void jumpTemporalDates() {

        LocalDate date1 = LocalDate.of(2019, 3, 11);
        LocalDate date2 = date1.with(nextOrSame(DayOfWeek.SUNDAY));
        LocalDate date3 = date2.with(lastDayOfMonth());

        System.out.println(date1);

        System.out.println(date2);

        System.out.println(date3);

    }

    @Test
    public void parsingPrintingDate() {
        LocalDate date = LocalDate.now();
        String s1 = date.format(DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println(s1);
        String s2 = date.format(DateTimeFormatter.ISO_LOCAL_DATE);
        System.out.println(s2);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String s3 = date.format(formatter);
        System.out.println(s3);
        LocalDate date2 = LocalDate.parse("28/07/1992", formatter);
        assertEquals(1992, date2.getYear());
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
