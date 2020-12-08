/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stub;

import business.user.Maintainer;
import business.user.User;
import exception.UsersException;
import java.util.ArrayList;
import java.util.List;
import persistence.user.UsersDAO;

/**
 *
 * @author VincenzaCoppola <v.coppola38@studenti.unisa.it>
 */
public class UsersDAOStub implements UsersDAO {

    @Override
    public List<User> readUser(String username, String role) throws UsersException {
        if (username == null && role == null)
            return null;
        if (username == null)
            return new ArrayList<>(){{
            add(new Maintainer("maintainerUsername","maintainerPassword"));
            add(new Maintainer("maintainer2Username","maintainer2Password"));
            add(new Maintainer("maintainer3Username","maintainer3Password"));
            }};
        else if (role == null)
            return new ArrayList<>(){{
                add(new Maintainer(username,"maintainerPassword"));
            }};
        else
            throw new UsersException();
    }
    
    @Override
    public boolean addUser(User user) throws UsersException {
        if (user.getUsername() != null && user.getPassword() != null)
            return true;
        else 
            throw new UsersException();
    }
    
    @Override
    public boolean updateUser(String oldUsername, User newUser) throws UsersException{
        System.out.println("popopopopopopopo");
        if (oldUsername==null || newUser==null || newUser.getUsername()==null || newUser.getPassword()==null
                || oldUsername.equals("") || newUser.getUsername().equals("")){
            ////case where the passed parameters are not valid
            System.out.println("QUAQUAUQUAUQAU");
            throw new UsersException();            
        } else if(oldUsername.equals("oldUsername1")){
            //case where the passed parameters are valid and the method successfully updates a row
            System.out.println(1);
            return true;
        } else if(oldUsername.equals("oldUsername2")){
            //case where the passed parameters are valid and the method does not update any rows
            System.out.println(2);
            return false;
        } else if(oldUsername.equals("oldUsername3")){
            //case where the method throws a SQLException
            throw new UsersException();
        }  else {
            throw new UsersException();
        }
    }
    
    
//    @Override
//    public boolean updateUser(String oldUsername, User newUser) throws UsersException{
//        if(oldUsername.equals("oldUsername1") && newUser.getUsername().equals("newUsername1") 
//                && newUser.getPassword().equals("newPassword1") && newUser instanceof Planner){  
//            System.out.println(1);
//            return true;
//        } else if(oldUsername.equals("oldUsername2") && newUser.getUsername().equals("newUsername2") 
//                && newUser.getPassword().equals("newPassword2") && newUser instanceof Planner){
//            System.out.println(2);
//            return false;
//        } else if(oldUsername.equals("oldUsername3") && newUser.getUsername().equals("newUsername3") 
//                && newUser.getPassword().equals("newPassword3") && newUser instanceof Planner){
//            System.out.println(3);
//            throw new UsersException();
//        } else if(oldUsername.equals("oldUsername4") && newUser.getUsername().equals("newUsername4") 
//                && newUser.getPassword().equals("newPassword4") && newUser instanceof Maintainer){
//            System.out.println(4);
//            return true;
//        } else if(oldUsername.equals("oldUsername5") && newUser.getUsername().equals("newUsername5") 
//                && newUser.getPassword().equals("newPassword5") && newUser instanceof Maintainer){
//            System.out.println(5);
//            return false;
//        } else if(oldUsername.equals("oldUsername6") && newUser.getUsername().equals("newUsername6") 
//                && newUser.getPassword().equals("newPassword6") && newUser instanceof Maintainer){
//            System.out.println(6);
//            throw new UsersException();
//        } else if(oldUsername.equals("oldUsername7") && newUser.getUsername().equals("newUsername7") 
//                && newUser.getPassword().equals("newPassword7") && newUser instanceof SystemAdministrator){
//            System.out.println(7);
//            return true;
//        } else if(oldUsername.equals("oldUsername8") && newUser.getUsername().equals("newUsername8") 
//                && newUser.getPassword().equals("newPassword8") && newUser instanceof SystemAdministrator){
//            System.out.println(8);
//            return false;
//        } else if(oldUsername.equals("oldUsername9") && newUser.getUsername().equals("newUsername9") 
//                && newUser.getPassword().equals("newPassword9") && newUser instanceof SystemAdministrator){
//            System.out.println(9);
//            throw new UsersException();
//        } else {
//            System.out.println(10);
//            throw new UsersException();
//        }
//    }
}
