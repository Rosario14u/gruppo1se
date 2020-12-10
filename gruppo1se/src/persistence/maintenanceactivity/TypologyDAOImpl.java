/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.maintenanceactivity;

import exception.TypologyException;
import persistence.database.ConnectionDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author aless
 */
public class TypologyDAOImpl implements TypologyDAO{
    private final String SQL_INSERT = "INSERT INTO Typology VALUES (?)";
    private final String SQL_SELECT = "SELECT * FROM Typology";
    
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
    
    @Override
    public List<String> viewTypologies() throws TypologyException {
        try {
            Connection conn = ConnectionDB.getInstanceConnection().getConnection();
            Statement stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery(SQL_SELECT);
            return this.makeTypologyList(res);
        } catch (SQLException ex) {
            throw new TypologyException();
        }
    }
    
    private List<String> makeTypologyList (ResultSet res) throws TypologyException{
        List<String> typologies = new ArrayList<>();
        try {
            while(res.next()){
                typologies.add(res.getString("typologyName"));
            }         
            return typologies;
        } catch (SQLException ex) {
            throw new TypologyException();
        }
    }
}
