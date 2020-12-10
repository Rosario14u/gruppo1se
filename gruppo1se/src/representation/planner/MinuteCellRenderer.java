/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package representation.planner;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author gorra
 */
public class MinuteCellRenderer implements TableCellRenderer{
    private static final TableCellRenderer RENDERER = new DefaultTableCellRenderer();
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
       Component c = RENDERER.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
       Color color = null;
        switch(column){
            case 0:
                if(row%2==0)
                  color = new Color(255,179,179);
                else
                  color = new Color(255,204,204);
                break;
            case 1:  
                String[] part =  String.valueOf(table.getValueAt(row, column)).split("/");
                float percentageSatisfiedSkill = Float.valueOf(part[0])/Float.valueOf(part[1]);
                color = skillsRenderer(percentageSatisfiedSkill);
                break;
           default:
                String availabilityValue = String.valueOf(table.getValueAt(row, column)).split(" ")[0];
                int availableMinutes = Integer.valueOf(availabilityValue);
                color = minuteRenderer(availableMinutes);
                break;
        }
       c.setBackground(color);
       return c;
    }
    
    
    private Color skillsRenderer(float percentage) {
        Color color = Color.lightGray;
        if(percentage>0.8 && percentage<=1){
                    color = new Color(0,176,80);
                }else if (percentage>0.6 && percentage<=0.8){
                    color = new Color(146,208,80);
                }else if (percentage>0.4 && percentage<=0.6){
                    color = new Color(255,255,0);
                }else if (percentage>0.2 && percentage<=0.4){
                    color = new Color(255,192,0);
                }else if (percentage>=0 && percentage<=0.2){
                    color = new Color(255,0,0);
                }
        return color;
    }
    
    
    private Color minuteRenderer(float availableMinutes) {
        Color color = Color.lightGray;
        if(availableMinutes>48 && availableMinutes<=60){
                    color = new Color(0,176,80);
                }else if (availableMinutes>36 && availableMinutes<=48){
                    color = new Color(146,208,80);
                }else if (availableMinutes>24 && availableMinutes<=36){
                    color = new Color(255,255,0);
                }else if (availableMinutes>12 && availableMinutes<=24){
                    color = new Color(255,192,0);
                }else if (availableMinutes>=0 && availableMinutes<=12){
                    color = new Color(255,0,0);
                }
        return color;
    }
}
