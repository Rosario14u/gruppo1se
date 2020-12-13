/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.user;

import business.maintenanceactivity.MaintenanceProcedure;
import dto.MaintainerDTO;
import dto.PlannerDTO;
import dto.SystemAdministratorDTO;
import dto.UserDTO;
import exception.NotValidParameterException;
import exception.ProcedureException;
import exception.TypologyException;
import exception.UsersException;
import java.util.List;
import persistence.maintenanceactivity.MaintenanceProcedureDAO;
import persistence.maintenanceactivity.TypologyDAO;
import persistence.user.UsersDAO;

/**
 *
 * @author rosar
 */
public class SystemAdministrator extends User {
    private MaintenanceProcedureDAO procedureDao;
    private UsersDAO usersDao;
    private TypologyDAO typologyDao; 
   
    
    
    public SystemAdministrator(String username, String password, MaintenanceProcedureDAO procedureDao,
            UsersDAO usersDao, TypologyDAO typologyDao) throws NotValidParameterException {
        super(username, password);
        validateSystemAdministrator(procedureDao, usersDao, typologyDao);
        this.procedureDao = procedureDao;
        this.typologyDao = typologyDao;
        this.usersDao = usersDao;
    }
    
    
    public boolean saveSmpProcedure(String newSmp,String oldSmp) throws ProcedureException, NotValidParameterException{
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
    
    public List<UserDTO> viewUsers() throws UsersException, NotValidParameterException{
        return usersDao.readUsers();
    }
    
    
    public boolean makeUser(String username, String password, String role) throws UsersException, NotValidParameterException{
        UserDTO user;
        if (role.equals("System Administrator"))
            user = new SystemAdministratorDTO(username, password);
        else if (role.equals("Maintainer"))
            user = new MaintainerDTO(username, password);
        else
            user = new PlannerDTO(username, password);
        return usersDao.addUser(user);
    }
    
    public int removeUsers(List<String> usernameList) throws UsersException{
        if(usernameList == null || usernameList.isEmpty())
            return 0;
        return usersDao.deleteUsers(usernameList);
    }
    
    
    public boolean modifyUser(String oldUsername, UserDTO newUser) throws UsersException{
        if(oldUsername==null || newUser==null || newUser.getUsername()==null || newUser.getPassword()==null
                || oldUsername.equals("") || newUser.getUsername().equals("")){
            throw new UsersException("Invalid parameters");
        }
        return usersDao.updateUser(oldUsername, newUser);
    }

    public boolean makeTypology(String typology) throws TypologyException{
        return typologyDao.addTypology(typology);
    }
    
    public List<String> readTypologies() throws TypologyException{
        return typologyDao.viewTypologies();
    }
    
    public boolean updateTypology(String oldTypology, String newTypology) throws TypologyException{
        return typologyDao.modifyTypology(oldTypology, newTypology);
    }
    
    
    private void validateSystemAdministrator(MaintenanceProcedureDAO procedureDao, UsersDAO usersDao,
            TypologyDAO typologyDao) throws NotValidParameterException{
        if(procedureDao == null || usersDao == null || typologyDao == null)
            throw new NotValidParameterException();  
    }
}
