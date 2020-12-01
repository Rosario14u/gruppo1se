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
public class NumberNotValidException extends Exception {

    /**
     * Creates a new instance of <code>IdNotValidException</code> without detail
     * message.
     */
    public NumberNotValidException() {
    }

    /**
     * Constructs an instance of <code>IdNotValidException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public NumberNotValidException(String msg) {
        super(msg);
    }
}
