package application.data.service;

import application.data.model.Order;
import application.data.model.ProductHot;
import application.data.repository.OrderRepository;
import application.data.model.ProductInOrder;
import application.model.Order.OrderByTime;
import application.model.Order.OrderDetailModel;
import application.model.Order.OrderHistory;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    private static final Logger logger = LogManager.getLogger(OrderService.class);
    @Autowired
    private OrderRepository orderRepository;

    public  ArrayList<ProductInOrder> listProductInOrder(int orderId,int userId,int status){
        return orderRepository.listProductInOrder(orderId,userId,status);
    }

    public void updateStatusOrderAll(int statusN,int statusO){
        orderRepository.updateStatusOrderAll(statusN,statusO);
    }

    public int getOrderIdByUserId(int userId){
        return orderRepository.getOrderIdByUserId(userId);
    }

    public ArrayList<OrderDetailModel> listOrderStatus(int status){
        return orderRepository.listOrderStatus(status);
    }

    public ArrayList<OrderHistory> historyOrder(int userId){
        return orderRepository.historyOrder(userId);
    }

    public int countOrderStatus(int status){
        return orderRepository.countOrderStatus(status);
    }
    public long getTotalOrder() {
        return orderRepository.getTotalOrder();
    }

    public int getTotalPriceOnMonth(int month,int year){
        return orderRepository.getTotalPriceOnMonth(month,year);
    }

    public int countProductInOrder(int status,int userId){
        return orderRepository.countProductInOrder(status,userId);
    }
    public ArrayList<ProductHot> listProductBuyHot(){
        return orderRepository.listProductBuyHot();
    }

    public ArrayList<ProductInOrder>  detailProductInOrder(int statusId,int userId){
        return orderRepository.detailProductInOrder(statusId,userId);
    }

    public List<Order> getListAllOrder(){
        try{
            return orderRepository.findAll();
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            return new ArrayList<>();
        }
    }

    public Order getOne(int orderId){
        return orderRepository.getOne(orderId);
    }
    public void addNewOrder(Order order) {
        orderRepository.save(order);
    }

    public ArrayList<OrderByTime> listOrderByTime(String year){
        return orderRepository.listOrderByTime(year);
    }
    @Transactional
    public void addNewListOrder(List<Order> listOrders) {
        orderRepository.save(listOrders);
    }


    public boolean updateOrder(Order order) {
        try {
            orderRepository.save(order);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return false;
    }

    public boolean deleteOrder(int orderId) {
        try {
            orderRepository.delete(orderId);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return false;
    }
}

