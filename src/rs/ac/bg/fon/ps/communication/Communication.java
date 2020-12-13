/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.communication;

import java.net.Socket;
import java.util.List;
import rs.ac.bg.fon.ps.domain.City;
import rs.ac.bg.fon.ps.domain.Deliverer;
import rs.ac.bg.fon.ps.domain.Delivery;
import rs.ac.bg.fon.ps.domain.Operator;
import rs.ac.bg.fon.ps.domain.Product;
import rs.ac.bg.fon.ps.domain.Restaurant;

/**
 *
 * @author Tamara
 */
public class Communication {
    Socket socket;
    Sender sender;
    Receiver receiver;
    private static Communication instance;
    
    
    private Communication() throws Exception{
        socket=new Socket("127.0.0.1", 9000);
        sender=new Sender(socket);
        receiver=new Receiver(socket);
    }
    
    public static Communication getInstance() throws Exception{
        if(instance==null){
            instance=new Communication();
        }
        return instance;
    }
    
    public Operator login(String username, String password) throws Exception{
        Operator operator=new Operator();
        operator.setUsername(username);
        operator.setPassword(password);
        Request request=new Request(Operation.LOGIN, operator);
        sender.send(request);
        Response response=(Response)receiver.receive();
        if(response.getException()==null){
            return (Operator)response.getResult();
        }else{
            throw response.getException();
        }
    }
    
    
    public List<City> getAllCities()throws Exception{
        Request request=new Request(Operation.GET_ALL_CITIES, null);
        sender.send(request);
        Response response=(Response)receiver.receive();
        if(response.getException()==null){
            return (List<City>)response.getResult();
        }else{
            throw response.getException();
        }
    }
    
    public void addDeliverer(Deliverer deliverer) throws Exception{
        Request request=new Request(Operation.ADD_DELIVERER, deliverer);
        sender.send(request);
        Response response=(Response)receiver.receive();
        if(response.getException()==null){
            Deliverer newDeliverer = (Deliverer) response.getResult();
            deliverer.setDelivererID(newDeliverer.getDelivererID());
        }else{
            throw response.getException();
        }
    }
    
    public List<Deliverer> getAllDeliverers() throws Exception{
        Request request=new Request(Operation.GET_ALL_DELIVERERS, null);
        sender.send(request);
        Response response=(Response)receiver.receive();
        if(response.getException()==null){
            return (List<Deliverer>)response.getResult();
        }else{
            throw response.getException();
        }
    }
    
    public List<Deliverer> getAllFreeDeliverersFromCity(City city) throws Exception{
        Request request=new Request(Operation.GET_ALL_FREE_DELIVERERS_FROM_CITY, city);
        sender.send(request);
        Response response=(Response)receiver.receive();
        if(response.getException()==null){
            return (List<Deliverer>)response.getResult();
        }else{
            throw response.getException();
        }
    }

    public void deleteDeliverer(Deliverer deliverer) throws Exception {
        Request request=new Request(Operation.DELETE_DELIVERER, deliverer);
        sender.send(request);
        Response response=(Response)receiver.receive();
        if(response.getException()==null){
            
        }else{
            throw response.getException();
        }
    }
    
     public void editDeliverer(Deliverer deliverer) throws Exception {
        Request request=new Request(Operation.EDIT_DELIVERER, deliverer);
        sender.send(request);
        Response response=(Response)receiver.receive();
        if(response.getException()==null){
            
        }else{
            throw response.getException();
        }
    }

    public List<Restaurant> getAllRestaurantsFromCity(City city) throws Exception {
        Request request=new Request(Operation.GET_ALL_RESTAURANTS_FROM_CITY, city);
        sender.send(request);
        Response response=(Response)receiver.receive();
        if(response.getException()==null){
            return (List<Restaurant>)response.getResult();
        }else{
            throw response.getException();
        }
    }

    public List<Restaurant> getAllRestaurants() throws Exception {
        Request request=new Request(Operation.GET_ALL_RESTAURANTS, null);
        sender.send(request);
        Response response=(Response)receiver.receive();
        if(response.getException()==null){
            return (List<Restaurant>)response.getResult();
        }else{
            throw response.getException();
        }
    }

    public List<Product> getAllProductsFromRestaurant(Restaurant restaurant) throws Exception {
        Request request=new Request(Operation.GET_ALL_PRODUCTS_FROM_RESTAURANTS, restaurant);
        sender.send(request);
        Response response=(Response)receiver.receive();
        if(response.getException()==null){
            return (List<Product>)response.getResult();
        }else{
            throw response.getException();
        }
    }   

    public void saveDelivery(Delivery delivery) throws Exception {
        Request request = new Request(Operation.ADD_DELIVERY, delivery);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            Delivery newDelivery = (Delivery) response.getResult();
            delivery.setDeliveryID(newDelivery.getDeliveryID());
        } else {
            throw response.getException();
        }
    }
}
    
