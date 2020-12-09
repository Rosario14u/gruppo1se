/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.user;

import business.maintenanceactivity.Appointment;
import business.maintenanceactivity.Skill;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author aless
 */
public class Maintainer extends User{
    private List<Appointment> appointmentsInWeek;
    private List<Skill> skills;
    
    public Maintainer(String username, String password) {
        super(username, password);
        this.appointmentsInWeek =  new ArrayList<>();
        this.skills = new ArrayList<>();
    }

//    @Override
//    public boolean equals(Object obj) {
//        
//        
//    }
//
//    @Override
//    public int hashCode() {
//        return super.hashCode();
//    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = 23 * hash + Objects.hashCode(this.appointmentsInWeek);
        hash = 23 * hash + Objects.hashCode(this.skills);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if(!super.equals(obj)){
            return false;
        }
        final Maintainer other = (Maintainer) obj;
        if (!((this.appointmentsInWeek.size() == other.appointmentsInWeek.size()) 
                && (this.appointmentsInWeek.containsAll(other.appointmentsInWeek)))) {
            return false;
        }
        if (!((this.skills.size() == other.skills.size()) && (this.skills.containsAll(other.skills)))) {
            return false;
        }
        return true;
    }

    
    
    

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }
    
    public List<Appointment> getAppointmentsInWeek() {
        return appointmentsInWeek;
    }

    public void setAppointmentsInWeek(List<Appointment> appointmentsInWeek) {
        this.appointmentsInWeek = appointmentsInWeek;
    }
}
