/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.user;

import business.maintenanceactivity.Appointment;
import business.maintenanceactivity.MaintenanceActivity;
import business.maintenanceactivity.MaintenanceActivityFactory;
import business.maintenanceactivity.MaintenanceProcedure;
import business.maintenanceactivity.Material;
import exception.AppointmentException;
import exception.DateException;
import exception.MaintenanceActivityException;
import exception.MaterialException;
import exception.SiteException;
import exception.SkillException;
import exception.UsersException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import persistence.maintenanceactivity.EmployeeAppointmentDAO;
import persistence.maintenanceactivity.RequiredMaterialForMaintenanceDAO;
import persistence.maintenanceactivity.MaintenanceActivityDAO;
import persistence.maintenanceactivity.RequiredSkillForMaintenanceDAO;
import persistence.user.MaintainerSkillDAO;
import persistence.user.UsersDAO;

/**
 *
 * @author rosar
 */
public class Planner extends User {
    private MaintenanceActivityDAO maintenanceActivityDao;
    private RequiredMaterialForMaintenanceDAO requiredMaterialsDao;
    private RequiredSkillForMaintenanceDAO requiredSkillsDao;
    private UsersDAO userDao;
    private EmployeeAppointmentDAO employeeAppointmentDao;
    private MaintainerSkillDAO maintainerSkillDao; 
    //private 
    
    
    /**
     * Constructor of Planner
     * @param username username of Planner 
     * @param password password of Planner
     */
    public Planner(String username, String password) {
        super(username, password);
    }
    
    
    /**
     * Constructor of Planner
     * @param username username of Planner 
     * @param password password of Planner
     * @param maintenanceActivityDao MaintenanceActivityDao object of Planner
     * @param requiredMaterialsDao RequiredMaterialDao object of Planner
     */
    public Planner(String username, String password, MaintenanceActivityDAO maintenanceActivityDao, 
            RequiredMaterialForMaintenanceDAO requiredMaterialsDao) {
        super(username, password);
        this.maintenanceActivityDao = maintenanceActivityDao;
        this.requiredMaterialsDao = requiredMaterialsDao;
    }
    
    
    public Planner(String username, String password, MaintenanceActivityDAO maintenanceActivityDao, 
            RequiredMaterialForMaintenanceDAO requiredMaterialsDao, 
            UsersDAO userDao, EmployeeAppointmentDAO employeeAppointmentDao, 
            RequiredSkillForMaintenanceDAO requiredSkillForMaintenanceDao,MaintainerSkillDAO maintainerSkillDao) {
        super(username, password);
        this.maintenanceActivityDao = maintenanceActivityDao;
        this.requiredMaterialsDao = requiredMaterialsDao;
        this.userDao = userDao;
        this.employeeAppointmentDao = employeeAppointmentDao;
        this.requiredSkillsDao = requiredSkillForMaintenanceDao;
        this.maintainerSkillDao = maintainerSkillDao;
    }
    
    /**
     * This method returns Maintenance Activity with the passed activityId if exists,
     * null otherwise
     * @param activityId activity id of the Maintenance Activity to visualize
     * @return {@code MaintenanceActivity} MaintenanceActivity
     * @throws exception.SiteException
     * @throws exception.MaintenanceActivityException
     * @throws exception.MaterialException
     */
    /*Method developed by Rosario Gaeta*/
    public MaintenanceActivity viewMaintenanceActivity(int activityId) throws SiteException, 
            MaintenanceActivityException, MaterialException{
        /*this method uses MaintenanceActivityDaoImpl and RequiredMaterialForMaintenanceDaoImpl objects to
        retrieve the required MaintenanceActivity object if exists*/
        MaintenanceActivity activity = maintenanceActivityDao.retrieveMaintenanceActivityDao(activityId);
        if(activity != null){
            activity.setMaterials(requiredMaterialsDao.retrieveMaterialsByActivityId(activityId));
        }
        return activity;
    }
    
    
    /**
     * 
     * @param activityId
     * @param branchOffice
     * @param area
     * @param typology
     * @param activityDescription
     * @param estimatedInterventionTime
     * @param date
     * @param maintenanceProcedure
     * @param interruptibleActivity
     * @param typologyOfActivity
     * @param typologyOfUnplannedActivity
     * @return 
     */
    //Developed by Antonio Gorrasi
    public boolean modifyMaintenanceActivity(int activityId, String branchOffice, String area, String typology, String activityDescription, 
            int estimatedInterventionTime, String date, boolean interruptibleActivity, 
            String typologyOfActivity, String typologyOfUnplannedActivity) throws MaintenanceActivityException{
        
        typologyOfActivity = typologyOfActivity.toUpperCase();
        typologyOfUnplannedActivity = typologyOfUnplannedActivity == null ? null : typologyOfUnplannedActivity.toUpperCase();
        
        try{
            MaintenanceActivityFactory.Typology type = typologyOfActivity.compareTo("PLANNED")==0 ? 
                        MaintenanceActivityFactory.Typology.PLANNED : MaintenanceActivityFactory.Typology.valueOf(typologyOfUnplannedActivity);

            MaintenanceActivity newActivity = MaintenanceActivityFactory.make(type, activityId, branchOffice, area, null, typology, activityDescription, 
                    estimatedInterventionTime, date, null, null,interruptibleActivity);

            return maintenanceActivityDao.modifyMaintenaceActivity(newActivity);
        }catch(IllegalArgumentException ex){
            throw new MaintenanceActivityException("Typology of Activity not valid");
        }
    }
    
    
    public boolean removeMaintenanceActivity(int activityId) throws MaintenanceActivityException{
        return maintenanceActivityDao.deleteMaintenanceActivity(activityId);
    }
    
    public boolean makeMaintenanceActivity(int activityId, String branchOffice, String area, String workspaceNotes, String typology, String activityDescription, int estimatedInterventionTime, 
            String date, String smp, List<Material> materials, boolean interruptibleActivity,
            boolean plannedActivity, boolean extraActivity, boolean ewo) throws MaintenanceActivityException, MaterialException{
        MaintenanceActivityFactory.Typology type = null;
        if (plannedActivity)
            type = MaintenanceActivityFactory.Typology.PLANNED;
        else if(extraActivity)
            type = MaintenanceActivityFactory.Typology.EXTRA;
        else
            type = MaintenanceActivityFactory.Typology.EWO;
        MaintenanceActivity activity = MaintenanceActivityFactory.make(type, activityId, branchOffice, area, workspaceNotes, typology, activityDescription, estimatedInterventionTime,
                date, smp, materials, interruptibleActivity);
        if (materials!=null)
            return maintenanceActivityDao.addMaintenanceActivity(activity) && requiredMaterialsDao.addRequiredMaterial(activityId, materials);
        else 
            return maintenanceActivityDao.addMaintenanceActivity(activity);
    }


    //Developed by Antonio Gorrasi
    public boolean addRequiredMaterial(int activityId, List<Material> requiredMaterial) throws MaterialException{
        return requiredMaterialsDao.addRequiredMaterial(activityId, requiredMaterial);
    }
    
    //Developed by Antonio Gorrasi
    public boolean removeRequiredMaterial(int activityId, List<Material> requiredMaterial) throws MaterialException{
        return requiredMaterialsDao.removeRequiredMaterial(activityId, requiredMaterial);
    }
    
    //Developed by Antonio Gorrasi
    public List<Material> retrieveAvaliableMaterialToAdd(int activityId) throws MaterialException{
        return requiredMaterialsDao.retrieveAvailableMaterialToAdd(activityId);
    }
    
    //Developed by Antonio Gorrasi
    public List<MaintenanceActivity> viewMaintenanceActivityByWeek(int week, int year) throws MaintenanceActivityException, SiteException, DateException, SkillException{
        List<LocalDate> date = WeekConverter.getStartEndDate(week, year);
        LocalDate startDateOfWeek = date.get(0);
        LocalDate endDateOfWeek = date.get(1);
        List<MaintenanceActivity> listOfMaintenanceActivity = maintenanceActivityDao.
                retrieveMaintenanceActivityFromRange(startDateOfWeek, endDateOfWeek);
        listOfMaintenanceActivity = filterActivityWithoutProcedure(listOfMaintenanceActivity);
        for(MaintenanceActivity maintainenceActivity: listOfMaintenanceActivity){
            MaintenanceProcedure procedure = maintainenceActivity.getMaintenanceProcedure();
            if (!procedure.getSmp().trim().replaceAll("  +", " ").equals("")){
                procedure.setSkills(requiredSkillsDao.retrieveSkillsBySmp(procedure.getSmp()));
            }
        }
        return listOfMaintenanceActivity;
    }
    
    private List<MaintenanceActivity> filterActivityWithoutProcedure(List<MaintenanceActivity> listActivity) {
        return listActivity.stream().filter(activity -> activity.getMaintenanceProcedure().getSmp()!=null).collect(Collectors.toList());
    }
    
    //Developed by Antonio Gorrasi
    public List<Maintainer> viewEmployeeAvailability(int week, int year) throws UsersException, DateException, AppointmentException, SkillException{
        List<Maintainer> maintainers = userDao.readMaintainers();
        List<LocalDate> date = WeekConverter.getStartEndDate(week, year);
        LocalDate startDateOfWeek = date.get(0);
        LocalDate endDateOfWeek = date.get(1);
        for(Maintainer maintainer: maintainers){
            maintainer.setAppointmentsInWeek(employeeAppointmentDao.getEmployeeAvailability(maintainer.getUsername(),startDateOfWeek,endDateOfWeek)); 
            maintainer.setSkills(maintainerSkillDao.getMaintainerSkills(maintainer.getUsername()));
        }
        return maintainers;
    }
    
    
    public boolean saveAppointments(String username, MaintenanceActivity activity, List<Appointment> appointments)
            throws AppointmentException, MaintenanceActivityException{
        if(username==null || username.trim().replaceAll("  +", " ").equals("") || activity == null || appointments == null)
            throw new AppointmentException("Error in saving appointment");
        boolean addBoolean = employeeAppointmentDao.addEmployeeAvailability(username, appointments);
        if(addBoolean != false)
            return maintenanceActivityDao.modifyMaintenaceActivity(activity);
        return false;
    }
    
    
    //Developed by Antonio Gorrasi
    public boolean verifyActivityAssignment(int activityId, int estimatedInterventionTime) throws MaintenanceActivityException{
        if(activityId <=0 )
            throw new MaintenanceActivityException();
        //int totalTimeAssigned = employeeAppointmentDao.getDurationOfAssignedActivity(activityId);
        //return totalTimeAssigned==estimatedInterventionTime;
        return true;
    }
}
