/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uniquejewerlydesings.control;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author willi
 */
public class pintarTabla extends DefaultTableCellRenderer {

    Font normal = new Font("Arial", Font.PLAIN, 12);
    Font negrilla = new Font("Helvetica", Font.BOLD, 18);
    Font cursiva = new Font("Times new roman", Font.ITALIC, 12);

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int column) {
        setEnabled(table == null || table.isEnabled());

        setBackground(Color.WHITE);//color de fondo

        String val = String.valueOf(table.getValueAt(row, 4)).toString();
        if (Integer.parseInt(val) < 3) {
            setBackground(Color.YELLOW);
            if (Integer.parseInt(val) ==0) {
                setBackground(Color.red);
            }
        }
        super.getTableCellRendererComponent(table, value, selected, focused, row, column);
        return this;
    }

}
