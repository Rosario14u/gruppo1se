/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.maintenanceactivity;

import business.maintenanceactivity.MaintenanceProcedure;
import exception.ProcedureException;

/**
 *
 * @author rosar
 */
public interface MaintenanceProcedureDAO {
    public boolean addSmp(MaintenanceProcedure procedure) throws ProcedureException;
}
