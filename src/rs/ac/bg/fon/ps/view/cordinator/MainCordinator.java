/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.cordinator;

import java.util.HashMap;
import java.util.Map;
import rs.ac.bg.fon.ps.domain.Operator;
import rs.ac.bg.fon.ps.view.contoller.DelivererController;
import rs.ac.bg.fon.ps.view.contoller.DeliveryController;
import rs.ac.bg.fon.ps.view.contoller.LoginController;
import rs.ac.bg.fon.ps.view.contoller.MainController;
import rs.ac.bg.fon.ps.view.form.FrmDeliverer;
import rs.ac.bg.fon.ps.view.form.FrmLogin;
import rs.ac.bg.fon.ps.view.form.FrmMain;
import rs.ac.bg.fon.ps.view.form.FrmViewDeliverers;
import rs.ac.bg.fon.ps.view.contoller.DelivererViewContoller;
import rs.ac.bg.fon.ps.view.contoller.RestaurantsContoller;
import rs.ac.bg.fon.ps.view.form.FrmDelivery;
import rs.ac.bg.fon.ps.view.form.FrmRestaurants;
import rs.ac.bg.fon.ps.view.form.util.FormMode;

/**
 *
 * @author Tamara
 */
public class MainCordinator {
    private static MainCordinator instance;

    private final MainController mainContoller;
    private final Map <String, Object> params;


    private MainCordinator() {
        mainContoller = new MainController(new FrmMain());
        params = new HashMap<>();
    }
    
    public static MainCordinator getInstance() {
         if (instance == null) {
            instance = new MainCordinator();
        }
        return instance;
    }
    
     public void openLoginForm() {
        LoginController loginContoller = new LoginController(new FrmLogin());
        loginContoller.openForm();
    }
    
    public void openMainForm() {
      mainContoller.openForm();
    }
    
     public void openAddNewDelivererForm() {
       DelivererController delivererController = new DelivererController(new FrmDeliverer(mainContoller.getFrmMain(), true));
       delivererController.openForm(FormMode.FORM_ADD);
    }

    public void openShowAllDelivererForm() {
       DelivererViewContoller viewDelivererContoller = new DelivererViewContoller(new FrmViewDeliverers(mainContoller.getFrmMain(), true));
       viewDelivererContoller.openForm();
    }

    public void openDelivererDetailsDelivererForm() {
        FrmDeliverer delivererDetails = new FrmDeliverer(mainContoller.getFrmMain(), true);
        DelivererController delivererController = new DelivererController(delivererDetails);
        delivererController.openForm(FormMode.FORM_VIEW);
    }

    public void openAddNewDeliveryForm() {
       DeliveryController deliveryController = new DeliveryController(new FrmDelivery(mainContoller.getFrmMain(), true));
       deliveryController.openForm();

    }
    
    public void openRestaurantsForm() {
        RestaurantsContoller restaurantsController= new RestaurantsContoller(new FrmRestaurants(mainContoller.getFrmMain(), true));
        restaurantsController.openForm();
    }

    
    public Object getParam(String name) {
        return params.get(name);
    }
    
    public void addParam(String name, Object key) {
        params.put(name, key);
    }

     public MainController getMainContoller() {
        return mainContoller;
    }

}
