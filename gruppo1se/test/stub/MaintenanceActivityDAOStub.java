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
import business.maintenanceactivity.Material;
import business.maintenanceactivity.PlannedMaintenanceActivity;
import business.maintenanceactivity.Site;
import exception.MaintenanceActivityException;
import exception.SiteException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import persistence.maintenanceactivity.MaintenanceActivityDAO;

/**
 *
 * @author rosar
 */
public class MaintenanceActivityDAOStub implements MaintenanceActivityDAO {

    @Override
    public boolean addMaintenanceActivity(MaintenanceActivity activity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteMaintenanceActivity(int activityId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MaintenanceActivity retrieveMaintenanceActivityDao(int activityId) throws SiteException, MaintenanceActivityException {
        List<Material> listaMaterial;
        switch (activityId) {
            case 1:
                return new PlannedMaintenanceActivity(activityId, new Site("ProvaBranchOffice1", "ProvaArea1", "ProvaWorkspaceNotes1"),
                        "ProvaTypology1", "ProvaDescription1", 121, LocalDate.parse("2020-12-21"), 
                        new MaintenanceProcedure("ProvaSmp1"), null, true);
            case 2:
                return new Ewo(activityId, new Site("ProvaBranchOffice2", "ProvaArea2", "ProvaWorkspaceNotes2"),
                        "ProvaTypology2", "ProvaDescription2", 122, LocalDate.parse("2020-12-22"), 
                        new MaintenanceProcedure("ProvaSmp2"), null, false);
            case 3:
                return new ExtraActivity(activityId, new Site("ProvaBranchOffice3", "ProvaArea3", "ProvaWorkspaceNotes3"),
                        "ProvaTypology3", "ProvaDescription3", 123, LocalDate.parse("2020-12-23"),
                        new MaintenanceProcedure("ProvaSmp3"), null, false);
            case 4:
                return null;
            case 5:
                throw new SiteException("SiteException");
            case 6:
                throw new MaintenanceActivityException("MaintenanceActivityException");
            case 7:
                return new PlannedMaintenanceActivity(activityId, new Site("ProvaBranchOffice7", "ProvaArea7", "ProvaWorkspaceNotes7"),
                        "ProvaTypology7", "ProvaDescription7", 127, LocalDate.parse("2020-12-27"),
                        new MaintenanceProcedure("ProvaSmp7"), null, false);
            default:
                return new PlannedMaintenanceActivity(activityId, new Site("ProvaBranchOffice8", "ProvaArea8", "ProvaWorkspaceNotes8"),
                        "ProvaTypology8", "ProvaDescription8", 128, LocalDate.parse("2020-12-28"),
                        new MaintenanceProcedure("ProvaSmp8"), null, true);
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
    
}
