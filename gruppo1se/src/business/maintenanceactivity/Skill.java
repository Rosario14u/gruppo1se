/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.maintenanceactivity;

import java.util.Objects;

/**
 *
 * @author aless
 */
public class Skill implements Comparable<Skill>{
    private String name;
    
    /**
     * 
     * @param name 
     */
    public Skill(String name) {
        this.name = name;
    }
    
    /**
     * 
     * @return 
     */
    public String getName() {
        return name;
    }
    
    /**
     * 
     * @return 
     */
    @Override
    public String toString() {
        return name;
    }
    
    
    /**
     * 
     * @return 
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.name);
        return hash;
    }


    /**
     * 
     * @param obj
     * @return 
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
        final Skill other = (Skill) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }
    
    /**
     * 
     * @param o
     * @return 
     */
    @Override
    public int compareTo(Skill o) {
        return this.getName().compareTo(o.getName());
    }    
}
