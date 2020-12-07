/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.user;

import exception.DateException;
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
import persistence.maintenanceactivity.MaintenanceActivityDAOImpl;
import persistence.maintenanceactivity.RequiredMaterialForMaintenanceDAOImpl;
import persistence.maintenanceactivity.SiteDaoImpl;

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
     * Test of getStartEndDate method, of class WeekConverter.
     */
    @Test(expected = DateException.class)
    public void testWeekInvalidLowerBound() throws DateException {
        int numberWeek = 0;
        WeekConverter.getStartEndDate(numberWeek, 2020);
    }
    
    @Test(expected = DateException.class)
    public void testWeekInvalidUpperBound() throws DateException {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2020);   
        int numberWeek = cal.getWeeksInWeekYear()+1;
        WeekConverter.getStartEndDate(numberWeek, 2020);
    }
    
    @Test
    public void testWeekValidLowerBound() throws DateException {
        int numberWeek = 1;
        List<LocalDate> actualDates = WeekConverter.getStartEndDate(numberWeek, 2020);
        assertEquals(LocalDate.of(2019, Month.DECEMBER, 30), actualDates.get(0));
        assertEquals(LocalDate.of(2020, Month.JANUARY, 5), actualDates.get(1));
    }
    
    @Test
    public void testWeekValidUpperBound() throws DateException {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2020);   
        int numberWeek = cal.getWeeksInWeekYear();
        List<LocalDate> actualDates = WeekConverter.getStartEndDate(numberWeek, 2020);
        assertEquals(LocalDate.of(2020, Month.DECEMBER, 28), actualDates.get(0));
        assertEquals(LocalDate.of(2021, Month.JANUARY, 3), actualDates.get(1));
    }
    
    @Test
    public void testGetWeek(){
        LocalDate date = LocalDate.of(2020, 12, 31);
        assertEquals(53, WeekConverter.getWeek(date));
    }
    
    @Test
    public void testGetWeek1(){
        LocalDate date = LocalDate.of(2020, 1, 1);
        assertEquals(1, WeekConverter.getWeek(date));
    }
    
    @Test
    public void testGetNumberOfWeeksInYear(){
        assertEquals(53, WeekConverter.getNumberOfWeeksInYear(2020));
        assertEquals(52, WeekConverter.getNumberOfWeeksInYear(2021));
        assertEquals(52, WeekConverter.getNumberOfWeeksInYear(2022));
    }
}
