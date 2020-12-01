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
 * @author aless
 */
public class PlannedMaintenanceActivity extends MaintenanceActivity{

    public PlannedMaintenanceActivity(int activityId, Site site, String typology, String activityDescription, int estimatedInterventionTime, LocalDate date, MaintenanceProcedure maintenanceProcedure, List<Material> materials, boolean interruptibleActivity) {
        super(activityId, site, typology, activityDescription, estimatedInterventionTime, date, maintenanceProcedure, materials, interruptibleActivity);
    }

    @Override
    public String toString() {
        return "PlannedMaintenanceActivity" + super.toString();
    }
    
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj) && PlannedMaintenanceActivity.class.isInstance(obj);
}
}
