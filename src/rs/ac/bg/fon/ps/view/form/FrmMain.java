/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.form;

import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Tamara
 */
public class FrmMain extends javax.swing.JFrame {
    /**
     * 
     * Creates new form FrmMain
     */
    public FrmMain() {
        super("Main form");
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblCurrentOperator = new javax.swing.JLabel();
        jmenuBarMain = new javax.swing.JMenuBar();
        jmenuRestaurant = new javax.swing.JMenu();
        jmiRestaurantsShowAll = new javax.swing.JMenuItem();
        jmenuDeliverer = new javax.swing.JMenu();
        jmiDelivererNew = new javax.swing.JMenuItem();
        jmiDelivererShowAll = new javax.swing.JMenuItem();
        jmenuDelivery = new javax.swing.JMenu();
        jmiDeliveryNew = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblCurrentOperator.setText("jLabel1");

        jmenuRestaurant.setText("Restaurants");

        jmiRestaurantsShowAll.setText("ShowAll");
        jmenuRestaurant.add(jmiRestaurantsShowAll);

        jmenuBarMain.add(jmenuRestaurant);

        jmenuDeliverer.setText("Deliverer");
        jmenuDeliverer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmenuDelivererActionPerformed(evt);
            }
        });

        jmiDelivererNew.setText("New");
        jmiDelivererNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiDelivererNewActionPerformed(evt);
            }
        });
        jmenuDeliverer.add(jmiDelivererNew);

        jmiDelivererShowAll.setText("Show All");
        jmiDelivererShowAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiDelivererShowAllActionPerformed(evt);
            }
        });
        jmenuDeliverer.add(jmiDelivererShowAll);

        jmenuBarMain.add(jmenuDeliverer);

        jmenuDelivery.setText("Delivery");

        jmiDeliveryNew.setText("New");
        jmenuDelivery.add(jmiDeliveryNew);

        jmenuBarMain.add(jmenuDelivery);

        setJMenuBar(jmenuBarMain);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(337, Short.MAX_VALUE)
                .addComponent(lblCurrentOperator)
                .addGap(22, 22, 22))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(245, Short.MAX_VALUE)
                .addComponent(lblCurrentOperator)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jmiDelivererShowAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiDelivererShowAllActionPerformed

    }//GEN-LAST:event_jmiDelivererShowAllActionPerformed

    private void jmenuDelivererActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmenuDelivererActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jmenuDelivererActionPerformed

    private void jmiDelivererNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiDelivererNewActionPerformed

    }//GEN-LAST:event_jmiDelivererNewActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar jmenuBarMain;
    private javax.swing.JMenu jmenuDeliverer;
    private javax.swing.JMenu jmenuDelivery;
    private javax.swing.JMenu jmenuRestaurant;
    private javax.swing.JMenuItem jmiDelivererNew;
    private javax.swing.JMenuItem jmiDelivererShowAll;
    private javax.swing.JMenuItem jmiDeliveryNew;
    private javax.swing.JMenuItem jmiRestaurantsShowAll;
    private javax.swing.JLabel lblCurrentOperator;
    // End of variables declaration//GEN-END:variables

    public JLabel getLblCurrentOperator() {
        return lblCurrentOperator;
    }

    public void setLblCurrentOperator(JLabel lblCurrentOperator) {
        this.lblCurrentOperator = lblCurrentOperator;
    }

    public void jmiDelivererNewAddActionListener(ActionListener actionListener) {
        jmiDelivererNew.addActionListener(actionListener);
    }

    public void jmiDelivererShowAllAddActionListener(ActionListener actionListener) {
        jmiDelivererShowAll.addActionListener(actionListener);
    }

    public void jmiDeliveryNewAddActionListener(ActionListener actionListener) {
        jmiDeliveryNew.addActionListener(actionListener);
    }

    public void jmiRestaurantsShowAllAddActionListeners(ActionListener actionListener) {
        jmiRestaurantsShowAll.addActionListener(actionListener);
    }
    
     
}

