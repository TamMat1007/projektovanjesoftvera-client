/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.form.components.table;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import rs.ac.bg.fon.ps.domain.Deliverer;
import rs.ac.bg.fon.ps.domain.City;

/**
 *
 * @author Tamara
 */
public class DelivererTableModel extends AbstractTableModel{
    
    private final String[] columnNames= {"ID","Name","LastName", "Phone","Status", "City"};
    private final List<Deliverer> deliverers;

    public DelivererTableModel(List<Deliverer> deliverers) {
        this.deliverers = deliverers;
    }

    @Override
    public String getColumnName(int column) {
        if (column>columnNames.length) return "n/a";
        return columnNames[column]; 
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return (columnIndex==1) || (columnIndex==5);
        //if (columnIndex==1) return true;
        //return false;
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        Deliverer deliverer = deliverers.get(rowIndex);
        switch(columnIndex){
            case 1:
                deliverer.setDelivererName(String.valueOf(value));
                break;
            case 5:
                deliverer.setDelivererCity((City) value);
                break;
        }
    }
    
    @Override
    public int getRowCount() {
        if (deliverers==null) return 0;
        return deliverers.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Deliverer  deliverer = deliverers.get(rowIndex);
        switch(columnIndex){
            case 0: return deliverer.getDelivererID();
            case 1: return deliverer.getDelivererName();
            case 2: return deliverer.getDelivererLastname();
            case 3: return deliverer.getDelivererPhone();
            case 4: return deliverer.getDelivererStatus();
            case 5: return deliverer.getDelivererCity();
            default:
                return "n/a";
        }
    }

    public Deliverer getDelivererAt(int row) {
        return deliverers.get(row);
    }

    public void addDeliverer(Deliverer deliverer) {
        deliverers.add(deliverer);
         //fireTableDataChanged();
        fireTableRowsInserted(deliverers.size()-1, deliverers.size()-1);
    }
}
