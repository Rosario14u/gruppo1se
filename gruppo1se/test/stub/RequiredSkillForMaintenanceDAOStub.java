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
import persistence.maintenanceactivity.RequiredSkillForMaintenanceDAO;

/**
 *
 * @author aless
 */
public class RequiredSkillForMaintenanceDAOStub implements RequiredSkillForMaintenanceDAO{

    @Override
    public List<Skill> retrieveSkillsByActivityId(int activityId) throws SkillException {
        switch (activityId) {
            case 1:
                return retrieveArrayList("Skill1","Skill2","Skill3");
            case 2:
                return retrieveArrayList("Skill4","Skill5","Skill6");
            case 3:
                return retrieveArrayList("Skill7","Skill8","Skill9");
            case 4:
                return retrieveArrayList("Skil10","Skill1","Skill2");
            case 5:
                return retrieveArrayList("Skill13","Skill14","Skill5");
            case 6:
                return retrieveArrayList("Skill16","Skill17","Skill8");
            case 7:
                return new ArrayList<>();
            default:
                throw new SkillException();
        }
    }
    
    private List<Skill> retrieveArrayList(String material1,String material2, String material3){
        return new ArrayList<>(){{
                    add(new Skill(material1));
                    add(new Skill(material2));
                    add(new Skill(material3));
                }};
    }

    /*============================================================================================================================*/
    
    @Override
    public boolean addRequiredSkill(int activityId, List<Skill> requiredSkill) throws SkillException {      
        switch(activityId){
            case 1:
                return true;
            default:
                throw new SkillException();
        }
    }

    
    @Override
    public boolean removeRequiredSkill(int activityId, List<Skill> requiredSkill) throws SkillException {
        switch (activityId){
            case 3:
                return true;
            case 4:
                return false;
            default:
                throw new SkillException();
        }
    }

    
    @Override
    public List<Skill> retrieveAvailableSkillToAdd(int activityId) throws SkillException {
        switch (activityId){
            case 6:                
                return new ArrayList<>(){{
                    add(new Skill("Skill1"));
                    add(new Skill("Skill2"));
                }};
            case 7:
                return new ArrayList<>();
            default:
                throw new SkillException();
        }
    }
    
}
