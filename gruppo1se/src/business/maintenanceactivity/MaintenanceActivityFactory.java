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
 */
public abstract class MaintenanceActivityFactory {
    
    public enum Typology {PLANNED, EWO, EXTRA}
    
    public static MaintenanceActivity make(Typology type, int activityId, Site site, String typology,
            String activityDescription, int estimatedInterventionTime, LocalDate date,
            MaintenanceProcedure maintenanceProcedure, List<String> materials, boolean interruptibleActivity){
        MaintenanceActivityFactory factory = null;
        if (type == Typology.PLANNED){
            factory = new PlannedActivityFactory();
        }else{
            factory = new UnplannedActivityFactory();
        }
        return factory.build(type,activityId, site, typology, activityDescription,
                estimatedInterventionTime, date, maintenanceProcedure, materials, interruptibleActivity);
    }
    
    private MaintenanceActivity build(Typology type, int activityId, Site site, String typology,
            String activityDescription,int estimatedInterventionTime, LocalDate date,
            MaintenanceProcedure maintenanceProcedure,
            List<String> materials, boolean interruptibleActivity) {
        return this.selectMaintenanceActivity(type, activityId, site, typology, activityDescription,
                estimatedInterventionTime, date, maintenanceProcedure, materials, interruptibleActivity);
    }
    
    protected abstract MaintenanceActivity selectMaintenanceActivity(Typology type, int activityId, Site site,
            String typology, String activityDescription, int estimatedInterventionTime,
            LocalDate date, MaintenanceProcedure maintenanceProcedure, List<String> materials,
            boolean interruptibleActivity);
}
