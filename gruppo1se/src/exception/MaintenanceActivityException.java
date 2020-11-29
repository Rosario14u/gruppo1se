/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exception;

/**
 *
 * @author gorra
 */
public class MaintenanceActivityException extends Exception {

    /**
     * Creates a new instance of <code>MaintenanceActivityException</code>
     * without detail message.
     */
    public MaintenanceActivityException() {
    }

    /**
     * Constructs an instance of <code>MaintenanceActivityException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public MaintenanceActivityException(String msg) {
        super(msg);
    }
}
