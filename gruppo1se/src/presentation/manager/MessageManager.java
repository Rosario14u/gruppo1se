/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.manager;

import java.awt.Component;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 *
 * @author rosar
 */
public class MessageManager {
    
    public static void errorMessage(Component component, String message){
        JOptionPane.showMessageDialog(component, message, "ERRORE", JOptionPane.ERROR_MESSAGE);
    }
    
    public static void infoMessage(Component component,String message){
        JOptionPane.showMessageDialog(component, message, "INFO", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static int confirmRequest(Component component, String msg, String title) {
        switch (JOptionPane.showConfirmDialog(component, msg, title, JOptionPane.YES_NO_OPTION)) {
            case JOptionPane.YES_OPTION:
                return EXIT_ON_CLOSE;
            case JOptionPane.NO_OPTION:
                return DO_NOTHING_ON_CLOSE;
            case JOptionPane.CLOSED_OPTION:
                return DO_NOTHING_ON_CLOSE;
            default:
                return DO_NOTHING_ON_CLOSE;
        }
    }
    
    public static int confirmMessage(Component component, String message){
        return JOptionPane.showConfirmDialog(component, message, "Confirm", JOptionPane.YES_NO_OPTION);
    }
}
