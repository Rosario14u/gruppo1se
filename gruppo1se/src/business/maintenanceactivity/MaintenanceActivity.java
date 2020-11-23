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
public class MaintenanceActivity {
    private final int activityId;
    private Site site;
    private String typology;
    private String activityDescription;
    private int estimatedInterventionTime;
    private LocalDate date;
    private MaintenanceProcedure maintenanceProcedure;
    private List<String> materials;
    private boolean interruptibleActivity;

    public MaintenanceActivity(int activityId, Site site, String typology, String activityDescription, int estimatedInterventionTime, LocalDate date, MaintenanceProcedure maintenanceProcedure, List<String> materials, boolean interruptibleActivity) {
        this.activityId = activityId;
        this.site = site;
        this.typology = typology;
        this.activityDescription = activityDescription;
        this.estimatedInterventionTime = estimatedInterventionTime;
        this.date = date;
        this.maintenanceProcedure = maintenanceProcedure;
        this.materials = materials;
        this.interruptibleActivity = interruptibleActivity;
    }

    public int getActivityId() {
        return activityId;
    }

    public Site getSite() {
        return site;
    }

    public String getTypology() {
        return typology;
    }

    public String getActivityDescription() {
        return activityDescription;
    }

    public int getEstimatedInterventionTime() {
        return estimatedInterventionTime;
    }

    public LocalDate getDate() {
        return date;
    }

    public MaintenanceProcedure getMaintenanceProcedure() {
        return maintenanceProcedure;
    }

    public List<String> getMaterials() {
        return materials;
    }

    public boolean isInterruptibleActivity() {
        return interruptibleActivity;
    }

    public void setMaterials(List<String> materials) {
        this.materials = materials;
    }

    @Override
    public String toString() {
        return "MaintenanceActivity{" + "activityId=" + activityId + ", site=" + site + ", typology=" + typology + 
                ", activityDescription=" + activityDescription + ", estimatedInterventionTime=" + 
                estimatedInterventionTime + ", date=" + date + ", maintenanceProcedure=" + maintenanceProcedure + 
                ", materials=" + materials + ", interruptibleActivity=" + interruptibleActivity + '}';
    }

}
