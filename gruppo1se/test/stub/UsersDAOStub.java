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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import persistence.database.ConnectionDB;
import persistence.maintenanceactivity.MaintenanceActivityDAOImpl;
import persistence.maintenanceactivity.MaintenanceProcedureDAOImpl;
import persistence.maintenanceactivity.RequiredMaterialForMaintenanceDAOImpl;
import persistence.maintenanceactivity.SiteDaoImpl;
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
        users.add(new Planner("UserPlanner","PwdPlanner", new MaintenanceActivityDAOImpl(new SiteDaoImpl()),new RequiredMaterialForMaintenanceDAOImpl()));
        users.add(new SystemAdministrator("UserSystemAdministrator","PwdSystemAdministrator",new MaintenanceProcedureDAOImpl(),new UsersDAOImpl()));
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
    
}
