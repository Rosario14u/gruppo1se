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
public class ProcedureException extends Exception {

    /**
     * Creates a new instance of <code>ProcedureException</code> without detail
     * message.
     */
    public ProcedureException() {
    }

    /**
     * Constructs an instance of <code>ProcedureException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ProcedureException(String msg) {
        super(msg);
    }
}
