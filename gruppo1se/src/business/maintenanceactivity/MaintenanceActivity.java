/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.maintenanceactivity;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author rosar
 *
 */
//Class developed by Rosario Gaeta
public abstract class MaintenanceActivity implements Comparable<MaintenanceActivity> {

    private final int activityId;
    private final Site site;
    private final String typology;
    private final String activityDescription;
    private final int estimatedInterventionTime;
    private LocalDate date;
    private final MaintenanceProcedure maintenanceProcedure;
    private List<Material> materials;
    private final boolean interruptibleActivity;

    /**
     * Constructor of Maintenance Activity
     *
     * @param activityId activityId of Maintenance Activity
     * @param site site of Maintenance Activity
     * @param typology typology of Maintenance Activity (Eletronic,Meccanical
     * ecc)
     * @param activityDescription typology of Maintenance Activity
     * (Eletronic,Meccanical ecc)
     * @param estimatedInterventionTime estimated intervention time of
     * Maintenance Activity
     * @param date date of Maintenance Activity
     * @param maintenanceProcedure maintenance procedure associated with
     * Maintenance Activity
     * @param materials list of materials associated with Maintenance Activity
     * @param interruptibleActivity interruptible activity
     */
    public MaintenanceActivity(int activityId, Site site, String typology, String activityDescription,
            int estimatedInterventionTime, LocalDate date, MaintenanceProcedure maintenanceProcedure,
            List<Material> materials, boolean interruptibleActivity){
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

    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Return string representation of the MaintenanceActivity object
     *
     * @return {@code String}
     */
    @Override
    public String toString() {
        return '{' + "activityId=" + activityId + ", site=" + site + ", typology=" + typology
                + ", activityDescription=" + activityDescription + ", estimatedInterventionTime="
                + estimatedInterventionTime + ", date=" + date + ", maintenanceProcedure=" + maintenanceProcedure
                + ", materials=" + materials + ", interruptibleActivity=" + interruptibleActivity + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + this.activityId;
        hash = 67 * hash + Objects.hashCode(this.site);
        hash = 67 * hash + Objects.hashCode(this.typology);
        hash = 67 * hash + Objects.hashCode(this.activityDescription);
        hash = 67 * hash + this.estimatedInterventionTime;
        hash = 67 * hash + Objects.hashCode(this.date);
        hash = 67 * hash + Objects.hashCode(this.maintenanceProcedure);
        hash = 67 * hash + Objects.hashCode(this.materials);
        hash = 67 * hash + (this.interruptibleActivity ? 1 : 0);
        return hash;
    }

    /**
     *
     * @param obj
     * @return true if the two object are equals, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MaintenanceActivity other = (MaintenanceActivity) obj;
        if (this.activityId != other.activityId) {
            return false;
        }
        if (this.estimatedInterventionTime != other.estimatedInterventionTime) {
            return false;
        }
        if (this.interruptibleActivity != other.interruptibleActivity) {
            return false;
        }
        if (!Objects.equals(this.typology, other.typology)) {
            return false;
        }
        if (!Objects.equals(this.activityDescription, other.activityDescription)) {
            return false;
        }
        if (!Objects.equals(this.site, other.site)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        if (!Objects.equals(this.maintenanceProcedure, other.maintenanceProcedure)) {
            return false;
        }
        if (!((this.materials.size() == other.materials.size()) && (this.materials.containsAll(other.materials)))) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(MaintenanceActivity o) {
        return this.getActivityId() - o.getActivityId();
    }
}
