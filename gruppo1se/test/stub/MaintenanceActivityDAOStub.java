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
    public boolean modifyMaintenaceActivity(MaintenanceActivity newActivity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
