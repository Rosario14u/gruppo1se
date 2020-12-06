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
    
}
