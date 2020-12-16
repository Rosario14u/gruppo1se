/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.user;

import exception.DateException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.Calendar;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author gorra
 */
public class WeekConverterTest {

    public WeekConverterTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    
    /**
     * This test asserts that the method getStartEndDate correctly throws a a
     * DateException when the parameter numberWeek is wrong (numberWeek = 0)
     */
    @Test(expected = DateException.class)
    public void testWeekInvalidLowerBound() throws DateException {
        int numberWeek = 0;
        WeekConverter.getStartEndDate(numberWeek, 2020);
    }

    
    /**
     * This test asserts that the method getStartEndDate correctly throws a a
     * DateException when the parameter numberWeek is wrong (numberWeek > total
     * number of weeks in year + 1)
     */
    @Test(expected = DateException.class)
    public void testWeekInvalidUpperBound() throws DateException {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2020);
        int numberWeek = cal.getWeeksInWeekYear() + 1;
        WeekConverter.getStartEndDate(numberWeek, 2020);
    }

    
    /**
     * This test asserts that the method getStartEndDate correctly returns the
     * Monday (day of the previous year (30/12/2019)) and the Sunday (5/1/2020)
     * of the first week of 2020
     */
    @Test
    public void testWeekValidLowerBound() {
        try {
            int numberWeek = 1;
            List<LocalDate> actualDates = WeekConverter.getStartEndDate(numberWeek, 2020);
            assertEquals(LocalDate.of(2019, Month.DECEMBER, 30), actualDates.get(0));
            assertEquals(LocalDate.of(2020, Month.JANUARY, 5), actualDates.get(1));
        } catch (DateException ex) {
            fail(ex.getClass().getName() + " - " + ex.getMessage());
        }
    }

    
    /**
     * This test asserts that the method getStartEndDate correctly returns the
     * Monday (28/12/2020) and the Sunday (day of the next year (3/1/2021)) of
     * the last week of 2020
     */
    @Test
    public void testWeekValidUpperBound() {
        try {
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.YEAR, 2020);
            int numberWeek = cal.getWeeksInWeekYear();
            List<LocalDate> actualDates = WeekConverter.getStartEndDate(numberWeek, 2020);
            assertEquals(LocalDate.of(2020, Month.DECEMBER, 28), actualDates.get(0));
            assertEquals(LocalDate.of(2021, Month.JANUARY, 3), actualDates.get(1));
        } catch (DateException ex) {
            fail(ex.getClass().getName() + " - " + ex.getMessage());
        }
    }

    
    /**
     * This test asserts that the method getWeek correctly returns 
     * the correct number of week (last day of year)
     */
    @Test
    public void testGetWeek() {
        LocalDate date = LocalDate.of(2020, 12, 31);
        assertEquals(53, WeekConverter.getWeek(date));
    }

    
    /**
     * This test asserts that the method getWeek correctly returns 
     * the correct number of week (first day of 2021 which belongs 
     * to the last week of 2020)
     */
    @Test
    public void testGetWeek2() {
        LocalDate date = LocalDate.of(2021, 1, 1);
        assertEquals(53, WeekConverter.getWeek(date));
    }

    
    /**
     * This test asserts that the method getNumberOfWeeksInYear 
     * correctly returns the total number of weeks in year
     */
    @Test
    public void testGetNumberOfWeeksInYear() {
        assertEquals(53, WeekConverter.getNumberOfWeeksInYear(LocalDate.of(2020, 12, 31)));
    }
    
    
    /**
     * This test asserts that the method getNumberOfWeeksInYear 
     * correctly returns the total number of weeks in year 
     * (the passed date is 1/1/2021 which is part of the 53rd week of 2020)
     */
    @Test
    public void testGetNumberOfWeeksInYear2() {
        assertEquals(53, WeekConverter.getNumberOfWeeksInYear(LocalDate.of(2021, 1, 1)));
    }
    
    /**
     * This method assert tht getYearOfWeek returns year based on the week
     */
    @Test
    public void testGetYearOfWeekSameYear(){
        int expectedYear = 2020;
        int resultedYear = WeekConverter.getYear(LocalDate.parse("2020-12-12"));
        assertEquals(expectedYear,resultedYear);
    }
    
    /**
     * This method assert tht getYearOfWeek returns year based on the week
     */
    @Test
    public void testGetYearOfWeekDifferentYear(){
        int expectedYear = 2020;
        int resultedYear = WeekConverter.getYear(LocalDate.parse("2021-01-01"));
        assertEquals(expectedYear,resultedYear);
    }
    
    /**
     * This test asserts that the method getDayOfWeek correctly 
     * returns the desired day of the week
     */   
    @Test
    public void testGetDayOfWeek(){
        LocalDate date = LocalDate.of(2020, 12, 31);
        // 3/1/2021 Sunday
        LocalDate expectedDate = LocalDate.of(2021, 1, 3);
        assertEquals(expectedDate, WeekConverter.getDayOfWeek(date, DayOfWeek.SUNDAY));
    }
    
}
