/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.user;

import business.maintenanceactivity.MaintenanceActivity;
import java.sql.Connection;
import persistence.maintenanceactivity.MaintenanceActivityDAO;
import persistence.maintenanceactivity.RequiredMaterialForMaintenanceDAO;

/**
 *
 * @author rosar
 */
public class Planner extends User {
    
    public Planner(String username, String password) {
        super(username, password);
    }
    
    public MaintenanceActivity viewMaintenanceActivity(int activityId, Connection conn){
        MaintenanceActivityDAO activityDao = new MaintenanceActivityDAO();
        MaintenanceActivity activity = activityDao.retrieveMaintenanceActivityDao(activityId, conn);
        RequiredMaterialForMaintenanceDAO requiredMaterialsDao = new RequiredMaterialForMaintenanceDAO();
        activity.setMaterials(requiredMaterialsDao.retrieveMaterialsByActivityId(activityId, conn));
        return activity;
    }
    
    
}
