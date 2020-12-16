/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.user;

import business.maintenanceactivity.Appointment;
import business.maintenanceactivity.MaintenanceActivity;
import business.maintenanceactivity.MaintenanceActivityFactory;
import business.maintenanceactivity.MaintenanceActivityFactory.Typology;
import business.maintenanceactivity.MaintenanceProcedure;
import business.maintenanceactivity.Material;
import business.maintenanceactivity.Site;
import dto.MaintainerDTO;
import exception.AppointmentException;
import exception.DateException;
import exception.MaintenanceActivityException;
import exception.MaterialException;
import exception.NotValidParameterException;
import exception.SkillException;
import exception.TypologyException;
import exception.UsersException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import persistence.maintenanceactivity.EmployeeAppointmentDAO;
import persistence.maintenanceactivity.RequiredMaterialForMaintenanceDAO;
import persistence.maintenanceactivity.MaintenanceActivityDAO;
import persistence.maintenanceactivity.RequiredSkillForMaintenanceDAO;
import persistence.maintenanceactivity.TypologyDAO;
import persistence.user.MaintainerSkillDAO;
import persistence.user.UsersDAO;

/**
 * <p>An object of the class Planner provides a series of 
 * method to manage Maintenance Activity<br>
 * and to assign this activities to maintainers. This class requires a 
 * series of dao object (as attributes) to performs this operation.</p> 
 * @author rosar
 */
public class Planner extends User {

    private final MaintenanceActivityDAO maintenanceActivityDao;
    private final RequiredMaterialForMaintenanceDAO requiredMaterialsDao;
    private final RequiredSkillForMaintenanceDAO requiredSkillsDao;
    private final UsersDAO userDao;
    private final EmployeeAppointmentDAO employeeAppointmentDao;
    private final MaintainerSkillDAO maintainerSkillDao;
    private final TypologyDAO typologyDao;
    
    /**
     * Constructor of Planner
     * @param username username of Planner
     * @param password password of Planner
     * @param maintenanceActivityDao Dao object of Maintenance Activity
     * @param requiredMaterialsDao  Dao object to access the material associated with activity
     * @param userDao Dao object of Users
     * @param employeeAppointmentDao Dao object to access the appointments of activity
     * @param requiredSkillForMaintenanceDao Dao object to access the required skill of a maintenance
     * @param maintainerSkillDao Dao object to access the skills of a maintainer
     * @param typologyDao
     */
    public Planner(String username, String password, MaintenanceActivityDAO maintenanceActivityDao,
            RequiredMaterialForMaintenanceDAO requiredMaterialsDao,
            UsersDAO userDao, EmployeeAppointmentDAO employeeAppointmentDao,
            RequiredSkillForMaintenanceDAO requiredSkillForMaintenanceDao, MaintainerSkillDAO maintainerSkillDao, TypologyDAO typologyDao) {
        super(username, password);
        this.maintenanceActivityDao = maintenanceActivityDao;
        this.requiredMaterialsDao = requiredMaterialsDao;
        this.userDao = userDao;
        this.employeeAppointmentDao = employeeAppointmentDao;
        this.requiredSkillsDao = requiredSkillForMaintenanceDao;
        this.maintainerSkillDao = maintainerSkillDao;
        this.typologyDao = typologyDao;
    }

    public RequiredMaterialForMaintenanceDAO getRequiredMaterialsDao() {
        return requiredMaterialsDao;
    }

    /**
     * This method returns Maintenance Activity with the passed activityId if
     * exists, null otherwise<br>
     * MaintenanceActivityDao and RequiredMaterialsDao are required
     * @param activityId activity id of the Maintenance Activity to visualize
     * @return {@code MaintenanceActivity} MaintenanceActivity
     * @throws exception.MaintenanceActivityException it there is a problem in retrieving the activity
     * @throws exception.NotValidParameterException if the required DAO (MaintenanceActivityDao and RequiredMaterialsDao)<br> 
     * objects are not correctly initialized
     */
    /*Method developed by Rosario Gaeta*/
    public MaintenanceActivity viewMaintenanceActivity(int activityId) throws MaintenanceActivityException, NotValidParameterException {
        /*this method uses MaintenanceActivityDaoImpl and RequiredMaterialForMaintenanceDaoImpl objects to
        retrieve the required MaintenanceActivity object if exists*/
        try{
            if (maintenanceActivityDao == null || requiredMaterialsDao == null) {
                throw new NotValidParameterException("Failure to retrieve data relating to maintenance activities");
            }
            MaintenanceActivity activity = maintenanceActivityDao.retrieveMaintenanceActivityDao(activityId);
            if (activity != null) {
                activity.setMaterials(requiredMaterialsDao.retrieveMaterialsByActivityId(activityId));
            }
            return activity;
        }catch(MaterialException ex){
            throw new MaintenanceActivityException("Error in retrieving activity");
        }
    }

   
    /**
     * This method allows to modify an existent Maintenance
     * activity acccording to actvityId parameter
     * @param activityId identifier of maintenance activity
     * @param typology type of activity
     * @param activityDescription description of activity
     * @param estimatedInterventionTime estimated time
     * @param date date of maintenance activity
     * @param interruptibleActivity represents 
     * whether an activity is interruptible or not 
     * @param typologyOfActivity  
     * @return {@code true} if the the change
     * is successful, {@code false} otherwise
     * @throws MaintenanceActivityException it there is a 
     * problem in modifyng the activity
     * @throws NotValidParameterException if the required DAO 
     * (MaintenanceActivityDao) object is not correctly initialized
     */
    //Developed by Antonio Gorrasi
    public boolean modifyMaintenanceActivity(int activityId, Site site, 
            String typology, String activityDescription, int estimatedInterventionTime, 
            LocalDate date, boolean interruptibleActivity, Typology typologyOfActivity)
            throws MaintenanceActivityException, NotValidParameterException {

        if (maintenanceActivityDao == null) {
            throw new NotValidParameterException("Failure to modify data relating to activities");
        }

        MaintenanceActivity newActivity = MaintenanceActivityFactory.make(typologyOfActivity, 
                activityId, site, typology, activityDescription, 
                estimatedInterventionTime, date, null, null, interruptibleActivity);

        return maintenanceActivityDao.modifyMaintenaceActivity(newActivity);
    }

    /**
     * 
     * @param activityId
     * @return {@code boolean} true if MaintenanceActivity is removed from database.
     * @throws MaintenanceActivityException if there's an SQL error while deleting the activity.
     * @throws NotValidParameterException if this Planner has no MaintenanceActivityDAO.
     */
    /*Developed by Vincenza Coppola*/    
    public boolean removeMaintenanceActivity(int activityId) throws MaintenanceActivityException, NotValidParameterException {
        if (maintenanceActivityDao == null) {
            throw new NotValidParameterException("Failure to remove data relating to maintenance activities");
        }
        return maintenanceActivityDao.deleteMaintenanceActivity(activityId);
    }

    /**
     * 
     * @param activityId
     * @param site
     * @param typology
     * @param procedure
     * @param activityDescription
     * @param estimatedInterventionTime
     * @param date
     * @param materials
     * @param interruptibleActivity
     * @param typologyOfActivity
     * @return {@code boolean} true if MaintenanceActivity and materials are inserted into the database 
     * @throws MaintenanceActivityException if there's an SQL error while inserting into the MaintenanceActivity table
     * @throws NotValidParameterException if this planner has no MaintenanceActivityDAO
     */
    /* Method developed by Alessio Citro*/
    public boolean makeMaintenanceActivity(int activityId, Site site, String typology, String activityDescription, int estimatedInterventionTime,
            LocalDate date, MaintenanceProcedure procedure, List<Material> materials, boolean interruptibleActivity,
            Typology typologyOfActivity) throws MaintenanceActivityException, NotValidParameterException {
        if (maintenanceActivityDao == null) {
            throw new NotValidParameterException("Failure to create data relating to maintenance activities");
        } 
        try{       
            MaintenanceActivity activity = MaintenanceActivityFactory.make(typologyOfActivity, activityId,
                    site, typology, activityDescription, estimatedInterventionTime,
                    date, procedure, materials, interruptibleActivity);
            if (materials != null) {
                return maintenanceActivityDao.addMaintenanceActivity(activity) && requiredMaterialsDao.addRequiredMaterial(activityId, materials);
            } else {
                return maintenanceActivityDao.addMaintenanceActivity(activity);
            }
        }catch(MaterialException ex){
            throw new MaintenanceActivityException(ex.getMessage());
        }
    }
    
    public List<Material> viewRequiredMaterialsByActivityId(int activityId) throws MaterialException, NotValidParameterException {
        if (requiredMaterialsDao == null) {
            throw new NotValidParameterException("Failure to create data relating to maintenance activities");
        }
        return requiredMaterialsDao.retrieveMaterialsByActivityId(activityId);
    }

    
    /**
     * This method allows to associate materials with an activity
     * @param activityId identifier of maintenance activity
     * @param requiredMaterial list of materials to associate
     * @return {@code true} if the materials are successfully 
     * associated, {@code false} otherwise
     * @throws MaterialException if there are problems in adding materials
     * @throws NotValidParameterException if the required DAO 
     * (requiredMaterialsDao) object is not correctly initialized
     */
    //Developed by Antonio Gorrasi
    public boolean addRequiredMaterial(int activityId, List<Material> requiredMaterial) 
            throws MaterialException, NotValidParameterException {
        
        if (requiredMaterialsDao == null) {
            throw new NotValidParameterException("Failure to associate materials to activity");
        }
        return requiredMaterialsDao.addRequiredMaterial(activityId, requiredMaterial);
    }

    
    /**
     * This method allows to delete materials previously
     * associated with maintenance activities
     * @param activityId identifier of activity
     * @param requiredMaterial list of materials to remove
     * @return {@code true} if the materials are successfully 
     * removed, {@code false} otherwise
     * @throws MaterialException if there are 
     * problems in removing materials
     * @throws NotValidParameterException if the required DAO 
     * (requiredMaterialsDao) object is not correctly initialized 
     */
    //Developed by Antonio Gorrasi
    public boolean removeRequiredMaterial(int activityId, List<Material> requiredMaterial) 
            throws MaterialException, NotValidParameterException {
        if (requiredMaterialsDao == null) {
            throw new NotValidParameterException("Failure to remove assoicated materials of the activity");
        }
        return requiredMaterialsDao.removeRequiredMaterial(activityId, requiredMaterial);
    }

    
    /**
     * This method returns a list of available materials that
     * have not yet been associated with the maintenance activity
     * @param activityId identifier of activity
     * @return a list of available materials
     * @throws MaterialException if there are 
     * problems in retrieving available materials
     * @throws NotValidParameterException if the required DAO 
     * (requiredMaterialsDao) object is not correctly initialized 
     */
    //Developed by Antonio Gorrasi
    public List<Material> retrieveAvaliableMaterialToAdd(int activityId) 
            throws MaterialException, NotValidParameterException {
        if (requiredMaterialsDao == null) {
            throw new NotValidParameterException("Failure to retrive associated materials to activity");
        }
        return requiredMaterialsDao.retrieveAvailableMaterialToAdd(activityId);
    }

    
    /**
     * This method returns a list of maintenance 
     * activities in a specific week of the year
     * @param week desired week
     * @param year year relative to the desired week
     * @return a list of Maintenance Activity
     * in a week 
     * @throws MaintenanceActivityException if there 
     * are problems in retrieving activities materials
     * @throws NotValidParameterException if the required 
     * DAO (maintenanceActivityDao, requiredSkillsDao) <br>
     * objects are not correctly initialized 
     */
    //Developed by Antonio Gorrasi
    public List<MaintenanceActivity> viewMaintenanceActivityByWeek(int week, int year) 
            throws MaintenanceActivityException, NotValidParameterException {
        
        if (maintenanceActivityDao == null || requiredSkillsDao == null) {
            throw new NotValidParameterException("Failure to retrieve data relating to activities");
        }
        try { 
            List<LocalDate> date = WeekConverter.getStartEndDate(week, year);
            LocalDate startDateOfWeek = date.get(0);
            LocalDate endDateOfWeek = date.get(1);
            
            List<MaintenanceActivity> listOfMaintenanceActivity = maintenanceActivityDao
                    .retrieveMaintenanceActivityFromRange(startDateOfWeek, endDateOfWeek);
            
            listOfMaintenanceActivity = filterActivityWithoutProcedure(listOfMaintenanceActivity);
            
            for (MaintenanceActivity maintainenceActivity : listOfMaintenanceActivity) {
                MaintenanceProcedure procedure = maintainenceActivity.getMaintenanceProcedure();
                if (!procedure.getSmp().trim().replaceAll("  +", " ").equals("")) {
                    procedure.setSkills(requiredSkillsDao.retrieveSkillsBySmp(procedure.getSmp()));
                }
            }
            return listOfMaintenanceActivity;
        } catch (DateException | SkillException ex) {
            throw new MaintenanceActivityException(ex.getMessage());
        }
    }
    
    
    /**
     * This method allows to filter a list of maintenance activity,
     * getting only the activity that have a not null procedure.
     * @param listActivity list of activity
     * @return {@code List<MaintenanceActivity>} listOfMaintenanceActivity
     */
    private List<MaintenanceActivity> filterActivityWithoutProcedure(List<MaintenanceActivity> listActivity) {
        return listActivity.stream().filter(activity -> activity.getMaintenanceProcedure() != null).collect(Collectors.toList());
    }

    
    /**
     * This method returns the availability of the maintainers
     * @param week desired week
     * @param year year relative to the desired week
     * @return the availability of the maintainers
     * @throws UsersException if there are problems in 
     * retrieving maintainers availability
     * @throws NotValidParameterException if the required 
     * DAO (userDao, maintainerSkillDao, employeeAppointmentDao) 
     * <br>objects are not correctly initialized 
     */
    //Developed by Antonio Gorrasi
    public List<MaintainerDTO> viewEmployeeAvailability(int week, int year) 
            throws UsersException, NotValidParameterException {
        if (userDao == null || employeeAppointmentDao == null || maintainerSkillDao == null) {
                throw new NotValidParameterException("Failure to retrieve data relating to Maintainers");
        }
        try {
            List<MaintainerDTO> maintainers = userDao.readMaintainers();
            List<LocalDate> date = WeekConverter.getStartEndDate(week, year);
            LocalDate startDateOfWeek = date.get(0);
            LocalDate endDateOfWeek = date.get(1);
            for (MaintainerDTO maintainer : maintainers) {
                maintainer.setAppointmentsInWeek(employeeAppointmentDao
                        .getEmployeeAvailability(maintainer.getUsername(), startDateOfWeek, endDateOfWeek));
                maintainer.setSkills(maintainerSkillDao.getMaintainerSkills(maintainer.getUsername()));
            }
            return maintainers;
        } catch (DateException | AppointmentException | SkillException ex) {
            throw new UsersException(ex.getMessage());
        }
    }
    
    
    /**
     * This method allows to save appointments of a maintainer associated with a maintenance activity and<br>
     * updates the maintenance activity date. Returns true if the operation is successful, false otherwise.<br>
     * EmployeeAppointmentDao and MaintenanceActivityDao are required.
     * @param username Username of the maintainer
     * @param activity Maintenance activity to be assigned to the maintainer
     * @param appointments Appointments to save
     * @return {@code boolean} successfulOperation
     * @throws AppointmentException if username is not valid or appointemnts list is null
     * @throws MaintenanceActivityException if activity passed is null
     * @throws NotValidParameterException if required dao ( EmployeeAppointmentDao and MaintenanceActivityDao) <br>
     * are not correctly initialized.
     */
    /*Developed by Rosario Gaeta*/
    public boolean saveAppointments(String username, MaintenanceActivity activity, List<Appointment> appointments)
            throws AppointmentException, MaintenanceActivityException, NotValidParameterException {
        if (employeeAppointmentDao == null || maintenanceActivityDao == null) {
            throw new NotValidParameterException("Failure to save appointments relating to Maintainer");
        }
        if (username == null || username.trim().replaceAll("  +", " ").equals("") || appointments == null) {
            throw new AppointmentException("Error in saving appointment");
        }
        if (activity == null) {
            throw new MaintenanceActivityException("Error in saving new activity date");
        }
        boolean addBoolean = employeeAppointmentDao.addEmployeeAvailability(username, appointments);
        if (addBoolean != false) {
            return maintenanceActivityDao.modifyMaintenaceActivity(activity);
        }
        return false;
    }

    
    /**
     * This method allows to check if an activity 
     * has already been previously associated
     * @param activityId identifier of activity
     * @param estimatedInterventionTime time of 
     * intervention
     * @return {@code true} if the task was previously 
     * assigned, {@code false} otherwise
     * @throws NotValidParameterException if 
     * required dao (employeeAppointmentDao)
     * are not correctly initialized.
     * @throws AppointmentException if there are 
     * problems in verifyng maintainers availability
     */
    //Developed by Antonio Gorrasi
    public boolean verifyActivityAssignment(int activityId, int estimatedInterventionTime)
            throws NotValidParameterException, AppointmentException {
        
        if (employeeAppointmentDao == null) {
            throw new NotValidParameterException("Failure to verify appointment");
        }
        int totalTimeAssigned = employeeAppointmentDao.getDurationOfAssignedActivity(activityId);
        return totalTimeAssigned==estimatedInterventionTime;
    }
    
    
    /**
     * 
     * @return a list of Strings, representing all the typologies stored into the database
     * @throws TypologyException
     * @throws NotValidParameterException 
     */
    /*Developed by Vincenza Coppola*/   
    public List<String> readTypologies() throws TypologyException, NotValidParameterException{
        if(typologyDao == null){
            throw new NotValidParameterException("Error in retrieving users");
        }      
        return typologyDao.viewTypologies();
    }
}
