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
    
    /**
     * 
     * @param typology
     * @return 
     * @throws TypologyException 
     */
    @Override
    public boolean addTypology(String typology) throws TypologyException {
        switch (typology) {
            case "exception":
                throw new TypologyException();
            case "false":
                return false;
            default:
                return true;
        }
    }
    
    /**
     * 
     * @return
     * @throws TypologyException 
     */    
    @Override
    public List<String> viewTypologies() throws TypologyException {
        List<String> typology = new ArrayList<>();
        typology.add("Typology1");
        typology.add("Typology2");
        typology.add("Typology3");
        return typology;
    }

    /**
     * 
     * @param oldTypology
     * @param newTypology
     * @return
     * @throws TypologyException 
     */
    @Override
    public boolean modifyTypology(String oldTypology, String newTypology) throws TypologyException {
        if(oldTypology.equals(newTypology))
            return false;
        else if(newTypology.equals("Typology Exception"))
            throw new TypologyException();
        else
            return true;
    }

    /**
     * 
     * @param typology
     * @return
     * @throws TypologyException 
     */    
    @Override
    public boolean deleteTypology(String typology) throws TypologyException {
        switch (typology) {
            case "TypologyFalse":
                return false;
            case "TypologyException":
                throw new TypologyException();
            default:
                return true;
        }
    }

}
