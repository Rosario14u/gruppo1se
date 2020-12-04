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
public class DateException extends Exception {

    /**
     * Creates a new instance of <code>DateException</code> without detail
     * message.
     */
    public DateException() {
    }

    /**
     * Constructs an instance of <code>DateException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public DateException(String msg) {
        super(msg);
    }
}
