/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.maintenanceactivity;

import business.maintenanceactivity.Appointment;
import exception.AppointmentException;
import exception.DateException;
import exception.NotValidParameterException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.sql.ResultSet;
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
    private static final String DELETE_APPOINTMENT_BY_ID = "DELETE FROM EmployeeAppointment"
            + " WHERE activityId = ?";
    private static final String SELECT_APPOINTMENT = "SELECT * FROM EmployeeAppointment where"
            + " username = ? and startDateTime between ? and ? order by activityId";
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
    
    
    /*=============================================testSetEmployeeAvailability=================================================================*/
    
    /* test of getEmployeeAvailability developed by Rosario Gaeta */
    /**
     * This test method assert that getEmployeeAvailability correctly returns a list of Appointments.
     */
    @Test
    public void testGetEmployeeAvailabilitySuccessfulRetrieve() {
        try{
            Appointment appointment1 = new Appointment(1,LocalDateTime.parse("2050-12-11 00:00:00",formatter),41);
            Appointment appointment2 = new Appointment(2,LocalDateTime.parse("2050-12-12 00:00:00",formatter),42);
            Appointment appointment3 = new Appointment(3,LocalDateTime.parse("2050-12-13 23:59:59",formatter),43);
            List<Appointment> expectedList = initializeList(appointment1, appointment2,appointment3);
            initializeDeleteInsert("username",expectedList);
            List<Appointment> resultedList = employeeAppointmentDAO.getEmployeeAvailability("username",
                    appointment1.getStartDateAndTime().toLocalDate(), appointment3.getStartDateAndTime().toLocalDate());
            assertEquals("Appointment list not equal error",expectedList.size(),resultedList.size());
            Collections.sort(resultedList);
            assertEquals("getEmployeeAvailabilty error",expectedList,resultedList);
        }catch(SQLException ex){
            fail("SQLException");
        } catch (AppointmentException ex) {
            fail("AppointmentException");
        } catch (DateException ex) {
            fail("DateException");
        }catch (NotValidParameterException ex) {
            fail("NotValidParameterException");
        }
    }
    
    /**
     * This test method assert that getEmployeeAvailability correctly returns an empty list when<br>
     * there aren't appointments for that maintainer in the specified range of date.
     */
    @Test
    public void testGetEmployeeAvailabilityNotIncluded() {
        try{
            Appointment appointment1 = new Appointment(1,LocalDateTime.parse("2050-12-11 00:00:00",formatter),41);
            Appointment appointment2 = new Appointment(2,LocalDateTime.parse("2050-12-12 00:00:00",formatter),42);
            Appointment appointment3 = new Appointment(3,LocalDateTime.parse("2050-12-13 23:59:59",formatter),43);
            List<Appointment> expectedList = initializeList(appointment1, appointment2,appointment3);
            deleteAppointment(appointment1.getStartDateAndTime(),
                    appointment3.getStartDateAndTime());
            List<Appointment> resultedList = employeeAppointmentDAO.getEmployeeAvailability("username",
                    appointment1.getStartDateAndTime().toLocalDate(), appointment3.getStartDateAndTime().toLocalDate());
            assertTrue("getEmployeeAvailabiltyNotIncluded error", resultedList.isEmpty());
        }catch(SQLException ex){
            fail("SQLException");
        } catch (AppointmentException ex) {
            fail("AppointmentException");
        } catch (DateException ex) {
            fail("DateException");
        }catch (NotValidParameterException ex) {
            fail("NotValidParameterException");
        }
    }
    
    /**
     * This test method assert that getEmployeeAvailability correctly raises a DateException when<br>
     * start date is after end date.
     * @throws exception.DateException
     */
    @Test(expected = DateException.class)
    public void testGetEmployeeAvailabilityWrongStartAndStop() throws DateException {
        try{
            Appointment appointment1 = new Appointment(1,LocalDateTime.parse("2050-12-11 00:00:00",formatter),41);
            Appointment appointment2 = new Appointment(2,LocalDateTime.parse("2050-12-12 00:00:00",formatter),42);
            Appointment appointment3 = new Appointment(3,LocalDateTime.parse("2050-12-13 23:59:59",formatter),43);
            List<Appointment> expectedList = initializeList(appointment1, appointment2,appointment3);
            initializeDeleteInsert("username",expectedList);
            List<Appointment> resultedList = employeeAppointmentDAO.getEmployeeAvailability("username",
                    appointment3.getStartDateAndTime().toLocalDate(), appointment1.getStartDateAndTime().toLocalDate());
        }catch(SQLException ex){
            fail("SQLException");
        } catch (AppointmentException ex) {
            fail("AppointmentException");
        } catch (NotValidParameterException ex) {
            fail("NotValidParameterException");
        }
    }
    
    /**
     * This test method assert that getEmployeeAvailability correctly raises a AppointmentException when<br>
     * a null username is passed.
     * @throws AppointmentException 
     */
    @Test(expected = AppointmentException.class)
    public void testGetEmployeeAvailabilityUsernameNull() throws AppointmentException {
        try{
            Appointment appointment1 = new Appointment(1,LocalDateTime.parse("2050-12-11 00:00:00",formatter),41);
            Appointment appointment2 = new Appointment(2,LocalDateTime.parse("2050-12-12 00:00:00",formatter),42);
            Appointment appointment3 = new Appointment(3,LocalDateTime.parse("2050-12-13 23:59:59",formatter),43);
            List<Appointment> expectedList = initializeList(appointment1, appointment2,appointment3);
            initializeDeleteInsert("username",expectedList);
            List<Appointment> resultedList = employeeAppointmentDAO.getEmployeeAvailability(null,
                    appointment1.getStartDateAndTime().toLocalDate(), appointment3.getStartDateAndTime().toLocalDate());
        }catch(SQLException ex){
            fail("SQLException");
        } catch (DateException ex) {
            fail("DateException");
        }catch (NotValidParameterException ex) {
            fail("NotValidParameterException");
        }
    }
    
    /**
     * This test method assert that getEmployeeAvailability correctly raises a AppointmentException when<br>
     * an empty username string is passed.
     * @throws AppointmentException 
     */
    @Test(expected = AppointmentException.class)
    public void testGetEmployeeAvailabilityUsernameEmpty() throws AppointmentException {
        try{
            Appointment appointment1 = new Appointment(1,LocalDateTime.parse("2050-12-11 00:00:00",formatter),41);
            Appointment appointment2 = new Appointment(2,LocalDateTime.parse("2050-12-12 00:00:00",formatter),42);
            Appointment appointment3 = new Appointment(3,LocalDateTime.parse("2050-12-13 23:59:59",formatter),43);
            List<Appointment> expectedList = initializeList(appointment1, appointment2,appointment3);
            initializeDeleteInsert("username",expectedList);
            List<Appointment> resultedList = employeeAppointmentDAO.getEmployeeAvailability(" ",
                    appointment1.getStartDateAndTime().toLocalDate(), appointment3.getStartDateAndTime().toLocalDate());
        }catch(SQLException ex){
            fail("SQLException");
        } catch (DateException ex) {
            fail("DateException");
        }catch (NotValidParameterException ex) {
            fail("NotValidParameterException");
        }
    }
    
    //=============================================testSetEmployeeAvailability=================================================================
    
    /* test of setEmployeeAvailability developed by Rosario Gaeta */
    
    
    /**
     * This method assert that addEmployeeAvailability correctly stores a list of appointments<br>
     * in the database and returns true.
     */
    @Test
    public void testAddEmployeeAvailabilitySuccessful() {
        try{
            Appointment appointment1 = new Appointment(1,LocalDateTime.parse("2050-12-11 00:00:00",formatter),41);
            Appointment appointment2 = new Appointment(2,LocalDateTime.parse("2050-12-12 00:00:00",formatter),42);
            Appointment appointment3 = new Appointment(3,LocalDateTime.parse("2050-12-13 23:59:59",formatter),43);
            List<Appointment> expectedList = initializeList(appointment1, appointment2,appointment3);
            deleteAppointment(appointment1.getStartDateAndTime(),
                    appointment3.getStartDateAndTime());
            boolean retVal = employeeAppointmentDAO.addEmployeeAvailability("username", expectedList);
            assertAppointment("username", expectedList);
        }catch(SQLException ex){
           Logger.getLogger(EmployeeAppointmentDAOImplTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (AppointmentException ex) {
            fail("AppointmentException");
        }
    }
    
    /**
     * This method assert that addEmployeeAvailability correctly returns false if<br>
     * an empty list of appointments is passed.
     */
    @Test
    public void testAddEmployeeAvailabilityUnsuccessfulEmpty() {
        try{
            boolean retVal = employeeAppointmentDAO.addEmployeeAvailability("username", new ArrayList<>());
            assertFalse("Error SuccessfulEmpty",retVal);
        }catch (AppointmentException ex) {
            fail("AppointmentException");
        }
    }
    
    /**
     * This method assert that addEmployeeAvailability correctly raises an AppointmentException if<br>
     * appointments are already present.
     * @throws exception.AppointmentException
     */
    @Test(expected = AppointmentException.class)
    public void testAddEmployeeAvailabilityUnsuccessful() throws AppointmentException {
        try{
            Appointment appointment1 = new Appointment(1,LocalDateTime.parse("2050-12-11 00:00:00",formatter),41);
            Appointment appointment2 = new Appointment(2,LocalDateTime.parse("2050-12-12 00:00:00",formatter),42);
            Appointment appointment3 = new Appointment(3,LocalDateTime.parse("2050-12-13 23:59:59",formatter),43);
            List<Appointment> expectedList = initializeList(appointment1, appointment2,appointment3);
            initializeDeleteInsert("username",expectedList);
            boolean retVal = employeeAppointmentDAO.addEmployeeAvailability("username", expectedList);
        }catch(SQLException ex){
            fail("SQLException");
        }
    }
    
    
    /**
     * This method assert that addEmployeeAvailability correctly raises an AppointmentException if<br>
     * a null list is passed.
     * @throws exception.AppointmentException
     */
    @Test(expected = AppointmentException.class)
    public void testAddEmployeeAvailabilityAppointmentNull() throws AppointmentException {
        employeeAppointmentDAO.addEmployeeAvailability("username", null);
    }
    
    /**
     * This method assert that addEmployeeAvailability correctly raises an AppointmentException if<br>
     * a null username is passed.
     * @throws exception.AppointmentException
     */ 
    @Test(expected = AppointmentException.class)
    public void testAddEmployeeAvailabilityUsernameNull() throws AppointmentException {
        try{
            Appointment appointment1 = new Appointment(1,LocalDateTime.parse("2050-12-11 00:00:00",formatter),41);
            Appointment appointment2 = new Appointment(2,LocalDateTime.parse("2050-12-12 00:00:00",formatter),42);
            Appointment appointment3 = new Appointment(3,LocalDateTime.parse("2050-12-13 23:59:59",formatter),43);
            List<Appointment> expectedList = initializeList(appointment1, appointment2,appointment3);
            deleteAppointment(appointment1.getStartDateAndTime(),
                    appointment3.getStartDateAndTime());
            employeeAppointmentDAO.addEmployeeAvailability(null,expectedList);
        }catch(SQLException ex){
            fail("SQLException");
        }
    }
    
    /**
     * This method assert that addEmployeeAvailability correctly raises an AppointmentException if<br>
     * a empty username is passed.
     * @throws exception.AppointmentException
     */ 
    @Test(expected = AppointmentException.class)
    public void testAddEmployeeAvailabilityUsernameEmpty() throws AppointmentException {
        try{
            Appointment appointment1 = new Appointment(1,LocalDateTime.parse("2050-12-11 00:00:00",formatter),41);
            Appointment appointment2 = new Appointment(2,LocalDateTime.parse("2050-12-12 00:00:00",formatter),42);
            Appointment appointment3 = new Appointment(3,LocalDateTime.parse("2050-12-13 23:59:59",formatter),43);
            List<Appointment> expectedList = initializeList(appointment1, appointment2,appointment3);
            deleteAppointment(appointment1.getStartDateAndTime(),
                    appointment3.getStartDateAndTime());
            employeeAppointmentDAO.addEmployeeAvailability(" ",expectedList);
            
        }catch(SQLException ex){
            fail("SQLException");
        }
    }
    
    //=============================================testGetDurationOfAssignedActivity=================================================================
    
    /* test of getDurationOfAssignedActivity developed by Rosario Gaeta */
    
    /**
     * This method assert that getDurationOfAssignedActivity correctly returns the sum of duration appoitments<br>
     * associated with a specific Maintenance activity.
     */
    @Test
    public void testGetDurationOfAssignedActivitySucceful() {
        try{
            int duration = 40;
            int expectedSum = duration*3;
            Appointment appointment1 = new Appointment(1,LocalDateTime.parse("2050-12-11 00:00:00",formatter),duration);
            Appointment appointment2 = new Appointment(1,LocalDateTime.parse("2050-12-12 12:00:00",formatter),duration);
            Appointment appointment3 = new Appointment(1,LocalDateTime.parse("2050-12-13 23:59:59",formatter),duration);
            List<Appointment> expectedList = initializeList(appointment1, appointment2,appointment3);
            deleteAppointmentById(1);
            for(Appointment appointment: expectedList){
                insertAppointment(appointment.getActivityId(), "username", appointment.getStartDateAndTime(), appointment.getDuration());
            }
            int returnedSum = employeeAppointmentDAO.getDurationOfAssignedActivity(1);
            assertEquals("sum of duration not equal",returnedSum,expectedSum);
        }catch(SQLException ex){
           fail("SQLException");
        } catch (AppointmentException ex) {
           fail("AppointmentException");
        }
    }
    
    /**
     * This method assert that getDurationOfAssignedActivity correctly returns 0 if there aren't appoitments<br>
     * associated with a specific Maintenance activity.
     */
    @Test
    public void testGetDurationOfAssignedActivitySuccefulZero() {
        try{
            int duration = 40;
            int expectedSum = 0;
            Appointment appointment1 = new Appointment(1,LocalDateTime.parse("2050-12-11 00:00:00",formatter),duration);
            Appointment appointment2 = new Appointment(1,LocalDateTime.parse("2050-12-11 12:00:00",formatter),duration);
            Appointment appointment3 = new Appointment(1,LocalDateTime.parse("2050-12-11 23:59:59",formatter),duration);
            List<Appointment> expectedList = initializeList(appointment1, appointment2,appointment3);
            deleteAppointmentById(1);
            int returnedSum = employeeAppointmentDAO.getDurationOfAssignedActivity(1);
            assertEquals("sum of duration not equal",returnedSum,expectedSum);
        }catch(SQLException ex){
           fail("SQLException");
        } catch (AppointmentException ex) {
            fail("AppointmentException");
        }
    }
    
    
    
    //=============================================function of utility=================================================================
    
    private void insertAppointment(int activityId, String username, LocalDateTime startDateTime,int duration) throws SQLException{
        PreparedStatement pstm = conn.prepareStatement(INSERT_APPOINTMENT);
        pstm.setInt(1, activityId);
        pstm.setString(2, username);
        pstm.setTimestamp(3, Timestamp.valueOf(startDateTime));
        pstm.setInt(4, duration);
        pstm.executeUpdate();
    }
    
    private void deleteAppointment(LocalDateTime startDateTime, LocalDateTime endDateTime)
            throws SQLException{
        PreparedStatement pstm = conn.prepareStatement(DELETE_APPOINTMENT);
        pstm.setTimestamp(1, Timestamp.valueOf(startDateTime));
        pstm.setTimestamp(2, Timestamp.valueOf(endDateTime));
        pstm.executeUpdate();
    }
    
    private void deleteAppointmentById(int activityId)
            throws SQLException{
        PreparedStatement pstm = conn.prepareStatement(DELETE_APPOINTMENT_BY_ID);
        pstm.setInt(1, activityId);
        pstm.executeUpdate();
    }
    
    
    private List<Appointment> initializeList(Appointment appointment1, Appointment appointment2, Appointment appointment3){
        return new ArrayList<>() {{
                add(appointment1);
                add(appointment2);
                add(appointment3);
            }};  
    }
    
    private void assertAppointment(String username, List<Appointment> expectedList) throws SQLException{
        PreparedStatement pstm = conn.prepareStatement(SELECT_APPOINTMENT);
        pstm.setString(1, username);
        pstm.setTimestamp(2, Timestamp.valueOf(expectedList.get(0).getStartDateAndTime()));
        pstm.setTimestamp(3, Timestamp.valueOf(expectedList.get(2).getStartDateAndTime()));
        ResultSet rs = pstm.executeQuery();
        boolean isEmpty = true;
        int i = 0;
        while(rs.next() && i < expectedList.size()){
            isEmpty = false;
            assertEquals("assert appointment username error",username, rs.getString("username"));
            assertEquals("assert appointment activityId error",expectedList.get(i).getActivityId(), rs.getInt("activityId"));
            assertEquals("assert appointment startDateTime error",Timestamp.valueOf(expectedList.get(i).getStartDateAndTime()), rs.getTimestamp("startDateTime"));
            assertEquals("assert appointment duration error",expectedList.get(i).getDuration(), rs.getInt("duration"));
            i++;
        }
        assertEquals("not equal", i, expectedList.size());
        assertFalse("isEmpty",isEmpty);
        assertTrue("is after last",rs.isAfterLast());
    }
    
    private void initializeDeleteInsert(String username, List<Appointment> expectedList) throws SQLException{
        deleteAppointment(expectedList.get(0).getStartDateAndTime(),
                    expectedList.get(2).getStartDateAndTime());
            for(Appointment appointment: expectedList){
                insertAppointment(appointment.getActivityId(),username,
                        appointment.getStartDateAndTime(),appointment.getDuration());
            }
    }
    
    
}
