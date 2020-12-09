/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.maintenanceactivity;

import business.maintenanceactivity.Skill;
import exception.SkillException;
import java.util.List;

/**
 *
 * @author aless
 */
public interface RequiredSkillForMaintenanceDAO {
    public List<Skill> retrieveSkillsByActivityId(int activityId) throws SkillException;
    public boolean addRequiredSkill(int activityId, List<Skill> requiredSkill) throws SkillException;
    public boolean removeRequiredSkill(int activityId, List<Skill> requiredSkill) throws SkillException;
    public List<Skill> retrieveAvailableSkillToAdd(int activityId) throws SkillException;
}
