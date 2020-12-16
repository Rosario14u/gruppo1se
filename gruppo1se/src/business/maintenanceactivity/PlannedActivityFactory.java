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
 * @author gorra
 */
public class PlannedActivityFactory extends MaintenanceActivityFactory{
    
     /**
     * This method returns the correct instance of planned maintenance activity based on type parameter 
     * @param type typology of unplanned maintenance activity
     * @param activityId ID of maintenance activity to instantiate
     * @param site site of maintenance activity to instantiate
     * @param typology typology of maintenance activity to instantiate
     * @param activityDescription description of maintenance activity to instantiate
     * @param estimatedInterventionTime estimatedIntervention time of maintenance activity to instantiate
     * @param date date of maintenance activity to instantiate
     * @param maintenanceProcedure procedure of maintenance activity to instantiate
     * @param materials required materials for maintenance to instantiate
     * @param interruptibleActivity indicates whether the activity is interruptible to instantiate
     * @return the correct instance of planned maintenance activity 
     */
    @Override
    protected MaintenanceActivity selectMaintenanceActivity(MaintenanceActivityFactory.Typology type, int activityId,
            Site site, String typology, String activityDescription,int estimatedInterventionTime, 
            LocalDate date, MaintenanceProcedure maintenanceProcedure, 
            List<Material> materials, boolean interruptibleActivity) {
        return new PlannedMaintenanceActivity( activityId, site, typology, activityDescription,
            estimatedInterventionTime, date, maintenanceProcedure, materials, interruptibleActivity);
    }
}
