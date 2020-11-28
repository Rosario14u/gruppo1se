/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.maintenanceactivity;

import business.maintenanceactivity.Material;
import java.util.List;

/**
 *
 * @author rosar
 */
public interface RequiredMaterialForMaintenanceDAO {
    public List<Material> retrieveMaterialsByActivityId(int activityId);
    public boolean addRequiredMaterial(int activityId, List<Material> requiredMaterial);
    public boolean removeRequiredMaterial(int activityId, List<Material> requiredMaterial);
}
