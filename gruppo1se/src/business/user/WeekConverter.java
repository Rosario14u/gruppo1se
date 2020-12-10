/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.user;

import exception.DateException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author gorra
 */
public class WeekConverter {
        public static List<LocalDate> getStartEndDate(int week, int year) throws DateException{  
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);   
        int weeksOfYear = calendar.getWeeksInWeekYear();
        if(week > weeksOfYear || week < 1 ){
            throw new DateException();
        }
        List<LocalDate> startEndDatesOfWeek = new ArrayList<>();
        
        try{
            calendar.set(Calendar.WEEK_OF_YEAR, week);
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            startEndDatesOfWeek.add((calendar.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()));
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
            startEndDatesOfWeek.add((calendar.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()));
            System.out.println(startEndDatesOfWeek.toString());
        }catch(ArrayIndexOutOfBoundsException ex){
            throw new DateException();
        }
        
        return startEndDatesOfWeek;
    }
    
    
    public static int getWeek(LocalDate date){
        Locale userLocale = Locale.ITALY;
        WeekFields weekNumbering = WeekFields.of(userLocale);        
        return date.get(weekNumbering.weekOfWeekBasedYear());
    }
    
    
    public static int getNumberOfWeeksInYear(int year){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);   
        return calendar.getWeeksInWeekYear();
    }
    
    public static LocalDate getDayOfWeek(LocalDate date, DayOfWeek day){
        return date.with(day);
    } 
}