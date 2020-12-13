/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.contoller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import rs.ac.bg.fon.ps.communication.Communication;
import rs.ac.bg.fon.ps.domain.City;
import rs.ac.bg.fon.ps.domain.Deliverer;
import rs.ac.bg.fon.ps.domain.DelivererStatus;
import rs.ac.bg.fon.ps.domain.Delivery;
import rs.ac.bg.fon.ps.domain.DeliveryStatus;
import rs.ac.bg.fon.ps.domain.Operator;
import rs.ac.bg.fon.ps.domain.Product;
import rs.ac.bg.fon.ps.domain.Restaurant;
import rs.ac.bg.fon.ps.view.constant.Constants;
import rs.ac.bg.fon.ps.view.cordinator.MainCordinator;
import rs.ac.bg.fon.ps.view.form.FrmDeliverer;
import rs.ac.bg.fon.ps.view.form.FrmDelivery;
import rs.ac.bg.fon.ps.view.form.components.table.DeliveryTableModel;
import rs.ac.bg.fon.ps.view.form.util.FormMode;

/**
 *
 * @author Tamara
 */
public class DeliveryController {
    private final FrmDelivery frmDelivery;

    public DeliveryController(FrmDelivery frmDelivery) {
        this.frmDelivery = frmDelivery;
    }

    public void openForm() {
        frmDelivery.setLocationRelativeTo(MainCordinator.getInstance().getMainContoller().getFrmMain());
        prepareView();
        frmDelivery.setVisible(true);
    }

    private void prepareView() {
        clearCb();
        fillCbDeliveryStatus();
        fillCbCity();
        fillDefaultValues();
        fillTblDelivery();
        addActionListeners();
    }

    private void clearCb() {
        frmDelivery.getCbRestaurant().setSelectedIndex(-1);
        frmDelivery.getCbProduct().setSelectedIndex(-1);
        frmDelivery.getCbDeliverer().setSelectedIndex(-1);
        frmDelivery.getCbDeliveryCity().removeAllItems();
        frmDelivery.getCbRestaurant().removeAllItems();
        frmDelivery.getCbProduct().removeAllItems();
    }
    
    private void fillCbDeliveryStatus() {
        frmDelivery.getCbDeliveryStatus().removeAllItems();
        for(DeliveryStatus status:DeliveryStatus.values()){
            frmDelivery.getCbDeliveryStatus().addItem(status);
        }

    }
    
    private void fillCbCity()  {
        try {
            List<City> cities = Communication.getInstance().getAllCities();
            frmDelivery.getCbDeliveryCity().setModel(new DefaultComboBoxModel<>(cities.toArray()));
            frmDelivery.getCbDeliveryCity().setSelectedIndex(-1);
            frmDelivery.getCbDeliveryCity().addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    if(e.getStateChange()==ItemEvent.SELECTED){
                        fillCbRestaurant();
                        fillCbDeliverer();
                    }
                }
            });
        } catch (Exception ex) {
            Logger.getLogger(DeliveryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void fillCbRestaurant(){
        try {
            City city=(City) frmDelivery.getCbDeliveryCity().getSelectedItem();
            List<Restaurant> restaurants=Communication.getInstance().getAllRestaurantsFromCity(city);
            frmDelivery.getCbRestaurant().setModel(new DefaultComboBoxModel<>(restaurants.toArray()));
            frmDelivery.getCbRestaurant().setSelectedIndex(-1);
            frmDelivery.getCbRestaurant().addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    if(e.getStateChange()==ItemEvent.SELECTED){
                        fillCbProduct();
                        
                    }
                }
            });
        } catch (Exception ex) {
            Logger.getLogger(DeliveryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    private void fillCbProduct() {
        try {
            Restaurant restaurant=(Restaurant) frmDelivery.getCbRestaurant().getSelectedItem();
            List<Product> products = Communication.getInstance().getAllProductsFromRestaurant(restaurant);
            frmDelivery.getCbProduct().setModel(new DefaultComboBoxModel<>(products.toArray()));
            frmDelivery.getCbProduct().setSelectedIndex(-1);
            frmDelivery.getCbProduct().addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    if(e.getStateChange()==ItemEvent.SELECTED){
                        Product product=(Product) e.getItem();
                        frmDelivery.getTxtProductPrice().setText(String.valueOf(product.getProductPrice()));
                        frmDelivery.getTxtProductQuantity().setText("1");
                        frmDelivery.getTxtProductQuantity().grabFocus();   
                        frmDelivery.getTxtProductQuantity().setSelectionStart(0);
                    }
                }
            });
        } catch (Exception ex) {
            Logger.getLogger(DeliveryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void fillCbDeliverer() {
        try {
            City city=(City) frmDelivery.getCbDeliveryCity().getSelectedItem();
            frmDelivery.getCbDeliverer().removeAllItems();
            List<Deliverer> deliverers = Communication.getInstance().getAllFreeDeliverersFromCity(city);
            frmDelivery.getCbDeliverer().setModel(new DefaultComboBoxModel<>(deliverers.toArray()));
            frmDelivery.getCbDeliverer().setSelectedIndex(-1);
        } catch (Exception ex) {
            Logger.getLogger(DeliveryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
     private void fillDefaultValues() {
        String currentDate = new SimpleDateFormat("dd.MM.yyyy.").format(new Date());
        frmDelivery.getTxtDeliveryDate().setText(currentDate);

        frmDelivery.getTxtDeliveryCost().setText("0.0");
        frmDelivery.getTxtTotalAmount().setText("0.0");
        frmDelivery.getTxtOverallTotal().setText("0.0");
        
        Operator operator = (Operator) MainCordinator.getInstance().getParam(Constants.PARAM_CURRENT_OPERATOR);
        frmDelivery.getTxtOperator().setText(operator.getOperatorName()+" " + operator.getOperatorLastname());
        
       frmDelivery.getCbDeliveryStatus().setSelectedItem(DeliveryStatus.IN_PROGRESS);
    }

    
    private void fillTblDelivery() {
        DeliveryTableModel model = new DeliveryTableModel(new Delivery());
        frmDelivery.getTblDelivery().setModel(model);
    }
    
    
    private void addActionListeners() {
        frmDelivery.addAddBtnActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
               addProduct();
            }

            private void addProduct() {
                 try{
                Product product=(Product) frmDelivery.getCbProduct().getSelectedItem();
                BigDecimal price=new BigDecimal(frmDelivery.getTxtProductPrice().getText().trim());
                BigDecimal quantity=new BigDecimal(frmDelivery.getTxtProductQuantity().getText().trim());
                DeliveryTableModel dtm=(DeliveryTableModel) frmDelivery.getTblDelivery().getModel();
                dtm.addDeliveryItem(product,price,quantity);
                BigDecimal totalAmount=dtm.getDelivery().getItemsAmount();
                frmDelivery.getTxtTotalAmount().setText(String.valueOf(totalAmount));
                
                }catch(Exception ex){
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frmDelivery, "Invalid product data!"+ ex.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
                }
            }
            
        });
        
        frmDelivery.addRemoveBtnActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeDeliveryItem();
            }

            private void removeDeliveryItem() {
                int rowIndex = frmDelivery.getTblDelivery().getSelectedRow();
                DeliveryTableModel dtm = (DeliveryTableModel) frmDelivery.getTblDelivery().getModel();
                if (rowIndex >= 0) {
                    dtm.removeInvoiceItem(rowIndex);
                    BigDecimal totalAmount = dtm.getDelivery().getItemsAmount();
                    frmDelivery.getTxtTotalAmount().setText(String.valueOf(totalAmount));
                } else {
                    JOptionPane.showMessageDialog(frmDelivery, "Delivery item is not selected!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

        });
        
        frmDelivery.addSaveBtnActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveDelivery();
            }

            private void saveDelivery() {
                try {
                    DateFormat df = new SimpleDateFormat("dd.MM.yyyy.");
                    DeliveryTableModel model = (DeliveryTableModel) frmDelivery.getTblDelivery().getModel();
                    Delivery delivery = model.getDelivery();
                 //   delivery.setNumber(frmDelivery.getTxtInvoiceNumber().getText().trim()); //TODO 
                    delivery.setDateOfCreation(df.parse(frmDelivery.getTxtDeliveryDate().getText().trim()));
                    Operator operator=(Operator) MainCordinator.getInstance().getParam(Constants.PARAM_CURRENT_OPERATOR);
                    delivery.setOperator(operator);
                    Deliverer deliverer=(Deliverer) frmDelivery.getCbDeliverer().getSelectedItem();
                    delivery.setDeliverer(deliverer);
                    delivery.setConsumerAddress(frmDelivery.getTxtConsumerAddress().getText());
                    delivery.setConsumerPhone(frmDelivery.getTxtConsumerContact().getText());
                    delivery.setDeliveryStatus((DeliveryStatus) frmDelivery.getCbDeliveryStatus().getSelectedItem());
                    Communication.getInstance().saveDelivery(delivery);
                    //Controler.getInstance().setDelivererStatus();
                    frmDelivery.getTxtDeliveryID().setText(String.valueOf(delivery.getDeliveryID()));
                    JOptionPane.showMessageDialog(frmDelivery, "Delivery is saved!");
                    frmDelivery.dispose();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frmDelivery, "Delivery is not saved! " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
    
  

    
}
