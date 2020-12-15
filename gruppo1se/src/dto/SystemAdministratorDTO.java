/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 * The {@code SystemAdministratorDTO} class, implements
 * the Data transfer object of SystemAdministrator
 * @author gorra
 */
public class SystemAdministratorDTO extends UserDTO {

    public SystemAdministratorDTO(String username, String password) {
        super(username, password);
    }

    @Override
    public String toString() {
        return "SystemAdministratorDTO " + super.toString();
    }
    
}
