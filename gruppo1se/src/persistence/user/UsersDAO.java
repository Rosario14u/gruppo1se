/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.user;

import persistence.database.ConnectionDB;
import business.user.*;
import java.sql.*;
import exception.UsersException;

/**
 *
 * @author aless
 */
public class UsersDAO {
    private final String SQL_INSERT = "INSERT INTO USERS (username, password, role) VALUES (?,?,?)";
    
    public boolean addUser(User user) throws UsersException{
        try {
            Connection conn = ConnectionDB.getInstanceConnection().getConnection();
            PreparedStatement stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            if (Maintainer.class.isInstance(user))
                stmt.setString(3, "Maintainer");
            else if (Planner.class.isInstance(user))
                stmt.setString(3, "Planner");
            else
                stmt.setString(3, "System Administrator");
            int row = stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            throw new UsersException();
        }
    }
    
}
