package application.data.service;

import application.data.model.OrderStatus;
import application.data.repository.OrderStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderStatusService {
    @Autowired
    private OrderStatusRepository orderStatusRepository;

    public OrderStatus getOne(int orderId){
        return orderStatusRepository.getOne(orderId);
    }
}
