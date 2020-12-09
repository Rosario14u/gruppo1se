/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.maintenanceactivity;

import exception.TypologyException;
import persistence.database.ConnectionDB;
import java.sql.*;

/**
 *
 * @author aless
 */
public class TypologyDAOImpl implements TypologyDAO{
    private final String SQL_INSERT = "INSERT INTO Typology VALUES (?)";
    
    @Override
    public boolean addTypology(String typology) throws TypologyException {
        if (typology == null)
            throw new TypologyException();
        try {
            Connection conn = ConnectionDB.getInstanceConnection().getConnection();
            PreparedStatement stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, typology);
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            throw new TypologyException();
        }
    }
    
}
