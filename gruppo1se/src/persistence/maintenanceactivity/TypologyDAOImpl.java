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
    private final String SQL_UPDATE = "UPDATE Typology SET typologyName = ? WHERE typologyName = ?";
    private final String SQL_DELETE = "DELETE FROM Typology WHERE typologyName = ?";
    
    /**
     * 
     * @param typology
     * @return {@code boolean} true if the typology is inserted into the database
     * @throws TypologyException if there's an SQL error while inserting into the Typology table
     */
    /*Developed by Alessio Citro*/
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
    
    /**
     * 
     * @return a list of Strings, representing all the typologies stored into the database
     * @throws TypologyException if there's an SQL error while selecting from the Typology table
     */
    /*Developed by Alessio Citro*/
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
    
    /**
     * 
     * @param res ResultSet containing all the entries from the typology table
     * @return a list of Strings, representing all the typologies stored into the database
     * @throws TypologyException if there's an SQL error while looping through the ResultSet
     */
    /*Developed by Alessio Citro*/
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
    
    /**
     * 
     * @param oldTypology
     * @param newTypology
     * @return {@code boolean} true if the typology is updated into the database
     * @throws TypologyException if there's an SQL error while updating into the Typology table
     */
    /*Developed by Alessio Citro*/
    @Override
    public boolean modifyTypology(String oldTypology, String newTypology) throws TypologyException {
        try {
            Connection conn = ConnectionDB.getInstanceConnection().getConnection();
            PreparedStatement stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, newTypology);
            stmt.setString(2, oldTypology);
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new TypologyException();
        }
    }
    
    /**
     * 
     * @param typology
     * @return {@code boolean} true if the typology is removed from the database
     * @throws TypologyException if there's an SQL error while deleting from the Typology table
     */
    /*Developed by Alessio Citro*/
    @Override
    public boolean deleteTypology(String typology) throws TypologyException {
        try {
            Connection conn = ConnectionDB.getInstanceConnection().getConnection();
            PreparedStatement stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setString(1, typology);
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new TypologyException();
        }
    }
}
