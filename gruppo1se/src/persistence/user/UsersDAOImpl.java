/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.user;


import persistence.database.ConnectionDB;
import dto.MaintainerDTO;
import dto.PlannerDTO;
import dto.SystemAdministratorDTO;
import dto.UserDTO;
import exception.NotValidParameterException;
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
    private final String DELETE_USER = "DELETE FROM Users WHERE username = ?";
    private final String SQL_SELECT = "SELECT * FROM USERS";
    private final String SELECT_MAINTAINER = "SELECT * FROM Users WHERE role = 'Maintainer' ORDER BY username";
    private final String SQL_UPDATE = "UPDATE users SET username=?, password=?, role=? WHERE username = ?";
    
    @Override
    public boolean addUser(UserDTO user) throws UsersException{
        try {
            Connection conn = ConnectionDB.getInstanceConnection().getConnection();
            PreparedStatement stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            if (MaintainerDTO.class.isInstance(user))
                stmt.setString(3, "Maintainer");
            else if (PlannerDTO.class.isInstance(user))
                stmt.setString(3, "Planner");
            else
                stmt.setString(3, "System Administrator");
            int row = stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            throw new UsersException();
        }
    }
    

    
    public List<UserDTO> readUsers() throws UsersException, NotValidParameterException{
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
    
    private List<UserDTO> makeUsers(ResultSet set) throws UsersException, NotValidParameterException{
        List<UserDTO> users = new ArrayList<>();
        try {
            while (set.next()){
                if (set.getString("role").equals("System Administrator"))
                    users.add(new SystemAdministratorDTO(set.getString("username"), set.getString("password")));
                else if (set.getString("role").equals("Maintainer"))
                    users.add(new MaintainerDTO(set.getString("username"), set.getString("password")));
                else
                    users.add(new PlannerDTO(set.getString("username"), set.getString("password")));
            }
            return users;
        } catch (SQLException ex) {
            throw new UsersException("Error on select query");
        }
    }
    /**
     * This method allows to delete Users passed as parameter
     * @param usernameList list of users to delete
     * @return {@code int} number of users deleted 
     * @throws UsersException if there are problems in deleting users
     */
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
    
    
    @Override
    public boolean updateUser(String oldUsername, UserDTO newUser) throws UsersException {       
        try {
            Connection conn = ConnectionDB.getInstanceConnection().getConnection();
            PreparedStatement stm = conn.prepareStatement(SQL_UPDATE);
            stm.setString(1, newUser.getUsername());
            stm.setString(2, newUser.getPassword());
            if (MaintainerDTO.class.isInstance(newUser))
                stm.setString(3, "Maintainer");
            else if (PlannerDTO.class.isInstance(newUser))
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
    
    @Override
    public List<MaintainerDTO> readMaintainers() throws UsersException{
        List<MaintainerDTO> maintainers = new ArrayList<>();
        try {
            Connection conn = ConnectionDB.getInstanceConnection().getConnection();
            Statement stmt = conn.createStatement();
            ResultSet set = stmt.executeQuery(SELECT_MAINTAINER);
            while(set.next()){
                maintainers.add(new MaintainerDTO(set.getString("username"), set.getString("password")));
            }
            return maintainers;
        }
        catch (SQLException ex) {
            throw new UsersException();
        }
    }
}
