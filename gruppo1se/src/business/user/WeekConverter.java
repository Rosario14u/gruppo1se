/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.user;

import exception.DateException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author gorra
 */
public class WeekConverter {
    public static List<LocalDate> getStartEndDate(int week, int year) throws DateException{  
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);   
        int weeksOfYear = cal.getWeeksInWeekYear();
        if(week > weeksOfYear || week < 1 ){
            throw new DateException();
        }
        List<LocalDate> startEndDatesOfWeek = new ArrayList<>();
        
        try{
            cal.set(Calendar.WEEK_OF_YEAR, week);
            cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            startEndDatesOfWeek.add((cal.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()));
            cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
            startEndDatesOfWeek.add((cal.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()));
            System.out.println(startEndDatesOfWeek.toString());
        }catch(ArrayIndexOutOfBoundsException ex){
            throw new DateException();
        }
        
        return startEndDatesOfWeek;
    }
}