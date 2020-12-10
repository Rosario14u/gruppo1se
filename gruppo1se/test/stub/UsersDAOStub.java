/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stub;

import business.user.Maintainer;
import business.user.Planner;
import business.user.SystemAdministrator;
import business.user.User;
import exception.UsersException;
import java.util.ArrayList;
import java.util.List;
import persistence.maintenanceactivity.MaintenanceProcedureDAOImpl;
import persistence.maintenanceactivity.TypologyDAOImpl;
import persistence.user.UsersDAO;
import persistence.user.UsersDAOImpl;

/**
 *
 * @author VincenzaCoppola <v.coppola38@studenti.unisa.it>
 */
public class UsersDAOStub implements UsersDAO {

    @Override
    public List<User> readUsers() throws UsersException {
        List<User> users = new ArrayList<>();
        users.add(new Maintainer("UserMaintainer","PwdMaintainer"));
        users.add(new Planner("UserPlanner","PwdPlanner", new MaintenanceActivityDAOStub(),
            new RequiredMaterialForMaintenanceDAOStub(), new UsersDAOStub(),
            new EmployeeAppointmentDAOStub(), new RequiredSkillForMaintenanceDAOStub()));
        users.add(new SystemAdministrator("UserSystemAdministrator","PwdSystemAdministrator",new MaintenanceProcedureDAOImpl(),new UsersDAOImpl(),new TypologyDAOImpl()));
        return users;
    }
    
    @Override
    public boolean addUser(User user) throws UsersException {
        if (user.getUsername() != null && user.getPassword() != null)
            return true;
        else 
            throw new UsersException();
    }
    
    @Override
    public int deleteUsers(List<String> usernameList) throws UsersException{
        if (usernameList == null){           // case in which a null parameter is passed  
            return 0;
        }else if(usernameList.size() == 3){  // case in which a non empty list is passed and the elements are deleted  
            return 3;
        }else if (usernameList.size() == 1){ // case in which a non empty list is passed but there wasn't element in database  
            return 0;
        }else if (usernameList.isEmpty()){   // case in which an empty list is passed 
            return 0;
        }else{                               // case in which a deleteUsers method raise an exception
            throw new UsersException();
        }      
    }
    
    public boolean updateUser(String oldUsername, User newUser) throws UsersException{
        if (oldUsername==null || newUser==null || newUser.getUsername()==null || newUser.getPassword()==null
                || oldUsername.equals("") || newUser.getUsername().equals("")){
            ////case where the passed parameters are not valid
            throw new UsersException();            
        } else if(oldUsername.equals("oldUsername1")){
            //case where the passed parameters are valid and the method successfully updates a row
            return true;
        } else if(oldUsername.equals("oldUsername2")){
            //case where the passed parameters are valid and the method does not update any rows
            return false;
        } else if(oldUsername.equals("oldUsername3")){
            //case where the method throws a SQLException
            throw new UsersException();
        }  else {
            throw new UsersException();
        }
    }
   

    @Override
    public List<Maintainer> readMaintainers() throws UsersException {
        return new ArrayList<>(){{
            add(new Maintainer("username1", "pwd1"));
            add(new Maintainer("username2", "pwd2"));
        }};
    }
}
