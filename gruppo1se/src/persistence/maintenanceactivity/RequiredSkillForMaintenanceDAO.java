/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.maintenanceactivity;

import business.maintenanceactivity.Skill;
import exception.NotValidParameterException;
import exception.SkillException;
import java.util.List;

/**
 *
 * @author aless
 */
public interface RequiredSkillForMaintenanceDAO {
    public List<Skill> retrieveSkillsBySmp(String smp) throws SkillException, NotValidParameterException;
    public boolean addRequiredSkill(String smp, List<Skill> requiredSkill) throws SkillException;
    public boolean removeRequiredSkill(String smp, List<Skill> requiredSkill) throws SkillException;
    public List<Skill> retrieveAvailableSkillToAdd(String smp) throws SkillException, NotValidParameterException;
}
