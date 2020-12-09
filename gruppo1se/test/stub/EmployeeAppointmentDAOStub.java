/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stub;

import business.maintenanceactivity.Appointment;
import exception.AppointmentException;
import exception.DateException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import persistence.maintenanceactivity.EmployeeAppointmentDAO;

/**
 *
 * @author gorra
 */
public class EmployeeAppointmentDAOStub implements EmployeeAppointmentDAO {

    @Override
    public List<Appointment> getEmployeeAvailability(String username, LocalDate startDate, LocalDate endDate)
            throws AppointmentException, DateException {
        
        if(startDate.isAfter(endDate))
            throw new DateException();
        else if(username == null || username.equals(""))
            throw new AppointmentException();
        else if(username.equals("username1")){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-gg HH:mm:ss");
            return new ArrayList<>(){{
                add(new Appointment(1, LocalDateTime.parse("2020-12-28 00:00:00", formatter), 20));
                add(new Appointment(2, LocalDateTime.parse("2020-12-29 00:00:00", formatter), 40));
            }};
        }else if(username.equals("username2")){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-gg HH:mm:ss");
            return new ArrayList<>(){{
                add(new Appointment(3, LocalDateTime.parse("2020-12-30 00:00:00", formatter), 60));
                add(new Appointment(4, LocalDateTime.parse("2020-12-31 00:00:00", formatter), 80));
            }};
        }
        else
            return new ArrayList<>();
    }
        
}