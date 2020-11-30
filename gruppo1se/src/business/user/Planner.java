/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.user;

import business.maintenanceactivity.MaintenanceActivity;
import business.maintenanceactivity.MaintenanceActivityFactory;
import business.maintenanceactivity.Material;
import exception.MaintenanceActivityException;
import exception.MaterialException;
import exception.SiteException;
import java.util.List;
import persistence.maintenanceactivity.RequiredMaterialForMaintenanceDAO;
import persistence.maintenanceactivity.MaintenanceActivityDAO;

/**
 *
 * @author rosar
 */
public class Planner extends User {
    private final MaintenanceActivityDAO maintenanceActivityDao;
    private final RequiredMaterialForMaintenanceDAO requiredMaterialsDao;
    /**
     * Constructor of Planner
     * @param username username of Planner 
     * @param password password of Planner
     * @param maintenanceActivityDao MaintenanceActivityDao object of Planner
     * @param requiredMaterialsDao RequiredMaterialDao object of Planner
     */
    public Planner(String username, String password, MaintenanceActivityDAO maintenanceActivityDao, RequiredMaterialForMaintenanceDAO requiredMaterialsDao) {
        super(username, password);
        this.maintenanceActivityDao = maintenanceActivityDao;
        this.requiredMaterialsDao = requiredMaterialsDao;
    }
    /**
     * This method returns Maintenance Activity with the passed activityId if exists,
     * null otherwise
     * @param activityId activity id of the Maintenance Activity to visualize
     * @return {@code MaintenanceActivity} MaintenanceActivity
     * @throws exception.SiteException
     * @throws exception.MaintenanceActivityException
     * @throws exception.MaterialException
     */
    /*Method developed by Rosario Gaeta*/
    public MaintenanceActivity viewMaintenanceActivity(int activityId) throws SiteException, MaintenanceActivityException, MaterialException{
        /*this method uses MaintenanceActivityDaoImpl and RequiredMaterialForMaintenanceDaoImpl objects to
        retrieve the required MaintenanceActivity object if exists*/
        MaintenanceActivity activity = maintenanceActivityDao.retrieveMaintenanceActivityDao(activityId);
        if(activity != null){
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
    //Developed by Antonio Gorrasi
    public boolean modifyMaintenanceActivity(int activityId, String branchOffice, String area, String typology, String activityDescription, 
            int estimatedInterventionTime, String date, boolean interruptibleActivity, 
            String typologyOfActivity, String typologyOfUnplannedActivity) throws MaintenanceActivityException{
        
        MaintenanceActivityFactory.Typology type = typologyOfActivity.compareTo("PLANNED")==0 ? 
                    MaintenanceActivityFactory.Typology.PLANNED : MaintenanceActivityFactory.Typology.valueOf(typologyOfUnplannedActivity);
        
        MaintenanceActivity newActivity = MaintenanceActivityFactory.make(type, activityId, branchOffice, area, null, typology, activityDescription, 
                estimatedInterventionTime, date, null, null, interruptibleActivity);
        System.out.println(newActivity.toString());
        return maintenanceActivityDao.modifyMaintenaceActivity(newActivity);
    }
    
    public boolean removeMaintenanceActivity(int activityId){
        return maintenanceActivityDao.deleteMaintenanceActivity(activityId);
    }
    
    public boolean makeMaintenanceActivity(int activityId, String branchOffice, String area, String workspaceNotes, String typology, String activityDescription, int estimatedInterventionTime, 
            String date, String smp, List<Material> materials, boolean interruptibleActivity,
            boolean plannedActivity, boolean extraActivity, boolean ewo) throws MaterialException{
        MaintenanceActivityFactory.Typology type = null;
        if (plannedActivity)
            type = MaintenanceActivityFactory.Typology.PLANNED;
        else if(extraActivity)
            type = MaintenanceActivityFactory.Typology.EXTRA;
        else
            type = MaintenanceActivityFactory.Typology.EWO;
        MaintenanceActivity activity = MaintenanceActivityFactory.make(type, activityId, branchOffice, area, workspaceNotes, typology, activityDescription, estimatedInterventionTime,
                date, smp, materials, interruptibleActivity);
        if (materials!=null)
            return maintenanceActivityDao.addMaintenanceActivity(activity) && requiredMaterialsDao.addRequiredMaterial(activityId, materials);
        else
            return maintenanceActivityDao.addMaintenanceActivity(activity);
    }


    //Developed by Antonio Gorrasi
    public boolean addRequiredMaterial(int activityId, List<Material> requiredMaterial) throws MaterialException{
        return requiredMaterialsDao.addRequiredMaterial(activityId, requiredMaterial);
    }
    
    //Developed by Antonio Gorrasi
    public boolean removeRequiredMaterial(int activityId, List<Material> requiredMaterial) throws MaterialException{
        return requiredMaterialsDao.removeRequiredMaterial(activityId, requiredMaterial);
    }
    
    //Developed by Antonio Gorrasi
    public List<Material> retrieveAvaliableMaterialToAdd(int activityId) throws MaterialException{
        return requiredMaterialsDao.retrieveAvaliableMaterialToAdd(activityId);
    }
}
