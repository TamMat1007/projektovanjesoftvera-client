/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.form.components.table;

import java.math.BigDecimal;
import javax.swing.table.AbstractTableModel;
import rs.ac.bg.fon.ps.domain.Delivery;
import rs.ac.bg.fon.ps.domain.DeliveryItem;
import rs.ac.bg.fon.ps.domain.Product;

/**
 *
 * @author Tamara
 */
public class DeliveryTableModel extends AbstractTableModel{
    private final Delivery delivery;
    private final String[] columnNames={"Order No.","Product","Product price", "Quantity","Total"};

    public DeliveryTableModel(Delivery delivery) {
        this.delivery = delivery;
    }
    

    @Override
    public int getRowCount() {
        if (delivery.getDeliveryItems()==null) return 0;
        return delivery.getDeliveryItems().size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        DeliveryItem deliveryItem = delivery.getDeliveryItems().get(rowIndex);
        switch(columnIndex){
            case 0: return deliveryItem.getItemOrderNumber();
            case 1: return deliveryItem.getProduct();
            case 2: return deliveryItem.getProductPrice();
            case 3: return deliveryItem.getQuantity();
            case 4: return deliveryItem.getItemTotal();
            default:
                return "n/a";
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public void addDeliveryItem(Product product, BigDecimal price, BigDecimal quantity) {
        DeliveryItem deliveryItem=new DeliveryItem();
        deliveryItem.setItemOrderNumber(delivery.getDeliveryItems().size()+1);
        deliveryItem.setProduct(product);
        deliveryItem.setProductPrice(price);
        deliveryItem.setQuantity(quantity);
        deliveryItem.setItemTotal(deliveryItem.getQuantity().multiply(deliveryItem.getProductPrice()));
        deliveryItem.setDelivery(delivery);
        
        delivery.getDeliveryItems().add(deliveryItem);
        delivery.setItemsAmount(delivery.getItemsAmount().add(deliveryItem.getQuantity().multiply(deliveryItem.getProductPrice())));
        fireTableRowsInserted(delivery.getDeliveryItems().size()-1, delivery.getDeliveryItems().size()-1);
      
    }
    
    public void removeInvoiceItem(int rowIndex) {
        DeliveryItem deliveryItem=delivery.getDeliveryItems().get(rowIndex);
        delivery.getDeliveryItems().remove(rowIndex);
        delivery.setItemsAmount(delivery.getItemsAmount().subtract(deliveryItem.getProductPrice().multiply(deliveryItem.getQuantity())));
        setOrderNumbers();
        fireTableRowsDeleted(delivery.getDeliveryItems().size()-1, delivery.getDeliveryItems().size()-1);
    }

    private void setOrderNumbers() {
        int orderNumber = 0;
        for (DeliveryItem item : delivery.getDeliveryItems()) {
            item.setItemOrderNumber(++orderNumber);
        }
    }
    
     public Delivery getDelivery(){
            return delivery;
     }

    
    
}
