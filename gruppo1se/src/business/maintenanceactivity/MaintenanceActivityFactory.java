/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.maintenanceactivity;

import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author rosar
 * Developed by Rosario Gaeta
 */
public abstract class MaintenanceActivityFactory {
    
    public enum Typology {PLANNED, EWO, EXTRA}
    /**
     * Static method that creates and returns a Maintenance Activity object according to the value of parameter type
     * @param type typology of MaintenanceActivity  
     * @param activityId Id of MaintenanceActivity that needs to be instantiate
     * @param site Site of MaintenanceActivity that needs to be instantiate
     * @param typology typology of MaintenanceActivity that needs to be instantiate
     * @param activityDescription activity description of MaintenanceActivity that needs to be instantiate
     * @param estimatedInterventionTime estimated intervention time of MaintenanceActivity that needs to be instantiate
     * @param date date of maintenance activity that needs to be Instantiate
     * @param maintenanceProcedure maintenance procedure of Maintenance Activity that needs to be Instantiate
     * @param materials materials of Maintenance Activity that needs to be Instantiate
     * @param interruptibleActivity interruptible activity of Maintenance Activity that needs to be Instantiate
     * @return the required instance of maintenance activity
     */
    public static MaintenanceActivity make(Typology type, int activityId, Site site, String typology,
            String activityDescription, int estimatedInterventionTime, LocalDate date,
            MaintenanceProcedure maintenanceProcedure, List<Material> materials, boolean interruptibleActivity){
        MaintenanceActivityFactory factory = null;
        if (type == Typology.PLANNED){
            factory = new PlannedActivityFactory();
        }else{
            factory = new UnplannedActivityFactory();
        }
        return factory.build(type,activityId, site, typology, activityDescription,
                estimatedInterventionTime, date, maintenanceProcedure, materials, interruptibleActivity);
    }
    /**
    * ** This method build the correct maintenance activity based on typology parameter
    * @param type typology of maintenance activity
    * @param activityId ID of maintenance activity to instantiate
    * @param site site of maintenance activity to instantiate
    * @param typology typology of maintenance activity to instantiate
    * @param activityDescription description of maintenance activity to instantiate
    * @param estimatedInterventionTime estimatedIntervention time of maintenance activity to instantiate
    * @param date date of maintenance activity to instantiate
    * @param maintenanceProcedure procedure of maintenance activity to instantiate
    * @param materials required materials for maintenance to instantiate
    * @param interruptibleActivity indicates whether the activity is interruptible to instantiate
    * @return the correct instance of maintenance activity 
     */
    private MaintenanceActivity build(Typology type, int activityId, Site site, String typology,
            String activityDescription,int estimatedInterventionTime, LocalDate date,
            MaintenanceProcedure maintenanceProcedure,
            List<Material> materials, boolean interruptibleActivity) {
        return this.selectMaintenanceActivity(type, activityId, site, typology, activityDescription,
                estimatedInterventionTime, date, maintenanceProcedure, materials, interruptibleActivity);
    }
    /**
    ** This method returns the correct instance of maintenance activity based on factory class that extends
    * MaintenanceActivityFactory and implements selectMaintenanceActivity
    * @param type typology of maintenance activity
    * @param activityId ID of maintenance activity to instantiate
    * @param site site of maintenance activity to instantiate
    * @param typology typology of maintenance activity to instantiate
    * @param activityDescription description of maintenance activity to instantiate
    * @param estimatedInterventionTime estimatedIntervention time of maintenance activity to instantiate
    * @param date date of maintenance activity to instantiate
    * @param maintenanceProcedure procedure of maintenance activity to instantiate
    * @param materials required materials for maintenance to instantiate
    * @param interruptibleActivity indicates whether the activity is interruptible to instantiate
    * @return the correct istance of maintenance activity 
     */
    protected abstract MaintenanceActivity selectMaintenanceActivity(Typology type, int activityId, Site site,
            String typology, String activityDescription, int estimatedInterventionTime,
            LocalDate date, MaintenanceProcedure maintenanceProcedure, List<Material> materials,
            boolean interruptibleActivity);
}
