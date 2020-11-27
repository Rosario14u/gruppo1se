/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.user;

import business.maintenanceactivity.MaintenanceActivity;
import business.maintenanceactivity.MaintenanceActivityFactory;
import business.maintenanceactivity.MaintenanceProcedure;
import business.maintenanceactivity.Material;
import business.maintenanceactivity.Site;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;
import persistence.maintenanceactivity.MaintenanceActivityDAO;
import persistence.maintenanceactivity.RequiredMaterialForMaintenanceDAO;

/**
 *
 * @author rosar
 */
public class Planner extends User {
    /**
     * Constructor of Planner
     * @param username username of Planner 
     * @param password password of Planner
     */
    public Planner(String username, String password) {
        super(username, password);
    }
    /**
     * This method returns Maintenance Activity with the passed activityId if exists,
     * null otherwise
     * @param activityId activity id of the Maintenance Activity to visualize
     * @return {@code MaintenanceActivity} MaintenanceActivity
     */
    /*Method developed by Rosario Gaeta*/
    public MaintenanceActivity viewMaintenanceActivity(int activityId){
        MaintenanceActivityDAO activityDao = new MaintenanceActivityDAO();
        /*this method uses MaintenanceActivityDAO and RequiredMaterialForMaintenanceDAO objects to
        retrieve the required MaintenanceActivity object if exists*/
        MaintenanceActivity activity = activityDao.retrieveMaintenanceActivityDao(activityId);
        if(activity != null){
            RequiredMaterialForMaintenanceDAO requiredMaterialsDao = new RequiredMaterialForMaintenanceDAO();
            activity.setMaterials(requiredMaterialsDao.retrieveMaterialsByActivityId(activityId));
        }
        return activity;
    }
    
    /**
     * 
     * @param activityId
     * @param branchOffice
     * @param area
     * @param typology
     * @param activityDescription
     * @param estimatedInterventionTime
     * @param date
     * @param maintenanceProcedure
     * @param interruptibleActivity
     * @param typologyOfActivity
     * @param typologyOfUnplannedActivity
     * @return 
     */
    public boolean modifyMaintenanceActivity(int activityId, String branchOffice, String area, String typology, String activityDescription, 
            int estimatedInterventionTime, String date, boolean interruptibleActivity, 
            String typologyOfActivity, String typologyOfUnplannedActivity){
        
        MaintenanceActivityFactory.Typology type = typologyOfActivity.compareTo("PLANNED")==0 ? 
                    MaintenanceActivityFactory.Typology.PLANNED : MaintenanceActivityFactory.Typology.valueOf(typologyOfUnplannedActivity); 
        
        MaintenanceActivity newActivity = MaintenanceActivityFactory.make(type, activityId, branchOffice, area, null, typology, activityDescription, 
                estimatedInterventionTime, date, null, null, interruptibleActivity);
        
        MaintenanceActivityDAO maintenanceActivityDao = new MaintenanceActivityDAO();
        return maintenanceActivityDao.modifyMaintenaceActivity(newActivity);
    }
    
    public boolean removeMaintenanceActivity(int activityId){
        MaintenanceActivityDAO dao = new MaintenanceActivityDAO();
        return dao.deleteMaintenanceActivity(activityId);
    }
    
    public boolean makeMaintenanceActivity(int activityId, String branchOffice, String area, String workspaceNotes, String typology, String activityDescription, int estimatedInterventionTime, 
            String date, String smp, List<Material> materials, boolean interruptibleActivity,
            boolean plannedActivity, boolean extraActivity, boolean ewo){
        MaintenanceActivityFactory.Typology type = null;
        if (plannedActivity)
            type = MaintenanceActivityFactory.Typology.PLANNED;
        else if(extraActivity)
            type = MaintenanceActivityFactory.Typology.EXTRA;
        else
            type = MaintenanceActivityFactory.Typology.EWO;
        MaintenanceActivity activity = MaintenanceActivityFactory.make(type, activityId, branchOffice, area, workspaceNotes, typology, activityDescription, estimatedInterventionTime,
                date, smp, materials, interruptibleActivity);
        MaintenanceActivityDAO dao = new MaintenanceActivityDAO();
        return dao.addMaintenanceActivity(activity);
    }
}
