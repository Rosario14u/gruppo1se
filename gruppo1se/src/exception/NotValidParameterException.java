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
public class NotValidParameterException extends Exception {

    /**
     * Creates a new instance of <code>NotValidParameterException</code> without
     * detail message.
     */
    public NotValidParameterException() {
    }

    /**
     * Constructs an instance of <code>NotValidParameterException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public NotValidParameterException(String msg) {
        super(msg);
    }
}
