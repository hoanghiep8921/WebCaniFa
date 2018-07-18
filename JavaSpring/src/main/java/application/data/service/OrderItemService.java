package application.data.service;

import application.data.model.OrderItem;
import application.data.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class OrderItemService {
    @Autowired
    private OrderItemRepository orderItemRepository;

    public boolean deleteListOrderItem(int orderId){
       orderItemRepository.deleteListOrderItem(orderId);
         return true;
    }

    public int getIdOrderItem(int productId,int orderId){
        return orderItemRepository.getIdOrderItem(productId,orderId);
    }
    public int checkIdOrderItem(int productId,int orderId){
        return orderItemRepository.checkIdOrderItem(productId,orderId);
    }
    public OrderItem getOne(int orderItemId){
        return orderItemRepository.getOne(orderItemId);
    }
    public void addNewOrderItem(OrderItem orderItem) {
        orderItemRepository.save(orderItem);
    }
    public void deleteProductInOrder(int productId,int orderId){
        orderItemRepository.deleteProductInOrder(productId,orderId);
    }
    public boolean updateOrderItem(OrderItem orderItem) {
        try {
            orderItemRepository.save(orderItem);
            return true;
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
        return false;
    }

    public boolean deleteOrderItem(int orderItemId) {
        try {
            orderItemRepository.delete(orderItemId);
            return true;
        } catch (Exception e) {
            System.out.println((e.getMessage()));
        }
        return false;
    }
}
