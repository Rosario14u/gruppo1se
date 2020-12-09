/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.maintenanceactivity;

import business.maintenanceactivity.Skill;
import exception.SkillException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistence.database.ConnectionDB;

/**
 *
 * @author aless
 */
public class RequiredSkillForMaintenanceDAOImpl implements RequiredSkillForMaintenanceDAO{
private final static String SELECT_REQUIRED_SKILL_BY_ACTIVITY_ID = "SELECT * FROM RequiredSkill "
            + "WHERE activityId = ? order by skillName";
    private final static String INSERT_REQUIRED_SKILL = "INSERT INTO RequiredSkill VALUES (?,?)";
    private final static String DELETE_REQUIRED_SKILL = "DELETE FROM RequiredSkill "
            + "WHERE (activityid = ?) and (skillname = ?)";
    private final static String SELECT_AVAILABLE_SKILL = "SELECT * FROM Skill EXCEPT "
            + "SELECT skillname FROM requiredskill WHERE activityid=?";
    /**
     * This method retrieve a list of skills associated to the maintenance activity identified by the activityId.
     * @param activityId of the MaintenanceActivity
     * @return {@code List<String>} listSkills, null otherwise
     * @throws exception.SkillException
     */
    @Override
    public List<Skill> retrieveSkillsByActivityId(int activityId) throws SkillException{
        List<Skill> listSkills = new ArrayList<>();
        try {
            Connection conn = ConnectionDB.getInstanceConnection().getConnection();
            PreparedStatement pstm = conn.prepareStatement(SELECT_REQUIRED_SKILL_BY_ACTIVITY_ID);
            pstm.setInt(1,activityId);            
            ResultSet res = pstm.executeQuery();
            while(res.next()){
                listSkills.add(new Skill(res.getString("skillName")));
            }
            return listSkills;
        } catch (SQLException ex) {
            throw new SkillException();
        }        
    }
    
    
    
    //================================================================================================================================================
    @Override
     public boolean addRequiredSkill(int activityId, List<Skill> requiredSkill) throws SkillException{
        try {
            Connection conn = ConnectionDB.getInstanceConnection().getConnection();
            for(Skill skill : requiredSkill){
                PreparedStatement pstm = conn.prepareStatement(INSERT_REQUIRED_SKILL);
                pstm.setInt(1,activityId);            
                pstm.setString(2,skill.getName());            
                pstm.executeUpdate();
            }
            return true;
        } catch (SQLException ex) {
            throw new SkillException("Inserting skill failed");
        }
    }
     
    @Override
    public boolean removeRequiredSkill(int activityId, List<Skill> requiredSkill) throws SkillException{
        try {
            Connection conn = ConnectionDB.getInstanceConnection().getConnection();
            int notRemovedCounter = 0;
            for(Skill skill : requiredSkill){
                PreparedStatement pstm = conn.prepareStatement(DELETE_REQUIRED_SKILL);
                pstm.setInt(1,activityId);            
                pstm.setString(2,skill.getName());
                if(pstm.executeUpdate() == 0){
                    notRemovedCounter++;
                }
            }
            return !(notRemovedCounter>0);
        } catch (SQLException ex) {
            throw new SkillException("Deleting skill failed");
        }
    }
    
    @Override
    public List<Skill> retrieveAvailableSkillToAdd(int activityId) throws SkillException{
        List<Skill> listSkills = new ArrayList<>();
        try {
            Connection conn = ConnectionDB.getInstanceConnection().getConnection();
            PreparedStatement pstm = conn.prepareStatement(SELECT_AVAILABLE_SKILL);
            pstm.setInt(1,activityId);           
            ResultSet res = pstm.executeQuery();
            while(res.next()){
                listSkills.add(new Skill(res.getString("skillName")));
            }
            return listSkills;
        } catch (SQLException ex) {
            throw new SkillException("Retrieving avaliable skill to use in Maintenance Activity failed");
        }
    }
}
