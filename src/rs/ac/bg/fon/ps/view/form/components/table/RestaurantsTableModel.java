/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.form.components.table;

import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import rs.ac.bg.fon.ps.domain.Restaurant;

/**
 *
 * @author Tamara
 */
public class RestaurantsTableModel extends AbstractTableModel{
    
    private final String[] columnNames= {"ID","Name","Address", "Phone","Open Mon-Fri", "Closed Mon-Fri","Open Weekend","Closed Weekend","City"};
    private final List<Restaurant> restaurants;
    SimpleDateFormat sdf= new SimpleDateFormat("HH:mm");
    
    public RestaurantsTableModel(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }
    
    @Override
    public String getColumnName(int column) {
        if (column>columnNames.length) return "n/a";
        return columnNames[column]; 
    }

    @Override
    public int getRowCount() {
        if (restaurants==null) return 0;
        return restaurants.size();
    }

    @Override
    public int getColumnCount() {
        return 9;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Restaurant  restaurant = restaurants.get(rowIndex);
        switch(columnIndex){
            case 0: return restaurant.getRestaurantID();
            case 1: return restaurant.getRestaurantName();
            case 2: return restaurant.getRestaurantAddress();
            case 3: return restaurant.getRestaurantPhone();
            case 4: return sdf.format(restaurant.getOpenWorkingDay());
            case 5: return sdf.format(restaurant.getClosedWorkingDay());
            case 6: return sdf.format(restaurant.getOpenWeekend());
            case 7: return sdf.format(restaurant.getClosedWeekend());
            case 8: return restaurant.getCity();
            default:
                return "n/a";
        }
    }
    
}
