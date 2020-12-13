/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.maintenanceactivity;

import business.maintenanceactivity.Material;
import exception.MaterialException;
import exception.NotValidParameterException;
import java.util.List;

/**
 *
 * @author rosar
 */
public interface RequiredMaterialForMaintenanceDAO {
    public List<Material> retrieveMaterialsByActivityId(int activityId) throws MaterialException;
    public boolean addRequiredMaterial(int activityId, List<Material> requiredMaterial) throws MaterialException;
    public boolean removeRequiredMaterial(int activityId, List<Material> requiredMaterial) throws MaterialException;
    public List<Material> retrieveAvailableMaterialToAdd(int activityId) throws MaterialException;
}
