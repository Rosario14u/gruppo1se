/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.maintenanceactivity;

import business.maintenanceactivity.MaintenanceActivity;
import exception.MaintenanceActivityException;
import exception.NotValidParameterException;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author rosar
 */
public interface MaintenanceActivityDAO{
    public boolean addMaintenanceActivity(MaintenanceActivity activity) throws MaintenanceActivityException, NotValidParameterException;
    public boolean deleteMaintenanceActivity(int activityId) throws MaintenanceActivityException, NotValidParameterException;
    public MaintenanceActivity retrieveMaintenanceActivityDao(int activityId) throws MaintenanceActivityException, NotValidParameterException;    
    public boolean modifyMaintenaceActivity(MaintenanceActivity newActivity) throws MaintenanceActivityException, NotValidParameterException;
    public List<MaintenanceActivity> retrieveMaintenanceActivityFromRange(LocalDate startDate, LocalDate stopDate) 
            throws MaintenanceActivityException, NotValidParameterException;
}
