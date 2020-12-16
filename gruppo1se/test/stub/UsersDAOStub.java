/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stub;

import dto.MaintainerDTO;
import dto.PlannerDTO;
import dto.SystemAdministratorDTO;
import dto.UserDTO;
import exception.NotValidParameterException;
import exception.UsersException;
import java.util.ArrayList;
import java.util.List;
import persistence.user.UsersDAO;

/**
 *
 * @author VincenzaCoppola <v.coppola38@studenti.unisa.it>
 */
public class UsersDAOStub implements UsersDAO {

    /**
     * 
     * @return
     * @throws UsersException
     * @throws NotValidParameterException 
     */
    /*Developed by Vincenza Coppola*/
    @Override
    public List<UserDTO> readUsers() throws UsersException, NotValidParameterException {
        List<UserDTO> users = new ArrayList<>();
        users.add(new MaintainerDTO("UserMaintainer","PwdMaintainer"));
        users.add(new PlannerDTO("UserPlanner","PwdPlanner"));
        users.add(new SystemAdministratorDTO("UserSystemAdministrator","PwdSystemAdministrator"));
        return users;
    }
    
    /**
     * 
     * @param user
     * @return
     * @throws UsersException 
     */
    /*Developed by Alessio Citro*/
    @Override
    public boolean addUser(UserDTO user) throws UsersException {
        if (user.getUsername() != null && user.getPassword() != null)
            return true;
        else 
            throw new UsersException();
    }
    
    /*Stub method of deleteUsers*/
    /**
     * Simulates the behaviour of deleteUsers of UsersDAO.
     * @param usernameList
     * @return
     * @throws UsersException in default cases
     */
    @Override
    public int deleteUsers(List<String> usernameList) throws UsersException{
        if (usernameList == null || usernameList.size() == 1 || usernameList.isEmpty()){
            /*case in which a null parameter is passed, in which a non empty list is passed
            but there wasn't element in database  or an empty list is passed*/             
            return 0;
        }else if(usernameList.size() == 3){ 
            /*case in which a non empty list is passed and the elements are deleted*/
            return 3;
        }else{
            /*case in which a deleteUsers method raise an exception*/
            throw new UsersException();
        }      
    }
    
    
    
    /**
     * Simulates the behaviour of updateUser of UsersDAO
     * @param oldUsername old username of the user
     * @param newUser containing the information
     * about the new user 
     * @return {@code true} or {@code false}
     * @throws UsersException if oldUsername is null or<br>
     * newUser is null or username or password of newUser<br>
     * are null or oldUsername or the username of newUser <br>
     * are empty string
     */
    //Developed by Antonio Gorrasi
    @Override
    public boolean updateUser(String oldUsername, UserDTO newUser) throws UsersException{
        if (oldUsername==null || newUser==null || newUser.getUsername()==null || newUser.getPassword()==null
                || oldUsername.equals("") || newUser.getUsername().equals("")){
            throw new UsersException();            
        } else if(oldUsername.equals("oldUsername1")){
            return true;
        } else if(oldUsername.equals("oldUsername2")){
            return false;
        } else if(oldUsername.equals("oldUsername3")){
            throw new UsersException();
        } else {
            throw new UsersException();
        }
    }
   

    /**
     * Simulates the behaviour of readMaintainers 
     * of UsersDAO
     * @return a list of Maintainer
     * @throws UsersException 
     */
    //Developed by Antonio Gorrasi
    @Override
    public List<MaintainerDTO> readMaintainers() throws UsersException {
        return new ArrayList<>(){{
            add(new MaintainerDTO("username1", "pwd1"));
            add(new MaintainerDTO("username2", "pwd2"));
        }};
    }
}
