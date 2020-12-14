/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stub;

import business.maintenanceactivity.MaintenanceProcedure;
import exception.ProcedureException;
import persistence.maintenanceactivity.MaintenanceProcedureDAO;

/**
 *
 * @author rosar
 */
public class MaintenanceProcedureDAOStub implements MaintenanceProcedureDAO {
    
    /**
     * Simulates the behaviour of addSmp of MaintenanceProcedureDAO.
     * @param procedure
     * @return
     * @throws ProcedureException 
     */
    @Override
    public boolean addSmp(MaintenanceProcedure procedure) throws ProcedureException {
        if(procedure == null || procedure.getSmp().equals("")){ 
            return false;
        }else if (procedure.getSmp().equals("ProvaSmp1")){
            return true;
        }else if(procedure.getSmp().equals("ProvaSmp2")){
            throw new ProcedureException();
        }else{
            throw new ProcedureException();
        }
        
    }
    
    /**
     * Simulates the behaviour of updateSmp of MaintenanceProcedureDAO.
     * @param newProcedure
     * @param oldSmp
     * @return
     * @throws ProcedureException 
     */
    @Override
    public boolean updateSmp(MaintenanceProcedure newProcedure, String oldSmp) throws ProcedureException {
        if(newProcedure == null || newProcedure.getSmp().equals("")){
            return false;
        }else if(newProcedure.getSmp().equals("ProvaSmp1")){
            return false;
        }else if(newProcedure.getSmp().equals("ProvaSmp2") && (oldSmp == null
                || oldSmp.trim().replaceAll("  +", " ").equals(""))){
            throw new ProcedureException();
        }else if(newProcedure.getSmp().equals("ProvaSmp2")){
            return true;
        }else{
            throw new ProcedureException();
        }
        
    }
    
}
