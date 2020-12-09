/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.maintenanceactivity;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @author gorra
 */
public class Appointment {
    private final int activityId;
    private final LocalDateTime startDateAndTime;
    private final int duration;

    public Appointment(int activityId, LocalDateTime startDateAndTime, int duration) {
        this.activityId = activityId;
        this.startDateAndTime = startDateAndTime;
        this.duration = duration;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + this.activityId;
        hash = 79 * hash + Objects.hashCode(this.startDateAndTime);
        hash = 79 * hash + this.duration;
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
        final Appointment other = (Appointment) obj;
        if (this.activityId != other.activityId) {
            return false;
        }
        if (this.duration != other.duration) {
            return false;
        }
        if (!Objects.equals(this.startDateAndTime, other.startDateAndTime)) {
            return false;
        }
        return true;
    }
    
    public int getActivityId() {
        return activityId;
    }

    public LocalDateTime getStartDateAndTime() {
        return startDateAndTime;
    }

    public int getDuration() {
        return duration;
    } 
}
