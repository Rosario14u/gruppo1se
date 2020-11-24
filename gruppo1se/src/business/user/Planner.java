/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.user;

import business.maintenanceactivity.MaintenanceActivity;
import business.maintenanceactivity.MaintenanceActivityFactory;
import business.maintenanceactivity.MaintenanceProcedure;
import business.maintenanceactivity.Site;
import java.sql.Connection;
import java.time.LocalDate;
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
    
    public boolean modifyMaintenanceActivity(int activityId, String branchOffice, String area, String typology, String activityDescription, 
            int estimatedInterventionTime, LocalDate date, MaintenanceProcedure maintenanceProcedure, boolean interruptibleActivity, 
            String typologyOfActivity, String typologyOfUnplannedActivity, Connection conn){
        
        Site site = new Site(branchOffice, area);
        
        MaintenanceActivityFactory.Typology type = typologyOfActivity.compareTo("PLANNED")==0 ? 
                    MaintenanceActivityFactory.Typology.PLANNED : MaintenanceActivityFactory.Typology.valueOf(typologyOfUnplannedActivity); 
        
        MaintenanceActivity newActivity = MaintenanceActivityFactory.make(type, activityId, site, typology, activityDescription, 
                estimatedInterventionTime, date, null, null, interruptibleActivity);
        
        MaintenanceActivityDAO maintenanceActivityDao = new MaintenanceActivityDAO();
        return maintenanceActivityDao.modifyMaintenaceActivity(activityId, newActivity, conn);
    }
    
    
}
