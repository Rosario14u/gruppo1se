/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stub;

import exception.TypologyException;
import java.util.ArrayList;
import java.util.List;
import persistence.maintenanceactivity.TypologyDAO;

/**
 *
 * @author VincenzaCoppola <v.coppola38@studenti.unisa.it>
 */
public class TypologyDAOStub implements TypologyDAO {

    @Override
    public boolean addTypology(String typology) throws TypologyException {
        if(typology.equals("exception"))
            throw new TypologyException();
        else if (typology.equals("false"))
            return false;
        else 
            return true;
    }
    
    
    @Override
    public List<String> viewTypologies() throws TypologyException {
        List<String> typology = new ArrayList<>();
        typology.add("Typology1");
        typology.add("Typology2");
        typology.add("Typology3");
        return typology;
    }
    
}
