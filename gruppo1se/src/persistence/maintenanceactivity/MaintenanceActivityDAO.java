/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.maintenanceactivity;

import business.maintenanceactivity.MaintenanceActivity;
import exception.MaintenanceActivityException;

/**
 *
 * @author rosar
 */
public interface MaintenanceActivityDAO{
    public boolean addMaintenanceActivity(MaintenanceActivity activity);
    public boolean deleteMaintenanceActivity(int activityId);
    public MaintenanceActivity retrieveMaintenanceActivityDao(int activityId);
    public boolean modifyMaintenaceActivity(MaintenanceActivity newActivity) throws MaintenanceActivityException;
}
