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
 * @author VincenzaCoppola <v.coppola38@studenti.unisa.it>
 */
public class ExtraActivity extends UnplannedMaintenanceActivity{

    public ExtraActivity(int activityId, Site site, String typology, String activityDescription, int estimatedInterventionTime, LocalDate date, MaintenanceProcedure maintenanceProcedure, List<Material> materials, boolean interruptibleActivity) {
        super(activityId, site, typology, activityDescription, estimatedInterventionTime, date, maintenanceProcedure, materials, interruptibleActivity);
    }
       
}
