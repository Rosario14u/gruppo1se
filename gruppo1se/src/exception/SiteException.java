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
public class SiteException extends Exception {

    /**
     * Creates a new instance of <code>SiteException</code> without detail
     * message.
     */
    public SiteException() {
    }

    /**
     * Constructs an instance of <code>SiteException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public SiteException(String msg) {
        super(msg);
    }
}
