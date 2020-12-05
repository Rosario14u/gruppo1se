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
    
}
