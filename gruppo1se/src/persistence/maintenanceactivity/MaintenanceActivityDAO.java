/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.maintenanceactivity;

import business.maintenanceactivity.MaintenanceActivity;
import exception.MaintenanceActivityException;
import exception.SiteException;

/**
 *
 * @author rosar
 */
public interface MaintenanceActivityDAO{
    public boolean addMaintenanceActivity(MaintenanceActivity activity) throws MaintenanceActivityException;
    public boolean deleteMaintenanceActivity(int activityId) throws MaintenanceActivityException;
    public MaintenanceActivity retrieveMaintenanceActivityDao(int activityId) throws SiteException ,MaintenanceActivityException;    
    public boolean modifyMaintenaceActivity(MaintenanceActivity newActivity) throws MaintenanceActivityException;
}
