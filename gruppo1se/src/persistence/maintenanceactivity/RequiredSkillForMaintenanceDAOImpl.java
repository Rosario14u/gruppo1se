/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.maintenanceactivity;

import business.maintenanceactivity.Skill;
import exception.NotValidParameterException;
import exception.SkillException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import persistence.database.ConnectionDB;

/**
 *
 * @author aless
 */
public class RequiredSkillForMaintenanceDAOImpl implements RequiredSkillForMaintenanceDAO{
private final static String SELECT_REQUIRED_SKILL_BY_SMP = "SELECT * FROM RequiredProcedureSkill "
            + "WHERE smp = ? order by skillName";
    private final static String INSERT_REQUIRED_SKILL = "INSERT INTO RequiredProcedureSkill VALUES (?,?)";
    private final static String DELETE_REQUIRED_SKILL = "DELETE FROM RequiredProcedureSkill "
            + "WHERE (smp = ?) and (skillname = ?)";
    private final static String SELECT_AVAILABLE_SKILL = "SELECT * FROM Skill EXCEPT "
            + "SELECT skillname FROM RequiredProcedureSkill WHERE smp=?";
    
    
    /**
     * This method retrieve a list of skills associated to the procedure identified by the smp name.
     * @param smp
     * @return {@code List<String>} listSkills, null otherwise
     * @throws exception.SkillException
     */
    @Override
    public List<Skill> retrieveSkillsBySmp(String smp) throws SkillException{
        List<Skill> listSkills = new ArrayList<>();
        if(smp == null || smp.trim().replaceAll("  +", " ").equals("")){
            throw new SkillException("Retrieving skill failed");
        }
        try {
            Connection conn = ConnectionDB.getInstanceConnection().getConnection();
            PreparedStatement pstm = conn.prepareStatement(SELECT_REQUIRED_SKILL_BY_SMP);
            pstm.setString(1,smp);            
            ResultSet res = pstm.executeQuery();
            while(res.next()){
                listSkills.add(new Skill(res.getString("skillName")));
            }
            return listSkills;
        } catch (SQLException ex) {
            throw new SkillException("Retrieving skill failed");
        }        
    }
    
    
    //================================================================================================================================================
    
    
    @Override
     public boolean addRequiredSkill(String smp, List<Skill> requiredSkill) throws SkillException{
        if(smp == null || smp.trim().replaceAll("  +", " ").equals("") || requiredSkill == null)
            throw new SkillException("Inserting skill failed");
        try {
            Connection conn = ConnectionDB.getInstanceConnection().getConnection();
            for(Skill skill : requiredSkill){
                PreparedStatement pstm = conn.prepareStatement(INSERT_REQUIRED_SKILL);
                pstm.setString(1,smp);            
                pstm.setString(2,skill.getName());            
                pstm.executeUpdate();
            }
            return true;
        } catch (SQLException ex) {
            throw new SkillException("Inserting skill failed");
        }
    }
    
     
    @Override
    public boolean removeRequiredSkill(String smp, List<Skill> requiredSkill) throws SkillException{
        if(smp == null || smp.trim().replaceAll("  +", " ").equals("") || requiredSkill == null)
            throw new SkillException("Deleting skill failed");
        try {
            Connection conn = ConnectionDB.getInstanceConnection().getConnection();
            int notRemovedCounter = 0;
            for(Skill skill : requiredSkill){
                PreparedStatement pstm = conn.prepareStatement(DELETE_REQUIRED_SKILL);
                pstm.setString(1,smp);            
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
    public List<Skill> retrieveAvailableSkillToAdd(String smp) throws SkillException, NotValidParameterException{
        List<Skill> listSkills = new ArrayList<>();
        if(smp == null || smp.trim().replaceAll("  +", " ").equals(""))
            throw new SkillException("Retrieving avaliable skill to use in Maintenance Activity failed");
        try {
            Connection conn = ConnectionDB.getInstanceConnection().getConnection();
            PreparedStatement pstm = conn.prepareStatement(SELECT_AVAILABLE_SKILL);
            pstm.setString(1,smp);           
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
