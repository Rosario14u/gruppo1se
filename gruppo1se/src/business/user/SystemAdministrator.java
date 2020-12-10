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
import persistence.maintenanceactivity.EmployeeAppointmentDAOImpl;
import persistence.maintenanceactivity.MaintenanceActivityDAOImpl;
import persistence.maintenanceactivity.MaintenanceProcedureDAO;
import persistence.maintenanceactivity.MaintenanceProcedureDAOImpl;
import persistence.maintenanceactivity.RequiredMaterialForMaintenanceDAOImpl;
import persistence.maintenanceactivity.RequiredSkillForMaintenanceDAOImpl;
import persistence.maintenanceactivity.SiteDaoImpl;
import persistence.user.MaintainerSkillDAOImpl;
import persistence.user.UsersDAO;
import persistence.user.UsersDAOImpl;

/**
 *
 * @author rosar
 */
public class SystemAdministrator extends User {
    private MaintenanceProcedureDAO procedureDao;
    private UsersDAO usersDAO;
    
    
    public SystemAdministrator(String username, String password){
        super(username, password);
        this.procedureDao = null;
        this.usersDAO = null;
    }
    
    
    public SystemAdministrator(String username, String password,MaintenanceProcedureDAO procedureDao, UsersDAO usersDAO) {
        super(username, password);
        this.procedureDao = procedureDao; 
        this.usersDAO = usersDAO;
    }
    
    
    public boolean saveSmpProcedure(String newSmp,String oldSmp) throws ProcedureException{
        MaintenanceProcedure procedure;
        boolean retVal = false;
        if (newSmp == null || newSmp.trim().replaceAll("  +", " ").equals("")){
            throw new ProcedureException("Problem in saving smp");
        }
        procedure = new MaintenanceProcedure(newSmp);
        if (oldSmp != null && !oldSmp.trim().replaceAll("  +", " ").equals("")){
            retVal = procedureDao.updateSmp(procedure,oldSmp);
        }
        if (retVal == false){
            procedureDao.addSmp(procedure);
        }
        return true;
    }
    
    public List<User> viewUsers() throws UsersException{
        return usersDAO.readUsers();
    }
    
    
    public boolean makeUser(String username, String password, String role) throws UsersException{
        User user;
        if (role.equals("System Administrator"))
            user = new SystemAdministrator(username, password, new MaintenanceProcedureDAOImpl(), new UsersDAOImpl());
        else if (role.equals("Maintainer"))
            user = new Maintainer(username, password);
        else
            user = new Planner(username, password, new MaintenanceActivityDAOImpl(new SiteDaoImpl()), 
                    new RequiredMaterialForMaintenanceDAOImpl(), new UsersDAOImpl(), 
                    new EmployeeAppointmentDAOImpl(), new RequiredSkillForMaintenanceDAOImpl(),new MaintainerSkillDAOImpl());
        return usersDAO.addUser(user);
    }
    
    public int removeUsers(List<String> usernameList) throws UsersException{
        if(usernameList == null || usernameList.isEmpty())
            return 0;
        return usersDAO.deleteUsers(usernameList);
    }
    
    
    public boolean modifyUser(String oldUsername, User newUser) throws UsersException{
        if(oldUsername==null || newUser==null || newUser.getUsername()==null || newUser.getPassword()==null
                || oldUsername.equals("") || newUser.getUsername().equals("")){
            throw new UsersException("Invalid parameters");
        }
        return usersDAO.updateUser(oldUsername, newUser);
    }
}
