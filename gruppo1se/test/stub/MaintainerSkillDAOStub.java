/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stub;

import business.maintenanceactivity.Skill;
import exception.SkillException;
import java.util.ArrayList;
import java.util.List;
import persistence.user.MaintainerSkillDAO;

/**
 *
 * @author gorra
 */
public class MaintainerSkillDAOStub implements MaintainerSkillDAO{

    /**
     * Simulates the behaviour of getMaintainerSkills 
     * of MaintainerSkillDAO
     * @param username username of maintainer
     * @return list of skills
     * @throws SkillException 
     */
    @Override
    public List<Skill> getMaintainerSkills(String username) throws SkillException {
        if(username.equals("username1")){
            return new ArrayList<>(){{
                add(new Skill("Skill1"));
                add(new Skill("Skill2"));
            }};
        }else if(username.equals("username2"))
            return new ArrayList<>();
        else
            throw new SkillException();
    }
    
}
