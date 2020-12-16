/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stub;


import business.maintenanceactivity.Material;
import exception.MaterialException;
import java.util.ArrayList;
import java.util.List;
import persistence.maintenanceactivity.RequiredMaterialForMaintenanceDAO;

/**
 *
 * @author rosar
 */
public class RequiredMaterialForMaintenanceDAOStub implements RequiredMaterialForMaintenanceDAO {
    /**
     * Simulates the behaviour of retrieveMaterialsByActivityId of RequiredMaterialForMaintenanceDAO.
     * @param activityId
     * @return {@code List<Material>}
     * @throws MaterialException 
     */
    @Override
    public List<Material> retrieveMaterialsByActivityId(int activityId) throws MaterialException {
        switch (activityId) {
            case 1:
                /*Simulate a case in which activity has no activity associated*/
                return new ArrayList<>();
            case 2:
                 /*Simulate a case in which the method raises an error*/
                throw new MaterialException();
            default:
                /*default cases is the normal case in which the method returns a list of material*/
                return retrieveArrayList("Material1","Material2","Material3");
        }
    }
    
    private List<Material> retrieveArrayList(String material1,String material2, String material3) {
        return new ArrayList<>(){{
                    add(new Material(material1));
                    add(new Material(material2));
                    add(new Material(material3));
                }};
    }

    /*============================================================================================================================*/
    
    
    /**
     * Simulates the behaviour of addRequiredMaterial 
     * of RequiredMaterialForMaintenanceDAO.
     * @param activityId
     * @param requiredMaterial
     * @return {@code boolean}
     * @throws MaterialException 
     */
    @Override
    public boolean addRequiredMaterial(int activityId, List<Material> requiredMaterial) throws MaterialException {      
        switch(activityId){
            case 1:
                return true;
            default:
                throw new MaterialException();
        }
    }

    
    
    /**
     * Simulates the behaviour of removeRequiredMaterial 
     * of RequiredMaterialForMaintenanceDAO.
     * @param activityId
     * @param requiredMaterial
     * @return {@code boolean}
     * @throws MaterialException 
     */
    @Override
    public boolean removeRequiredMaterial(int activityId, List<Material> requiredMaterial) throws MaterialException {
        switch (activityId){
            case 3:
                return true;
            case 4:
                return false;
            default:
                throw new MaterialException();
        }
    }

    
    /**
     * Simulates the behaviour of retrieveAvailableMaterialToAdd 
     * of RequiredMaterialForMaintenanceDAO.
     * @param activityId
     * @return
     * @throws MaterialException 
     */
    @Override
    public List<Material> retrieveAvailableMaterialToAdd(int activityId) throws MaterialException {
        switch (activityId){
            case 6:
                return new ArrayList<>(){{
                    add(new Material("Material1"));
                    add(new Material("Material2"));
                }};
            case 7:
                return new ArrayList<>();
            default:
                throw new MaterialException();
        }
    }
    
}
