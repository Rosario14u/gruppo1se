/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exception;

/**
 *
 * @author rosar
 */
public class AppointmentException extends Exception {

    /**
     * Creates a new instance of <code>AppointmentException</code> without
     * detail message.
     */
    public AppointmentException() {
    }

    /**
     * Constructs an instance of <code>AppointmentException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public AppointmentException(String msg) {
        super(msg);
    }
}
