/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.contoller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import rs.ac.bg.fon.ps.communication.Communication;
import rs.ac.bg.fon.ps.domain.Deliverer;
import rs.ac.bg.fon.ps.domain.Restaurant;
import rs.ac.bg.fon.ps.view.cordinator.MainCordinator;
import rs.ac.bg.fon.ps.view.form.FrmRestaurants;
import rs.ac.bg.fon.ps.view.form.components.table.DelivererTableModel;
import rs.ac.bg.fon.ps.view.form.components.table.RestaurantsTableModel;

/**
 *
 * @author Tamara
 */
public class RestaurantsContoller {
    private final FrmRestaurants frmRestaurants;

    public RestaurantsContoller(FrmRestaurants frmRestaurants) {
        this.frmRestaurants = frmRestaurants;
    }

    public void openForm() {
        frmRestaurants.setLocationRelativeTo(MainCordinator.getInstance().getMainContoller().getFrmMain());
        prepareView();
        frmRestaurants.setVisible(true);
    }

    private void prepareView() {
        fillTblRestaurants();
    }

    private void fillTblRestaurants() {
        List<Restaurant> restaurants;
        try {
            restaurants = Communication.getInstance().getAllRestaurants();
            RestaurantsTableModel rtm = new RestaurantsTableModel(restaurants);
            frmRestaurants.getTblRestaurants().setModel(rtm);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frmRestaurants, "Error: " + ex.getMessage(), "ERROR DETAILS", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(RestaurantsContoller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
