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
public abstract class MaintenanceActivity {
    private final int activityId;
    private Site site;
    private String typology;
    private String activityDescription;
    private int estimatedInterventionTime;
    private LocalDate date;
    private MaintenanceProcedure maintenanceProcedure;
    private List<Material> materials;
    private boolean interruptibleActivity;
    /**
     * Constructor of Maintenance Activity
     * @param activityId activityId of Maintenance Activity
     * @param site site of Maintenance Activity
     * @param typology typology of Maintenance Activity (Eletronic,Meccanical ecc)
     * @param activityDescription typology of Maintenance Activity (Eletronic,Meccanical ecc)
     * @param estimatedInterventionTime estimated intervention time of Maintenance Activity
     * @param date date of Maintenance Activity
     * @param maintenanceProcedure maintenance procedure associated with Maintenance Activity
     * @param materials list of materials associated with Maintenance Activity
     * @param interruptibleActivity interruptible activity
     */
    public MaintenanceActivity(int activityId, Site site, String typology, String activityDescription, int estimatedInterventionTime, LocalDate date, MaintenanceProcedure maintenanceProcedure, List<Material> materials, boolean interruptibleActivity) {
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
    /**
     * 
     * @return {@code int} activityId
     */
    public int getActivityId() {
        return activityId;
    }
    /**
     * 
     * @return {@code Site} site
     */
    public Site getSite() {
        return site;
    }
    /**
     * 
     * @return {@code String} typology
     */
    public String getTypology() {
        return typology;
    }
    /**
     * 
     * @return {@code String} activityDescription
     */
    public String getActivityDescription() {
        return activityDescription;
    }
    /**
     * 
     * @return {@code String} estimatedInterventionTime
     */
    public int getEstimatedInterventionTime() {
        return estimatedInterventionTime;
    }
    /**
     * 
     * @return {@code LocalDate} date
     */
    public LocalDate getDate() {
        return date;
    }
    /**
     * 
     * @return {@code MaintenanceProcedure} maintenanceProcedure
     */
    public MaintenanceProcedure getMaintenanceProcedure() {
        return maintenanceProcedure;
    }
    /**
     * 
     * @return {@code List<String>} materials
     */
    public List<Material> getMaterials() {
        return materials;
    }
    /**
     * 
     * @return {@code boolean} interruptibleActivity
     */
    public boolean isInterruptibleActivity() {
        return interruptibleActivity;
    }
    /**
     * 
     * @param materials list of materials
     */
    public void setMaterials(List<Material> materials) {
        this.materials = materials;
    }
    /**
     * Return string representation of the MaintenanceActivity object 
     * @return {@code String}
     */
    @Override
    public String toString() {
        return "MaintenanceActivity{" + "activityId=" + activityId + ", site=" + site + ", typology=" + typology + 
                ", activityDescription=" + activityDescription + ", estimatedInterventionTime=" + 
                estimatedInterventionTime + ", date=" + date + ", maintenanceProcedure=" + maintenanceProcedure + 
                ", materials=" + materials + ", interruptibleActivity=" + interruptibleActivity + '}';
    }

}
