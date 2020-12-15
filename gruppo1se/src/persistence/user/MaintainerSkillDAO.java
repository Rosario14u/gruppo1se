/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.user;

import business.maintenanceactivity.Skill;
import exception.SkillException;
import java.util.List;

/**
 *
 * @author gorra
 */
public interface MaintainerSkillDAO {
    public List<Skill> getMaintainerSkills(String username) throws SkillException;
}
