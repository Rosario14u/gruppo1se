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
 * <p>An object of the class System Administrator provides a series of method to manage Users<br>
 * and other aspect of the system, like maintenance procedure and typology of activity. <br>
 * This class requires a series of dao object (as attributes) to performs this
 * operation.</p> 
 * @author rosar
 */
public class SystemAdministrator extends User {
    private final MaintenanceProcedureDAO procedureDao;
    private final UsersDAO usersDao;
    private final TypologyDAO typologyDao; 
   
    
    /**
     * Constructor System Administrator
     * @param username username of System Administrator
     * @param password password of System Administrator
     * @param procedureDao Dao object of Procedure
     * @param usersDao Dao object of User
     * @param typologyDao Dao object of Typology
     */
    public SystemAdministrator(String username, String password, MaintenanceProcedureDAO procedureDao,
            UsersDAO usersDao, TypologyDAO typologyDao) {
        super(username, password);
        this.procedureDao = procedureDao;
        this.typologyDao = typologyDao;
        this.usersDao = usersDao;
    }
    
    /**
     * This method allows to save a procedure in the system. Returns true if the operation is successful, false otherwise.<br>
     * ProcedureDao object required
     * @param newSmp new smp to save
     * @param oldSmp old smp to subscribe (if exists)
     * @return {@code boolean} successfulOperation
     * @throws ProcedureException if new smp is not valid or there are problems in saving precedure
     * @throws NotValidParameterException  if required dao (ProcedureDao) is not correctly initialized
     */
    /*Developed by Rosario Gaeta*/
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
            retVal = procedureDao.updateSmp(procedure,oldSmp); // This method tries to update the smp only if there was a ridenomination of smp
        }
        if (retVal == false){
            procedureDao.addSmp(procedure); // Saving of the procedure in the system 
        }
        return true;
    }
    
    /**
     * 
     * @return {@code List<UserDTO>} the list of the users table's rows.
     * @throws UsersException if there is an SQL error while reading the users table.
     * @throws NotValidParameterException  if this SystemAdministrator has no UsersDAO.
     */
    /*Developed by Vincenza Coppola*/    
    public List<UserDTO> viewUsers() throws UsersException, NotValidParameterException{
        if(usersDao == null){
            throw new NotValidParameterException("Error in retrieving users");
        }
        return usersDao.readUsers();
    }
    
    /**
     * 
     * @param username
     * @param password
     * @param role
     * @return {@code boolean} true if the user is inserted into the database
     * @throws UsersException if there's an SQL error while inserting into the Users table
     * @throws NotValidParameterException if this SystemAdministrator has no UsersDAO
     */
    /*Developed by Alessio Citro*/
    public boolean makeUser(String username, String password, UserRole role) throws UsersException, NotValidParameterException{
        if(usersDao == null){
            throw new NotValidParameterException("Error in creating user");
        }
        UserDTO user;
        if (UserRole.ADMINISTRATOR == role)
            user = new SystemAdministratorDTO(username, password);
        else if (UserRole.MAINTAINER == role)
            user = new MaintainerDTO(username, password);
        else
            user = new PlannerDTO(username, password);
        return usersDao.addUser(user);
    }
    
    /**
     * this method allows to remove users from the database. Returns the number of deleted users
     * UsersDao object required.
     * @param usernameList List of users to delete
     * @return {@code int} number of deleted rows
     * @throws UsersException if there are problems in deleting users
     * @throws NotValidParameterException if required dao (UsersDao) is not correctly initialized
     */
    /*Developed by Rosario Gaeta*/
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

    /**
     * 
     * @param typology
     * @return {@code boolean} true if the typology is inserted into database.
     * @throws TypologyException if there is an SQL error while inserting into the typology table.
     * @throws NotValidParameterException if this SystemAdministrator has no TypologyDAO.
     */
    /*Developed by Vincenza Coppola*/    
    public boolean makeTypology(String typology) throws TypologyException, NotValidParameterException{
        if(typologyDao == null){
            throw new NotValidParameterException("Error in retrieving users");
        }
        return typologyDao.addTypology(typology);
    }

    /**
     * 
     * @return {@code List<String>} the list of the typology table's rows.
     * @throws TypologyException if there is an SQL error while reading from the typology table.
     * @throws NotValidParameterException if this SystemAdministrator has no TypologyDAO.
     */
    /*Developed by Vincenza Coppola*/    
    public List<String> readTypologies() throws TypologyException, NotValidParameterException{
        if(typologyDao == null){
            throw new NotValidParameterException("Error in retrieving users");
        }        
        return typologyDao.viewTypologies();
    }

    /**
     * 
     * @param oldTypology
     * @param newTypology
     * @return{@code boolean} true if the typology is updated into the database.
     * @throws TypologyException if there is an SQL error while updating a row into the typology table.
     * @throws NotValidParameterException if this SystemAdministrator has no TypologyDAO.
     */
    /*Developed by Vincenza Coppola*/    
    public boolean updateTypology(String oldTypology, String newTypology) throws TypologyException, NotValidParameterException{
        if(typologyDao == null){
            throw new NotValidParameterException("Error in retrieving users");
        }
        return typologyDao.modifyTypology(oldTypology, newTypology);
    }
    
    /**
     * 
     * @param typology
     * @return {@code boolean} true if the typology is removed from the database.
     * @throws TypologyException if there is an SQL error while deleting from the typology table.
     * @throws NotValidParameterException if this SystemAdministrator has no TypologyDAO.
     */
     /*Developed by Vincenza Coppola*/    
    public boolean removeTypology(String typology) throws TypologyException, NotValidParameterException{
        if(typologyDao == null){
            throw new NotValidParameterException("Error in retrieving users");
        }
        return typologyDao.deleteTypology(typology);
    }
}

