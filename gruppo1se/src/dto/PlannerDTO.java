/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 * The {@code PlannerDTO} class, implements
 * the Data transfer object of Planner
 * @author gorra
 */
public class PlannerDTO extends UserDTO {

    public PlannerDTO(String username, String password) {
        super(username, password);
    }

    @Override
    public String toString() {
        return "PlannerDTO " + super.toString();
    }
    
}
