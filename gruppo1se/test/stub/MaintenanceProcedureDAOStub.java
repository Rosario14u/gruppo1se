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
     * @return {@code boolean}
     * @throws ProcedureException when smp is already present
     */
    @Override
    public boolean addSmp(MaintenanceProcedure procedure) throws ProcedureException {
        if(procedure == null || procedure.getSmp().equals("")){
            /*case in which procedure is null or empty string*/
            return false;
        }else if (procedure.getSmp().equals("ProvaSmp1")){
            /*case in which added procedure is not already present*/
            return true;
        }else if(procedure.getSmp().equals("ProvaSmp2")){
            /*case in which added procedure is already present*/
            throw new ProcedureException();
        }else{
            /*default case*/
            throw new ProcedureException();
        }
        
    }
    
    /**
     * Simulates the behaviour of updateSmp of MaintenanceProcedureDAO.
     * @param newProcedure
     * @param oldSmp
     * @return {@code boolean}
     * @throws ProcedureException in default cases and when new procedure has a ProvaSmp2 string or
     * oldSmp is null or empty string.
     */
    @Override
    public boolean updateSmp(MaintenanceProcedure newProcedure, String oldSmp) throws ProcedureException {
        if(newProcedure == null || newProcedure.getSmp().equals("")){
            /*case in which new procedure is null or empty string.*/
            return false;
        }else if(newProcedure.getSmp().equals("ProvaSmp1")){
            /*case in which update should returns false because procedure is added and not already present.*/
            return false;
        }else if(newProcedure.getSmp().equals("ProvaSmp2") && (oldSmp == null
                || oldSmp.trim().replaceAll("  +", " ").equals(""))){
            /*case in which oldSmp is null or empty string .*/
            throw new ProcedureException();
        }else if(newProcedure.getSmp().equals("ProvaSmp2")){
            /*case in which the method returns true*/
            return true;
        }else{ /*default case*/
            throw new ProcedureException();
        }
        
    }
    
}
