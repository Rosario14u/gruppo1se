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
import java.util.logging.Level;
import java.util.logging.Logger;
import persistence.database.ConnectionDB;

/**
 *
 * @author rosar
 */
public class MaintenanceProcedureDAOImpl implements MaintenanceProcedureDAO{
    private final static String INSERT_PROCEDURE = "INSERT INTO MaintenanceProcedure values(?)";
    private final static String UPDATE_PROCEDURE = "UPDATE MaintenanceProcedure SET smp=? WHERE smp=?";
    
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
    
    @Override
    public boolean updateSmp(MaintenanceProcedure newProcedure, String oldSmp) throws ProcedureException {
        if (newProcedure == null || newProcedure.getSmp().trim().replaceAll("  +", " ").equals("") || oldSmp == null
                || oldSmp.trim().replaceAll("  +", " ").equals(""))
            throw new ProcedureException("Error in saving procedure");
        if (newProcedure.getSmp().equals(oldSmp))
            throw new ProcedureException("new file name and old file name are equals");
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
