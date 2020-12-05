/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.user;

import business.maintenanceactivity.MaintenanceProcedure;
import exception.ProcedureException;
import persistence.maintenanceactivity.MaintenanceProcedureDAO;

/**
 *
 * @author rosar
 */
public class SystemAdministrator extends User {
    private MaintenanceProcedureDAO procedureDao;
    
    public SystemAdministrator(String username, String password){
        super(username, password);
        this.procedureDao = null;
    }
    
    public SystemAdministrator(String username, String password,MaintenanceProcedureDAO procedureDao) {
        super(username, password);
        this.procedureDao = procedureDao; 
    }
    
    public boolean saveSmpProcedure(String smp) throws ProcedureException{
        if (smp != null){
           return procedureDao.addSmp(new MaintenanceProcedure(smp)); 
        }else{
            throw new ProcedureException("Error in procedure creation");
        }
        
    }
}
