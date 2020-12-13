/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.maintenanceactivity;

import exception.NotValidParameterException;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author gorra
 * Developed by Antonio Gorrasi
 */
public class MaintenanceProcedure {
    private String smp;
    private List<Skill> skills;

    /**
     * Constructor of Maintenance Procedure
     * @param smp Standard maintenance procedure
     */
    public MaintenanceProcedure(String smp) throws NotValidParameterException {
        validateMaintenanceProcedure(smp);
        this.smp = smp;
        skills = null;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    /**
     * 
     * @return {@code String} Standard maintenance procedure
     */
    public String getSmp() {
        return smp;
    }

    /**
     * Set Standard maintenance procedure
     * @param smp Standard maintenance procedure
     */
    public void setSmp(String smp) throws NotValidParameterException {
        if(smp == null || smp.equals(""))
            throw new NotValidParameterException("Procedure not valid 3");
        this.smp = smp;
    }

    public void setSkills(List<Skill> skills) throws NotValidParameterException {
        if(skills == null || skills.stream().anyMatch(skill -> skill == null))
            throw new NotValidParameterException("Procedure not valid 2");
        this.skills = skills;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.smp);
        return hash;
    }

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
        final MaintenanceProcedure other = (MaintenanceProcedure) obj;
        if (!Objects.equals(this.smp, other.smp)) {
            return false;
        }
        return true;
    }
    
    
    
    private void validateMaintenanceProcedure(String smp) throws NotValidParameterException{
        if(smp == null || smp.trim().replaceAll("  +", " ") .equals(""))
            throw new NotValidParameterException("Procedure not valid 1");
    }
    

        /**
     * Return string representation of the MaintenanceProcedure object
     * @return {@code String} Maintenance Procedure
     */
    @Override
    public String toString() {
        return "MaintenanceProcedure{" + "smp=" + smp + "skills=" + skills +'}';
    }
}
