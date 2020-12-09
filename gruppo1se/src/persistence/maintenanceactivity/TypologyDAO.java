/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.maintenanceactivity;

import exception.TypologyException;

/**
 *
 * @author aless
 */
public interface TypologyDAO {
    public boolean addTypology(String typology) throws TypologyException;
}
