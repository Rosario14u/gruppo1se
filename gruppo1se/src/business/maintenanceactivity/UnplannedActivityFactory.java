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
public class UnplannedActivityFactory extends MaintenanceActivityFactory{
    @Override
    protected MaintenanceActivity selectMaintenanceActivity(MaintenanceActivityFactory.Typology type, int activityId, Site site, String typology,
            String activityDescription, int estimatedInterventionTime, LocalDate date, 
            MaintenanceProcedure maintenanceProcedure, List<String> materials, boolean interruptibleActivity) {
        if (type == MaintenanceActivityFactory.Typology.EWO){
            return new Ewo(activityId, site, typology, activityDescription,
                estimatedInterventionTime, date, maintenanceProcedure, materials, interruptibleActivity);
        }
        return new ExtraActivity(activityId, site, typology, activityDescription,
            estimatedInterventionTime, date, maintenanceProcedure, materials, interruptibleActivity);
    }   
}
