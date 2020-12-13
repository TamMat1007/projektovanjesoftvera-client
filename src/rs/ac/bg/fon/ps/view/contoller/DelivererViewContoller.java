/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.contoller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import rs.ac.bg.fon.ps.communication.Communication;
import rs.ac.bg.fon.ps.domain.Deliverer;
import rs.ac.bg.fon.ps.view.constant.Constants;
import rs.ac.bg.fon.ps.view.cordinator.MainCordinator;
import rs.ac.bg.fon.ps.view.form.FrmViewDeliverers;
import rs.ac.bg.fon.ps.view.form.components.table.DelivererTableModel;

/**
 *
 * @author Tamara
 */
public class DelivererViewContoller {
    private final FrmViewDeliverers frmViewDeliverers;
    
    public DelivererViewContoller(FrmViewDeliverers frmViewDeliverers) {
        this.frmViewDeliverers=frmViewDeliverers;
        addActionListener();
    }

    private void addActionListener() {
       frmViewDeliverers.getBtnDetailsAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = frmViewDeliverers.getTblDeliverers().getSelectedRow();
                if (row >= 0) {
                    Deliverer deliverer = ((DelivererTableModel)frmViewDeliverers.getTblDeliverers().getModel()).getDelivererAt(row);
                    MainCordinator.getInstance().addParam(Constants.PARAM_DELIVERER, deliverer);
                    MainCordinator.getInstance().openDelivererDetailsDelivererForm();
                } else {
                    JOptionPane.showMessageDialog(frmViewDeliverers, "You must select one deliverer", "DELIVERER DETAILS", JOptionPane.ERROR_MESSAGE);
                }
            }
        });     
        frmViewDeliverers.addWindowListener(new WindowAdapter() {
           @Override
           public void windowActivated(WindowEvent e) {
              fillTblDeliverers();
           }
      });
    }
    
    public void openForm() {
        frmViewDeliverers.setLocationRelativeTo(MainCordinator.getInstance().getMainContoller().getFrmMain());
        prepareView();
        frmViewDeliverers.setVisible(true);
    }

     private void prepareView() {
        frmViewDeliverers.setTitle("View deliverers");
        fillTblDeliverers();
    }
     
     private void fillTblDeliverers() {
        List<Deliverer> deliverers;
        try {
            deliverers = Communication.getInstance().getAllDeliverers();
            DelivererTableModel dtm = new DelivererTableModel(deliverers);
            frmViewDeliverers.getTblDeliverers().setModel(dtm);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frmViewDeliverers, "Error: " + ex.getMessage(), "ERROR DETAILS", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(DelivererViewContoller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
     public void refresh() {
        fillTblDeliverers();
    }
    
}
