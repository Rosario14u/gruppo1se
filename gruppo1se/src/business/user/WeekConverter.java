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
 * The {@code WeekConverter} class
 *
 * @author gorra
 */
public class WeekConverter {

    /**
     * This method returns the date of the firt and the 
     * last day of the given week and the givern year.
     * 
     * @param week Week to know start and end date.
     * @param year Year relative to week.
     * @return List of {@code LocalDate} values. The first 
     * parameter is the firt day of the week and the last 
     * parameter is the last day of the given week. 
     * @throws DateException 
     */
    public static List<LocalDate> getStartEndDate(int week, int year) throws DateException {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        int weeksOfYear = calendar.getWeeksInWeekYear();
        if (week > weeksOfYear || week < 1) {
            throw new DateException();
        }
        List<LocalDate> startEndDatesOfWeek = new ArrayList<>();

        try {
            calendar.set(Calendar.WEEK_OF_YEAR, week);
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            startEndDatesOfWeek.add((calendar.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()));
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
            startEndDatesOfWeek.add((calendar.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()));
        } catch (ArrayIndexOutOfBoundsException ex) {
            throw new DateException();
        }
        return startEndDatesOfWeek;
    }

    
    
    /**
     * This method returns the week number of a given date.
     * 
     * @param date date 
     * @return An {@code Integer} between 1 and 52 (in some years 53),
     * representative of the week number that contains the given date.
     */
    public static int getWeek(LocalDate date) {
        Locale userLocale = Locale.ITALY;
        WeekFields weekNumbering = WeekFields.of(userLocale);
        return date.get(weekNumbering.weekOfWeekBasedYear());
    }

    
    
    /**
     * This method returns the number of weeks in a years based on date 
     * because in some cases the first days of the year are still part of 
     * the last week of the previous year. <br> See January 1, 2021 which 
     * belongs to the 53rd week of 2020.
     * 
     * @param date The starting date to derive the number of weeks in a year.
     * @return {@code int}. The number of weeks in a year.
     */
    public static int getNumberOfWeeksInYear(LocalDate date) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, getYear(date)); 
        return calendar.getWeeksInWeekYear();
    }

    
    
    /**
     * This method returns the date relative to a {@code DayOfWeek} 
     * (Monday, ..., Sunday), starting from a given date.
     * 
     * @param date date
     * @param day Desired day of the week (Monday, ..., Sunday).
     * @return {@code LocalDate} representative of the desired day of the week.
     */
    public static LocalDate getDayOfWeek(LocalDate date, DayOfWeek day) {
        return date.with(day);
    }

    
    
    /**
     * This method returns the year based on the week in which the date is
     *
     * @param date date passed as parameter
     * @return {@code int} year based on week
     */
    public static int getYear(LocalDate date) {
        Locale userLocale = Locale.ITALY;
        WeekFields weekNumbering = WeekFields.of(userLocale);
        return date.get(weekNumbering.weekBasedYear());
    }
}
