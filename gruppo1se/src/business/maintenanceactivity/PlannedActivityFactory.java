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
    @Override
    protected MaintenanceActivity selectMaintenanceActivity(MaintenanceActivityFactory.Typology type, int activityId,
            Site site, String typology, String activityDescription,int estimatedInterventionTime, 
            LocalDate date, MaintenanceProcedure maintenanceProcedure, 
            List<Material> materials, boolean interruptibleActivity) {
        return new PlannedMaintenanceActivity( activityId, site, typology, activityDescription,
            estimatedInterventionTime, date, maintenanceProcedure, materials, interruptibleActivity);
    }
}
