/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.user;

import dto.MaintainerDTO;
import dto.UserDTO;
import exception.NotValidParameterException;
import exception.UsersException;
import java.util.List;

/**
 *
 * @author VincenzaCoppola <v.coppola38@studenti.unisa.it>
 */
public interface UsersDAO {
   public List<UserDTO> readUsers() throws UsersException, NotValidParameterException;
   public boolean addUser(UserDTO user) throws UsersException;
   public int deleteUsers(List<String> usernameList) throws UsersException;
   public boolean updateUser(String oldUsername, UserDTO newUser) throws UsersException;
   public List<MaintainerDTO> readMaintainers() throws UsersException;
}
