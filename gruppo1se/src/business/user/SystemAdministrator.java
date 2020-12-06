/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.user;

import business.maintenanceactivity.MaintenanceProcedure;
import exception.ProcedureException;
import exception.UsersException;
import java.util.List;
import persistence.maintenanceactivity.MaintenanceActivityDAOImpl;
import persistence.maintenanceactivity.MaintenanceProcedureDAO;
import persistence.maintenanceactivity.RequiredMaterialForMaintenanceDAOImpl;
import persistence.maintenanceactivity.SiteDaoImpl;
import persistence.user.UsersDAO;
import persistence.user.UsersDAOImpl;

/**
 *
 * @author rosar
 */
public class SystemAdministrator extends User {
    private MaintenanceProcedureDAO procedureDao;
    private final UsersDAO usersDAO;
    
    
    public SystemAdministrator(String username, String password){
        super(username, password);
        this.procedureDao = null;
        this.usersDAO = null;
    }
    
    public SystemAdministrator(String username, String password,MaintenanceProcedureDAO procedureDao) {
        super(username, password);
        this.procedureDao = procedureDao; 
        this.usersDAO = null;
    }
    
    public SystemAdministrator(String username, String password, UsersDAO usersDAO){
        super(username, password);
        this.procedureDao = null;
        this.usersDAO = usersDAO;
    }
    
    public boolean saveSmpProcedure(String smp) throws ProcedureException{
        if (smp != null){
           return procedureDao.addSmp(new MaintenanceProcedure(smp)); 
        }else{
            throw new ProcedureException("Error in procedure creation");
        }
        
    }
    
    public List<User> viewUser(String username, String role) throws UsersException{
        return usersDAO.readUser(username, role);
    }
    
    public boolean makeUser(String username, String password, String role) throws UsersException{
        User user;
        if (role.equals("System Administrator"))
            user = new SystemAdministrator(username, password, new UsersDAOImpl());
        else if (role.equals("Maintainer"))
            user = new Maintainer(username, password);
        else
            user = new Planner(username, password, new MaintenanceActivityDAOImpl(new SiteDaoImpl()), new RequiredMaterialForMaintenanceDAOImpl());
        return usersDAO.addUser(user);
    }
}
