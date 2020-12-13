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
            UsersDAO usersDao, TypologyDAO typologyDao) {
        super(username, password);
        this.procedureDao = procedureDao;
        this.typologyDao = typologyDao;
        this.usersDao = usersDao;
    }
    
    
    public boolean saveSmpProcedure(String newSmp,String oldSmp) throws ProcedureException, NotValidParameterException{
        if(procedureDao == null){
            throw new NotValidParameterException("Error in saving procedure");
        }
        
        boolean retVal = false;
        if (newSmp == null || newSmp.trim().replaceAll("  +", " ").equals("")){
            throw new ProcedureException("Problem in saving smp");
        }
        MaintenanceProcedure procedure = new MaintenanceProcedure(newSmp);
        
        if (oldSmp != null && !oldSmp.trim().replaceAll("  +", " ").equals("")){
            retVal = procedureDao.updateSmp(procedure,oldSmp);
        }
        if (retVal == false){
            procedureDao.addSmp(procedure);
        }
        return true;
    }
    
    public List<UserDTO> viewUsers() throws UsersException, NotValidParameterException{
        if(usersDao == null){
            throw new NotValidParameterException("Error in retrieving users");
        }
        return usersDao.readUsers();
    }
    
    
    public boolean makeUser(String username, String password, String role) throws UsersException, NotValidParameterException{
        if(usersDao == null){
            throw new NotValidParameterException("Error in creating user");
        }
        UserDTO user;
        if (role.equals("System Administrator"))
            user = new SystemAdministratorDTO(username, password);
        else if (role.equals("Maintainer"))
            user = new MaintainerDTO(username, password);
        else
            user = new PlannerDTO(username, password);
        return usersDao.addUser(user);
    }
    
    public int removeUsers(List<String> usernameList) throws UsersException, NotValidParameterException{
        if(usersDao == null){
            throw new NotValidParameterException("Error in removing users");
        }
        if(usernameList == null || usernameList.isEmpty())
            return 0;
        return usersDao.deleteUsers(usernameList);
    }
    
    
    public boolean modifyUser(String oldUsername, UserDTO newUser) throws UsersException, NotValidParameterException{
        if(usersDao == null){
            throw new NotValidParameterException("Error in updating users");
        }
        if(oldUsername==null || newUser==null || newUser.getUsername()==null || newUser.getPassword()==null
                || oldUsername.equals("") || newUser.getUsername().equals("")){
            throw new UsersException("Invalid parameters");
        }
        return usersDao.updateUser(oldUsername, newUser);
    }

    public boolean makeTypology(String typology) throws TypologyException, NotValidParameterException{
        if(typologyDao == null){
            throw new NotValidParameterException("Error in retrieving users");
        }
        return typologyDao.addTypology(typology);
    }
    
    public List<String> readTypologies() throws TypologyException, NotValidParameterException{
        if(typologyDao == null){
            throw new NotValidParameterException("Error in retrieving users");
        }        
        return typologyDao.viewTypologies();
    }
    
    public boolean updateTypology(String oldTypology, String newTypology) throws TypologyException, NotValidParameterException{
        if(typologyDao == null){
            throw new NotValidParameterException("Error in retrieving users");
        }
        return typologyDao.modifyTypology(oldTypology, newTypology);
    }
}
