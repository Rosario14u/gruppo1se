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
                return retrieveArrayList("Material1","Material2","Material3");
            case 2:
                return retrieveArrayList("Material4","Material5","Material6");
            case 3:
                return retrieveArrayList("Material7","Material8","Material9");
            case 4:
                return retrieveArrayList("Material10","Material11","Material12");
            case 5:
                return retrieveArrayList("Material13","Material14","Material5");
            case 6:
                return retrieveArrayList("Material16","Material17","Material8");
            case 7:
                return new ArrayList<>();
            default:
                throw new MaterialException();
        }
    }
    
    private List<Material> retrieveArrayList(String material1,String material2, String material3){
        return new ArrayList<>(){{
                    add(new Material(material1));
                    add(new Material(material2));
                    add(new Material(material3));
                }};
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
