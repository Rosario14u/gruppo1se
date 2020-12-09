/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.maintenanceactivity;

import business.maintenanceactivity.Appointment;
import exception.AppointmentException;
import exception.DateException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import persistence.database.ConnectionDB;

/**
 *
 * @author rosar
 */
public class EmployeeAppointmentDAOImplTest {
    private static Connection conn;
    private static final String INSERT_APPOINTMENT = "INSERT INTO EmployeeAppointment"
            + "(activityId,username,startDateTime,duration) VALUES(?,?,?,?)";
    private static final String DELETE_APPOINTMENT = "DELETE FROM EmployeeAppointment"
            + " WHERE startDateTime between ? and ?";
    private final EmployeeAppointmentDAO employeeAppointmentDAO;
    private final DateTimeFormatter formatter; 
    public EmployeeAppointmentDAOImplTest() {
        employeeAppointmentDAO = new EmployeeAppointmentDAOImpl();
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    }
    
    @BeforeClass
    public static void setUpClass() {
        try {
            conn = ConnectionDB.getInstanceConnection().getConnection();
            conn.setAutoCommit(false);
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeAppointmentDAOImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @AfterClass
    public static void tearDownClass() {
        try {
          conn.setAutoCommit(true);
          conn.close();
        } catch (SQLException ex) {
          Logger.getLogger(EmployeeAppointmentDAOImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
         try {
            conn.rollback();
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeAppointmentDAOImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void testGetEmployeeAvailabilitySuccessfulRetrieve() {
        try{
            List<Appointment> expectedList = initializeList("2050-12-11 00:00:00",
                    "2050-12-12 00:00:00","2050-12-13 00:00:00");
            initializeDeleteInsert(expectedList);
            List<Appointment> resultedList = employeeAppointmentDAO.getEmployeeAvailability("username",
                    LocalDate.parse("2050-12-11"), LocalDate.parse("2050-12-13"));
            assertEquals("Appointment list not equal error",expectedList.size(),resultedList.size());
            Collections.sort(resultedList);
            assertEquals("getEmployeeAvailabilty error",expectedList,resultedList);
        }catch(SQLException ex){
            fail("SQLException");
        } catch (AppointmentException ex) {
            fail("AppointmentException");
        } catch (DateException ex) {
            fail("DateException");
        }
    }
    
    @Test
    public void testGetEmployeeAvailabilityNotIncluded() {
        try{
            List<Appointment> expectedList = initializeList("2050-12-11 00:00:00",
                    "2050-12-12 00:00:00","2050-12-13 00:00:00");
            delete_appointment(LocalDateTime.parse("2020-12-11 00:00:00",formatter),
                    LocalDateTime.parse("2020-12-13 00:00:00",formatter));
            List<Appointment> resultedList = employeeAppointmentDAO.getEmployeeAvailability("username",
                    LocalDate.parse("2050-12-11"), LocalDate.parse("2050-12-13"));
            assertTrue("getEmployeeAvailabiltyNotIncluded error", resultedList.isEmpty());
        }catch(SQLException ex){
            fail("SQLException");
        } catch (AppointmentException ex) {
            fail("AppointmentException");
        } catch (DateException ex) {
            fail("DateException");
        }
    }
    
    
    @Test(expected = DateException.class)
    public void testGetEmployeeAvailabilityWrongStartAndStop() throws DateException {
        try{
            List<Appointment> expectedList = initializeList("2050-12-11 00:00:00",
                    "2050-12-12 00:00:00","2050-12-13 00:00:00");
            initializeDeleteInsert(expectedList);
            List<Appointment> resultedList = employeeAppointmentDAO.getEmployeeAvailability("username",
                    LocalDate.parse("2050-12-13"), LocalDate.parse("2050-12-11"));
        }catch(SQLException ex){
            fail("SQLException");
        } catch (AppointmentException ex) {
            fail("AppointmentException");
        } 
    }
    
    @Test(expected = AppointmentException.class)
    public void testGetEmployeeAvailabilityUsernameNull() throws AppointmentException {
        try{
            List<Appointment> expectedList = initializeList("2050-12-11 00:00:00",
                    "2050-12-12 00:00:00","2050-12-13 00:00:00");
            initializeDeleteInsert(expectedList);
            List<Appointment> resultedList = employeeAppointmentDAO.getEmployeeAvailability(null,
                    LocalDate.parse("2050-12-11"), LocalDate.parse("2050-12-13"));
        }catch(SQLException ex){
            fail("SQLException");
        } catch (DateException ex) {
            fail("DateException");
        }
    }
    
    @Test(expected = AppointmentException.class)
    public void testGetEmployeeAvailabilityUsernameEmpty() throws AppointmentException {
        try{
            List<Appointment> expectedList = initializeList("2050-12-11 00:00:00",
                    "2050-12-12 00:00:00","2050-12-13 00:00:00");
            initializeDeleteInsert(expectedList);
            List<Appointment> resultedList = employeeAppointmentDAO.getEmployeeAvailability(" ",
                    LocalDate.parse("2050-12-11"), LocalDate.parse("2050-12-13"));
        }catch(SQLException ex){
            fail("SQLException");
        } catch (DateException ex) {
            fail("DateException");
        }
    }
    
    
    
    private void insert_appointment(int activityId, String username, LocalDateTime startDateTime,int duration) throws SQLException{
        PreparedStatement pstm = conn.prepareStatement(INSERT_APPOINTMENT);
        pstm.setInt(1, activityId);
        pstm.setString(2, username);
        pstm.setTimestamp(3, Timestamp.valueOf(startDateTime));
        pstm.setInt(4, duration);
        pstm.executeUpdate();
    }
    
    private void delete_appointment(LocalDateTime startDateTime, LocalDateTime endDateTime)
            throws SQLException{
        PreparedStatement pstm = conn.prepareStatement(DELETE_APPOINTMENT);
        pstm.setTimestamp(1, Timestamp.valueOf(startDateTime));
        pstm.setTimestamp(2, Timestamp.valueOf(endDateTime));
        pstm.executeUpdate();
    }
    
    private List<Appointment> initializeList(String dateTime1, String dateTime2, String dateTime3){
        return new ArrayList<>() {{
                add(new Appointment(1,LocalDateTime.parse(dateTime1,formatter),41));
                add(new Appointment(2,LocalDateTime.parse(dateTime2,formatter),42));
                add(new Appointment(3,LocalDateTime.parse(dateTime3,formatter),43));
            }};  
    }
    
    private void initializeDeleteInsert(List<Appointment> expectedList) throws SQLException{
        delete_appointment(expectedList.get(0).getStartDateAndTime(),
                    expectedList.get(2).getStartDateAndTime());
            for(Appointment appointment: expectedList){
                insert_appointment(appointment.getActivityId(),"username",
                        appointment.getStartDateAndTime(),appointment.getDuration());
            }
    }
    
    
}