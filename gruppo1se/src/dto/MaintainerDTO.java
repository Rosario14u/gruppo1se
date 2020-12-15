/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import business.maintenanceactivity.Appointment;
import business.maintenanceactivity.Skill;
import java.util.List;

/**
 * The {@code MaintainerDTO} class, implements
 * the Data transfer object of Maintainer
 * @author gorra
 */
public class MaintainerDTO extends UserDTO {

    private List<Appointment> appointmentsInWeek;
    private List<Skill> skills;

    public MaintainerDTO(String username, String password) {
        super(username, password);
    }

    public List<Appointment> getAppointmentsInWeek() {
        return appointmentsInWeek;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setAppointmentsInWeek(List<Appointment> appointmentsInWeek) {
        this.appointmentsInWeek = appointmentsInWeek;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    @Override
    public String toString() {
        return "MaintainerDTO " + super.toString();
    }
    
}
