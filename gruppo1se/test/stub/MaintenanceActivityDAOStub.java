/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stub;

import business.maintenanceactivity.Ewo;
import business.maintenanceactivity.ExtraActivity;
import business.maintenanceactivity.MaintenanceActivity;
import business.maintenanceactivity.MaintenanceProcedure;
import business.maintenanceactivity.PlannedMaintenanceActivity;
import business.maintenanceactivity.Site;
import exception.MaintenanceActivityException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import persistence.maintenanceactivity.MaintenanceActivityDAO;

/**
 *
 * @author rosar
 */
public class MaintenanceActivityDAOStub implements MaintenanceActivityDAO {

     @Override
    public boolean addMaintenanceActivity(MaintenanceActivity activity) throws MaintenanceActivityException {
        if (activity.getActivityId() == 1 && activity.getSite().getBranchOffice().equals("ProvaBranchOffice") && activity.getSite().getArea().equals("ProvaArea") &&
                activity.getSite().getWorkSpaceNotes().equals("ProvaWorkspaceNotes") && activity.getTypology().equals("ProvaTypology") && 
                activity.getEstimatedInterventionTime() == 30 && activity.getDate().equals(LocalDate.of(2050, 11, 25)) && 
                activity.getMaintenanceProcedure().getSmp().equals("ProvaPDF") && activity.getMaterials().isEmpty()
                && !activity.isInterruptibleActivity() && PlannedMaintenanceActivity.class.isInstance(activity))
            return true;
        else if (activity.getActivityId() == 2 && activity.getSite().getBranchOffice().equals("ProvaBranchOffice") && activity.getSite().getArea().equals("ProvaArea") &&
                activity.getSite().getWorkSpaceNotes().equals("ProvaWorkspaceNotes") && activity.getTypology().equals("ProvaTypology") && 
                activity.getEstimatedInterventionTime() == 30 && activity.getDate().equals(LocalDate.of(2020, 11, 24)) && 
                activity.getMaintenanceProcedure().getSmp().equals("ProvaPDF") && activity.getMaterials().isEmpty() 
                && !activity.isInterruptibleActivity() && PlannedMaintenanceActivity.class.isInstance(activity))
            throw new MaintenanceActivityException();
        else if (activity.getActivityId() == 1 && activity.getSite().getBranchOffice().equals("ProvaBranchOffice") && activity.getSite().getArea().equals("ProvaArea") &&
                activity.getSite().getWorkSpaceNotes().equals("ProvaWorkspaceNotes") && activity.getTypology().equals("ProvaTypology") && 
                activity.getEstimatedInterventionTime() == 30 && activity.getDate().equals(LocalDate.of(2050, 11, 25)) && 
                activity.getMaintenanceProcedure().getSmp().equals("ProvaPDF") && activity.getMaterials().isEmpty()
                && !activity.isInterruptibleActivity() && ExtraActivity.class.isInstance(activity)) 
            return true;
        else if (activity.getActivityId() == 2 && activity.getSite().getBranchOffice().equals("ProvaBranchOffice") && activity.getSite().getArea().equals("ProvaArea") &&
                activity.getSite().getWorkSpaceNotes().equals("ProvaWorkspaceNotes") && activity.getTypology().equals("ProvaTypology") && 
                activity.getEstimatedInterventionTime() == 30 && activity.getDate().equals(LocalDate.of(2020, 11, 24)) && 
                activity.getMaintenanceProcedure().getSmp().equals("ProvaPDF") && activity.getMaterials().isEmpty()
                && !activity.isInterruptibleActivity() && ExtraActivity.class.isInstance(activity))
            throw new MaintenanceActivityException();
        else if (activity.getActivityId() == 1 && activity.getSite().getBranchOffice().equals("ProvaBranchOffice") && activity.getSite().getArea().equals("ProvaArea") &&
                activity.getSite().getWorkSpaceNotes().equals("ProvaWorkspaceNotes") && activity.getTypology().equals("ProvaTypology") && 
                activity.getEstimatedInterventionTime() == 30 && activity.getDate().equals(LocalDate.of(2050, 11, 25)) && 
                activity.getMaintenanceProcedure().getSmp().equals("ProvaPDF") && activity.getMaterials().isEmpty()
                && !activity.isInterruptibleActivity() && Ewo.class.isInstance(activity))
            return true;
        else if (activity.getActivityId() == 2 && activity.getSite().getBranchOffice().equals("ProvaBranchOffice") && activity.getSite().getArea().equals("ProvaArea") &&
                activity.getSite().getWorkSpaceNotes().equals("ProvaWorkspaceNotes") && activity.getTypology().equals("ProvaTypology") && 
                activity.getEstimatedInterventionTime() == 30 && activity.getDate().equals(LocalDate.of(2020, 11, 24)) && 
                activity.getMaintenanceProcedure().getSmp().equals("ProvaPDF") && activity.getMaterials().isEmpty() 
                && !activity.isInterruptibleActivity() && Ewo.class.isInstance(activity))
            throw new MaintenanceActivityException();
        else return false;
    }


    @Override
    public boolean deleteMaintenanceActivity(int activityId) throws MaintenanceActivityException {
        switch(activityId){
            case 1:
                return true;
            case 2:
                return false;
            default:
                throw new MaintenanceActivityException();
        }
    }
    
    
    /*Method developed by ROsario Gaeta*/
    /**
     * Simulates the behaviour of retrieveMaintenanceActivityDao of MaintenanceActivityDAO.
     * @param activityId
     * @return
     * @throws MaintenanceActivityException if activity id is >=7
     */
    @Override
    public MaintenanceActivity retrieveMaintenanceActivityDao(int activityId) throws  MaintenanceActivityException {
        switch (activityId) {
            case 1: /*Case in which an activity is returned.*/
                return new PlannedMaintenanceActivity(activityId, new Site("ProvaBranchOffice", "ProvaArea", "ProvaWorkspaceNotes"),
                        "ProvaTypology", "ProvaDescription", 120, LocalDate.parse("2020-12-20"),
                        null, null,false);
            case 2: /*Case in which an activity is returned (This two case are paired with the cases in 1 and 2 of 
                retrieveMaterialsByActivityId of RequiredMaterialForMaintenanceDAOStub).*/
                return new PlannedMaintenanceActivity(activityId, new Site("ProvaBranchOffice", "ProvaArea", "ProvaWorkspaceNotes"),
                        "ProvaTypology", "ProvaDescription", 120, LocalDate.parse("2020-12-20"),
                        null, null,false);
            case 3: /*Case in which a Planned activity is returned.*/
                return new PlannedMaintenanceActivity(activityId, new Site("ProvaBranchOffice", "ProvaArea", "ProvaWorkspaceNotes"),
                        "ProvaTypology", "ProvaDescription", 120, LocalDate.parse("2020-12-20"),
                        new MaintenanceProcedure("ProvaSmp"), null,true);
            case 4: /*Case in which an Ewo is returned.*/
                return new Ewo(activityId,  new Site("ProvaBranchOffice", "ProvaArea", "ProvaWorkspaceNotes"),
                        "ProvaTypology", "ProvaDescription", 120, LocalDate.parse("2020-12-20"),
                        new MaintenanceProcedure("ProvaSmp"), null,true);
            case 5: /*Case in which an ExtraActivity is returned.*/
                return new ExtraActivity(activityId, new Site("ProvaBranchOffice", "ProvaArea", "ProvaWorkspaceNotes"),
                        "ProvaTypology", "ProvaDescription", 120, LocalDate.parse("2020-12-20"),
                        new MaintenanceProcedure("ProvaSmp"), null,true);
            case 6: /*Case in which is returned a ExtraActivity activity.*/
                return null;
            case 7: /*Case in which site dao returns an exception.*/
                throw new MaintenanceActivityException("SiteException");
            default: /*in the other cases the stub raises an exception */
                throw new MaintenanceActivityException("MaintenanceActivityException");
        }
    }

    @Override
    public boolean modifyMaintenaceActivity(MaintenanceActivity newActivity) throws MaintenanceActivityException {
        if(newActivity.getActivityId()==1 && newActivity.getSite().getBranchOffice().equals("branchOffice1")
                && newActivity.getSite().getArea().equals("area1") && newActivity.getActivityDescription().equals("description1")
                && newActivity.getDate().equals(LocalDate.now()) && newActivity.getEstimatedInterventionTime()==1 
                && newActivity.getTypology().equals("typology1") && newActivity instanceof PlannedMaintenanceActivity){
            return true;
        }else if(newActivity.getActivityId()==2 && newActivity.getSite().getBranchOffice().equals("branchOffice2")
                && newActivity.getSite().getArea().equals("area2") && newActivity.getActivityDescription().equals("description2")
                && newActivity.getDate().equals(LocalDate.now()) && newActivity.getEstimatedInterventionTime()==2 
                && newActivity.getTypology().equals("typology2") && newActivity instanceof PlannedMaintenanceActivity){
            return false;
        }else if(newActivity.getActivityId()==3 && newActivity.getSite().getBranchOffice().equals("branchOffice3")
                && newActivity.getSite().getArea().equals("area3") && newActivity.getActivityDescription().equals("description3")
                && newActivity.getDate().equals(LocalDate.now()) && newActivity.getEstimatedInterventionTime()==3
                && newActivity.getTypology().equals("typology3") && newActivity instanceof PlannedMaintenanceActivity){
            throw new MaintenanceActivityException();
        }else if(newActivity.getActivityId()==4 && newActivity.getSite().getBranchOffice().equals("branchOffice4")
                && newActivity.getSite().getArea().equals("area4") && newActivity.getActivityDescription().equals("description4")
                && newActivity.getDate().equals(LocalDate.now()) && newActivity.getEstimatedInterventionTime()==4 
                && newActivity.getTypology().equals("typology4") && newActivity instanceof Ewo){
            return true;
        }else if(newActivity.getActivityId()==5 && newActivity.getSite().getBranchOffice().equals("branchOffice5")
                && newActivity.getSite().getArea().equals("area5") && newActivity.getActivityDescription().equals("description5")
                && newActivity.getDate().equals(LocalDate.now()) && newActivity.getEstimatedInterventionTime()==5 
                && newActivity.getTypology().equals("typology5") && newActivity instanceof Ewo){
            return false;
        }else if(newActivity.getActivityId()==6 && newActivity.getSite().getBranchOffice().equals("branchOffice6")
                && newActivity.getSite().getArea().equals("area6") && newActivity.getActivityDescription().equals("description6")
                && newActivity.getDate().equals(LocalDate.now()) && newActivity.getEstimatedInterventionTime()==6 
                && newActivity.getTypology().equals("typology6") && newActivity instanceof Ewo){
            throw new MaintenanceActivityException();
        }else if(newActivity.getActivityId()==7 && newActivity.getSite().getBranchOffice().equals("branchOffice7")
                && newActivity.getSite().getArea().equals("area7") && newActivity.getActivityDescription().equals("description7")
                && newActivity.getDate().equals(LocalDate.now()) && newActivity.getEstimatedInterventionTime()==7
                && newActivity.getTypology().equals("typology7") && newActivity instanceof ExtraActivity){
            return true;
        }else if(newActivity.getActivityId()==8 && newActivity.getSite().getBranchOffice().equals("branchOffice8")
                && newActivity.getSite().getArea().equals("area8") && newActivity.getActivityDescription().equals("description8")
                && newActivity.getDate().equals(LocalDate.now()) && newActivity.getEstimatedInterventionTime()==8 
                && newActivity.getTypology().equals("typology8") && newActivity instanceof ExtraActivity){
            return false;
        }else if(newActivity.getActivityId()==9 && newActivity.getSite().getBranchOffice().equals("branchOffice9")
                && newActivity.getSite().getArea().equals("area9") && newActivity.getActivityDescription().equals("description9")
                && newActivity.getDate().equals(LocalDate.now()) && newActivity.getEstimatedInterventionTime()==9 
                && newActivity.getTypology().equals("typology9") && newActivity instanceof ExtraActivity){
            throw new MaintenanceActivityException();
        }else{
            throw new MaintenanceActivityException();
        }   
    }

    @Override
    public List<MaintenanceActivity> retrieveMaintenanceActivityFromRange(LocalDate startDate, LocalDate stopDate) throws MaintenanceActivityException {
        if(startDate.equals(LocalDate.of(2021, Month.JANUARY, 4))){
            throw new MaintenanceActivityException();
        }else if(startDate.equals(LocalDate.of(2021, Month.JANUARY, 11))){
            throw new MaintenanceActivityException();
        }else if(startDate.equals(LocalDate.of(2021, Month.JANUARY, 18))){
            throw new MaintenanceActivityException();
        }else if(startDate.equals(LocalDate.of(2021, Month.JANUARY, 25))){
            return new ArrayList<>(){{
                add(new PlannedMaintenanceActivity(1, new Site("ProvaBranchOffice1", "ProvaArea1"), "ProvaTypology1", "ProvaDescription1", 1, LocalDate.of(2020, Month.JANUARY, 1), null, null, true));
                add(new PlannedMaintenanceActivity(1, new Site("ProvaBranchOffice2", "ProvaArea2"), "ProvaTypology2", "ProvaDescription2", 2, LocalDate.of(2020, Month.JANUARY, 2), null, null,true));
                add(new PlannedMaintenanceActivity(1, new Site("ProvaBranchOffice3", "ProvaArea3"), "ProvaTypology3", "ProvaDescription3", 3, LocalDate.of(2020, Month.JANUARY, 3), null, null,true));
            }};
        }else{
            return new ArrayList();
        }
    }
}
