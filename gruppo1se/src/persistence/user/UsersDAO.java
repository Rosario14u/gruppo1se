/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.user;

import business.user.User;
import exception.UsersException;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author VincenzaCoppola <v.coppola38@studenti.unisa.it>
 */
public interface UsersDAO {
   public List<User> readUsers() throws UsersException;
   public boolean addUser(User user) throws UsersException;
}
