/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.maintenanceactivity;

import business.maintenanceactivity.MaintenanceProcedure;
import java.sql.Connection;
import java.sql.PreparedStatement;
import exception.ProcedureException;
import java.sql.SQLException;
import persistence.database.ConnectionDB;

/**
 *
 * @author rosar
 */
public class MaintenanceProcedureDAOImpl implements MaintenanceProcedureDAO{
    private final static String INSERT_PROCEDURE = "INSERT INTO MaintenanceProcedure values(?)";
    private final static String UPDATE_PROCEDURE = "UPDATE MaintenanceProcedure SET smp=? WHERE smp=?";
    
    /**
     * This method allows to save a smp file in the system. <br>
     * Returns true if the method correctly save the smp file
     * @param procedure Procedure to be saved
     * @return {@code boolean} successfulAdding
     * @throws ProcedureException if there is a problem in saving smp file
     */
    /*Method developed by Rosario Gaeta*/
    @Override
    public boolean addSmp(MaintenanceProcedure procedure) throws ProcedureException {
        if (procedure == null)
            throw new ProcedureException("Error in storing procedure");
        try {
            Connection conn = ConnectionDB.getInstanceConnection().getConnection();
            PreparedStatement pstm = conn.prepareStatement(INSERT_PROCEDURE);
            pstm.setString(1, procedure.getSmp());
            pstm.executeUpdate();
            return true;
        } catch (SQLException ex) {
            throw new ProcedureException("Error in storing procedure");
        }    
    }
    /**
     * This method allows to update an existing smp file stored in the system
     * @param newProcedure Procedure to add
     * @param oldSmp smp to update
     * @return {@boolean} SuccessfulUpdating
     * @throws ProcedureException if there is a problem in updating smp file
     */
    /*Method developed by Rosario Gaeta*/
    @Override
    public boolean updateSmp(MaintenanceProcedure newProcedure, String oldSmp) throws ProcedureException {
        if (newProcedure == null || newProcedure.getSmp().trim().replaceAll("  +", " ").equals("") || oldSmp == null
                || oldSmp.trim().replaceAll("  +", " ").equals(""))
            throw new ProcedureException("Error in saving procedure");
        try {
            Connection conn = ConnectionDB.getInstanceConnection().getConnection();
            PreparedStatement pstm = conn.prepareStatement(UPDATE_PROCEDURE);
            pstm.setString(1, newProcedure.getSmp());
            pstm.setString(2, oldSmp);
            return pstm.executeUpdate() != 0;
        } catch (SQLException ex) {
            throw new ProcedureException("Error in saving procedure");
        }
        
    }
    
}
