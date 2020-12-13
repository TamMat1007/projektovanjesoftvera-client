/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.contoller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import rs.ac.bg.fon.ps.domain.Operator;
import rs.ac.bg.fon.ps.view.constant.Constants;
import rs.ac.bg.fon.ps.view.cordinator.MainCordinator;
import rs.ac.bg.fon.ps.view.form.FrmMain;

/**
 *
 * @author Tamara
 */
public class MainController {
    private final FrmMain frmMain;

    public MainController(FrmMain frmMain) {
       this.frmMain = frmMain;
        addActionListener();
    }

    public void openForm(){
        Operator operator = (Operator) MainCordinator.getInstance().getParam(Constants.PARAM_CURRENT_OPERATOR);
        frmMain.getLblCurrentOperator().setText(operator.getOperatorName()+" " + operator.getOperatorLastname());
        frmMain.setVisible(true);
    }

    private void addActionListener() {
        frmMain.jmiDelivererNewAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                jmiDelivererNewActionPerformed(actionEvent);
            }

            private void jmiDelivererNewActionPerformed(ActionEvent actionEvent) {
               MainCordinator.getInstance().openAddNewDelivererForm();
            }
        });
        
        frmMain.jmiDelivererShowAllAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                jmiDelivererShowAllActionPerformed(actionEvent);
            }

            private void jmiDelivererShowAllActionPerformed(ActionEvent actionEvent) {
                MainCordinator.getInstance().openShowAllDelivererForm();
            }
        });     
        
        frmMain.jmiDeliveryNewAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                jmiDeliveryNewActionPerformed(actionEvent);
            }

            private void jmiDeliveryNewActionPerformed(ActionEvent actionEvent) {
                MainCordinator.getInstance().openAddNewDeliveryForm();
            }
        });
        frmMain.jmiRestaurantsShowAllAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                jmiRestaurantsShowAllActionPerformed(actionEvent);
            }

            private void jmiRestaurantsShowAllActionPerformed(ActionEvent actionEvent) {
                MainCordinator.getInstance().openRestaurantsForm();
            }
        });
    }
    
    
    public FrmMain getFrmMain() {
        return frmMain;
    }
}
