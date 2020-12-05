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

    @Override
    public boolean addSmp(MaintenanceProcedure procedure) throws ProcedureException {
        if(procedure != null){
            if (procedure.getSmp().equals("ProvaSmp1")){
                return true;
            }else if(procedure.getSmp().equals("ProvaSmp2")){
                return false;
            }else{
                throw new ProcedureException();
            }
        }else{
            return false;
        }
    }
    
}
