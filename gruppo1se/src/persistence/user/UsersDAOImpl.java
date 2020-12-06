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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author VincenzaCoppola <v.coppola38@studenti.unisa.it>
 */
public class UsersDAOImpl implements UsersDAO {
    private final String SQL_INSERT = "INSERT INTO USERS (username, password, role) VALUES (?,?,?)";
    private final String SQL_SELECT_USERNAME = "SELECT * FROM USERS WHERE USERNAME = ?";
    private final String SQL_SELECT_ROLE = "SELECT * FROM USERS WHERE ROLE = ?";
    
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
    
    public List<User> readUser(String username, String role) throws UsersException{
        try {
            Connection conn = ConnectionDB.getInstanceConnection().getConnection();
            PreparedStatement stmt = null;
            if (username == null && role == null)
                return null;
            if (username == null){
                stmt = conn.prepareStatement(SQL_SELECT_ROLE);
                stmt.setString(1, role);
                ResultSet set = stmt.executeQuery();
                return this.makeUsers(set);
            }
            else{ 
                stmt = conn.prepareStatement(SQL_SELECT_USERNAME);
                stmt.setString(1, username);
                ResultSet set = stmt.executeQuery();
                return this.makeUsers(set);
            }
        } catch (SQLException ex) {
            throw new UsersException();
        }
    }
    
    private List<User> makeUsers(ResultSet set) throws UsersException{
        List<User> users = new ArrayList<>();
        try {
            while (set.next()){
                if (set.getString("role").equals("System Administrator"))
                    users.add(new SystemAdministrator(set.getString("username"), set.getString("password"),new UsersDAOImpl()));
                else if (set.getString("role").equals("Maintainer"))
                    users.add(new Maintainer(set.getString("username"), set.getString("password")));
                else
                    users.add(new Planner(set.getString("username"), set.getString("password"), null, null));
            }
            return users;
        } catch (SQLException ex) {
            throw new UsersException("Error on select query");
        }
    }
}