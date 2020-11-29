/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stub;

import business.maintenanceactivity.Ewo;
import business.maintenanceactivity.ExtraActivity;
import business.maintenanceactivity.MaintenanceProcedure;
import business.maintenanceactivity.Material;
import business.maintenanceactivity.PlannedMaintenanceActivity;
import business.maintenanceactivity.Site;
import exception.MaintenanceActivityException;
import exception.MaterialException;
import exception.SiteException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import persistence.maintenanceactivity.RequiredMaterialForMaintenanceDAO;

/**
 *
 * @author rosar
 */
public class RequiredMaterialForMaintenanceDAOStub implements RequiredMaterialForMaintenanceDAO {

    @Override
    public List<Material> retrieveMaterialsByActivityId(int activityId) throws MaterialException {
        switch (activityId) {
            case 1:
                return new ArrayList<>(){{
                    add(new Material("Materiale1"));
                    add(new Material("Materiale2"));
                    add(new Material("Materiale3"));
                }};
                
            case 2:
                return new ArrayList<>(){{
                    add(new Material("Materiale4"));
                    add(new Material("Materiale5"));
                    add(new Material("Materiale6"));
                }};
                
            case 3:
                return new ArrayList<>(){{
                    add(new Material("Materiale7"));
                    add(new Material("Materiale8"));
                    add(new Material("Materiale9"));
                }};
            case 4:
                return new ArrayList<>(){{
                    add(new Material("Materiale10"));
                    add(new Material("Materiale11"));
                    add(new Material("Materiale12"));
                }};
            case 5:
                return new ArrayList<>(){{
                    add(new Material("Materiale13"));
                    add(new Material("Materiale14"));
                    add(new Material("Materiale15"));
                }};
            case 6:
                return new ArrayList<>(){{
                    add(new Material("Materiale16"));
                    add(new Material("Materiale17"));
                    add(new Material("Materiale18"));
                }};
            case 7:
                return new ArrayList<>();
            default:
                throw new MaterialException();
        }
    }

    @Override
    public boolean addRequiredMaterial(int activityId, List<Material> requiredMaterial) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean removeRequiredMaterial(int activityId, List<Material> requiredMaterial) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Material> retrieveAvaliableMaterialToAdd(int activityId) throws MaterialException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
