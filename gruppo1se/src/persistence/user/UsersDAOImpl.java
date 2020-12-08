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
import persistence.maintenanceactivity.MaintenanceProcedureDAOImpl;

/**
 *
 * @author VincenzaCoppola <v.coppola38@studenti.unisa.it>
 */
public class UsersDAOImpl implements UsersDAO {
    private final String SQL_INSERT = "INSERT INTO USERS (username, password, role) VALUES (?,?,?)";
    private final String DELETE_USER = "DELETE FROM Users WHERE username = ?";
    private final String SQL_SELECT = "SELECT * FROM USERS";
    private final String SQL_UPDATE = "UPDATE users SET username=?, password=?, role=? WHERE username = ?";
    
    @Override
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
    

    
    public List<User> readUsers() throws UsersException{
        try {
            Connection conn = ConnectionDB.getInstanceConnection().getConnection();
            Statement stmt = conn.createStatement();
            ResultSet set = stmt.executeQuery(SQL_SELECT);
            return this.makeUsers(set);
            }
        catch (SQLException ex) {
            throw new UsersException();
        }
    }
    
    private List<User> makeUsers(ResultSet set) throws UsersException{
        List<User> users = new ArrayList<>();
        try {
            while (set.next()){
                if (set.getString("role").equals("System Administrator"))
                    users.add(new SystemAdministrator(set.getString("username"), set.getString("password"), new MaintenanceProcedureDAOImpl(), new UsersDAOImpl()));
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
    
    @Override
    public int deleteUsers(List<String> usernameList) throws UsersException{
        try{
            if (usernameList != null && !usernameList.isEmpty()){
                String query = queryBuilder(usernameList.size());
                Connection conn = ConnectionDB.getInstanceConnection().getConnection();
                PreparedStatement pstm = conn.prepareStatement(query);
                for(int i = 0; i < usernameList.size(); i++){
                    pstm.setString(i+1, usernameList.get(i));
                }
                return pstm.executeUpdate();
            }
            return 0;
        }catch(SQLException ex){
            throw new UsersException("Problems in users deleting");
        }
    }
    
    private String queryBuilder(int size){
        StringBuilder builder = new StringBuilder(DELETE_USER);
        for(int i = 1; i < size; i++){
            builder.append(" or username = ?");
        }
        return builder.toString();   
    }
    
    
    public boolean updateUser(String oldUsername, User newUser) throws UsersException {       
        try {
            Connection conn = ConnectionDB.getInstanceConnection().getConnection();
            PreparedStatement stm = conn.prepareStatement(SQL_UPDATE);
            stm.setString(1, newUser.getUsername());
            stm.setString(2, newUser.getPassword());
            if (Maintainer.class.isInstance(newUser))
                stm.setString(3, "Maintainer");
            else if (Planner.class.isInstance(newUser))
                stm.setString(3, "Planner");
            else
                stm.setString(3, "System Administrator");
            stm.setString(4, oldUsername);
            int row = stm.executeUpdate();
            return row > 0;
        } catch (SQLException ex) {
            throw new UsersException("Error in updating");
        }
    }
}
