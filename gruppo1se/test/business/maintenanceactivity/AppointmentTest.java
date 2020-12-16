/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.maintenanceactivity;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Calendar;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rosar
 */
public class AppointmentTest {
    private Appointment instance;
    public AppointmentTest() {
        instance = new Appointment(1, LocalDateTime.of(1999, Month.SEPTEMBER, 17, 10, 30),50);
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
     * Test of getActivityId method, of class Appointment.
     */
    @Test
    public void testGetActivityId() {
        int expResult = 1;
        int result = instance.getActivityId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getStartDateAndTime method, of class Appointment.
     */
    @Test
    public void testGetStartDateAndTime() {
        LocalDateTime expResult = LocalDateTime.of(1999, Month.SEPTEMBER, 17, 10, 30);
        LocalDateTime result = instance.getStartDateAndTime();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDuration method, of class Appointment.
     */
    @Test
    public void testGetDuration() {
        int expResult = 50;
        int result = instance.getDuration();
        assertEquals(expResult, result);
    }


    /**
     * Test of equals method, of class Appointment.
     */
    @Test
    public void testEqualsSameInstance() {
        Object obj = instance;
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of equals method, of class Appointment.
     */
    @Test
    public void testEqualsSameAttributes() {
        Object obj = new Appointment(1, LocalDateTime.of(1999, Month.SEPTEMBER, 17, 10, 30),50);
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of equals method, of class Appointment.
     */
    @Test
    public void testEqualsDifferentAttributesDate() {
        Object obj = new Appointment(1, LocalDateTime.of(2000, Month.SEPTEMBER, 17, 10, 30),50);
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of equals method, of class Appointment.
     */
    @Test
    public void testEqualsDifferentAttributesMonth() {
        Object obj = new Appointment(1, LocalDateTime.of(1999, Month.DECEMBER, 17, 10, 30),50);
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of equals method, of class Appointment.
     */
    @Test
    public void testEqualsDifferentAttributesDay() {
        Object obj = new Appointment(1, LocalDateTime.of(1999, Month.SEPTEMBER, 18, 10, 30),50);
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of equals method, of class Appointment.
     */
    @Test
    public void testEqualsDifferentAttributesHour() {
        Object obj = new Appointment(1, LocalDateTime.of(1999, Month.SEPTEMBER, 17, 9, 30),50);
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }
    
        /**
     * Test of equals method, of class Appointment.
     */
    @Test
    public void testEqualsDifferentAttributesMinute() {
        Object obj = new Appointment(1, LocalDateTime.of(1999, Month.SEPTEMBER, 17, 10, 31),50);
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of equals method, of class Appointment.
     */
    @Test
    public void testEqualsDifferentAttributesId() {
        Object obj = new Appointment(2, LocalDateTime.of(1999, Month.SEPTEMBER, 17, 10, 30),50);
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of equals method, of class Appointment.
     */
    @Test
    public void testEqualsDifferentAttributesDuration() {
        Object obj = new Appointment(1, LocalDateTime.of(1999, Month.SEPTEMBER, 17, 10, 30),40);
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of compareTo method, of class Appointment.
     */
    @Test
    public void testCompareTo() {
        Appointment o = new Appointment(2, LocalDateTime.of(1999,Month.SEPTEMBER , 17, 10, 30), 50);
        int expResult = -1;
        int result = instance.compareTo(o);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Appointment.
     */
    @Test
    public void testToString() {
        String expResult = "Appointment{" + "activityId=" + instance.getActivityId() + ", startDateAndTime="
                + instance.getStartDateAndTime() + ", duration=" + instance.getDuration() + '}';;
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
