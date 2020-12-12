/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.user;

import business.maintenanceactivity.Skill;
import exception.NotValidParameterException;
import exception.SkillException;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import persistence.database.ConnectionDB;

/**
 *
 * @author gorra
 */
public class MaintainerSkillDAOImpl implements MaintainerSkillDAO{
    private static String SELECT_SKILLS_FOR_MAINTAINER = "SELECT skillname FROM maintainerSkill WHERE username = ?";

    
    @Override
    public List<Skill> getMaintainerSkills(String username) throws SkillException, NotValidParameterException{
        if (username == null || username.trim().replace("  +", " ").equals(""))
            throw new SkillException("Error in skills loading");
        try {
            List<Skill> maintainerSkills = new ArrayList<>();
            Connection conn = ConnectionDB.getInstanceConnection().getConnection();
            PreparedStatement pstm = conn.prepareStatement(SELECT_SKILLS_FOR_MAINTAINER);
            pstm.setString(1, username);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
               maintainerSkills.add(new Skill(rs.getString("skillname"))); 
            }
            return maintainerSkills;
        } catch (SQLException ex) {
           throw new SkillException("Error in skills loading");
        }
    }
}
