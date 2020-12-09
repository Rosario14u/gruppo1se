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
 * Developed by Antonio Gorrasi
 */
public class UnplannedActivityFactory extends MaintenanceActivityFactory{
    /**
     * This method returns the correct instance of unplanned maintenance activity based on typology parameter 
     * @param type typology of unplanned maintenance activity
     * @param activityId ID of maintenance activity to instantiate
     * @param site site of maintenance activity to instantiate
     * @param typology typology of maintenance activity to instantiate
     * @param activityDescription description of maintenance activity to instantiate
     * @param estimatedInterventionTime estimatedIntervention time of maintenance activity to instantiate
     * @param date date of maintenance activity to instantiate
     * @param maintenanceProcedure procedure of maintenance activity to instantiate
     * @param materials required materials for maintenance to instantiate
     * @param skills required skills for maintenance to instantiate
     * @param interruptibleActivity indicates whether the activity is interruptible to instantiate
     * @return the correct instance of unplanned maintenance activity 
     */
    @Override
    protected MaintenanceActivity selectMaintenanceActivity(MaintenanceActivityFactory.Typology type, int activityId, Site site, String typology,
            String activityDescription, int estimatedInterventionTime, LocalDate date, 
            MaintenanceProcedure maintenanceProcedure, List<Material> materials, List<Skill> skills, boolean interruptibleActivity) {
        if (type == MaintenanceActivityFactory.Typology.EWO){
            return new Ewo(activityId, site, typology, activityDescription,
                estimatedInterventionTime, date, maintenanceProcedure, materials, skills, interruptibleActivity);
        }
        return new ExtraActivity(activityId, site, typology, activityDescription,
            estimatedInterventionTime, date, maintenanceProcedure, materials, skills, interruptibleActivity);
    }  
}
