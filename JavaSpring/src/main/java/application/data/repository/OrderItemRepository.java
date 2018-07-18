package application.data.repository;

import application.data.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.ArrayList;

public interface OrderItemRepository extends JpaRepository<OrderItem,Integer> {
    @Modifying
    @Query(value = " delete from order_item oi where oi.productId= ?1 and oi.orderId = ?2 ")
    @Transactional
    void deleteProductInOrder(int productId,int orderId);

    @Query(value = " SELECT EXISTS (select oi.id from order_item oi where oi.product_id = ?1 and oi.order_id = ?2 ) ",nativeQuery = true)
    int checkIdOrderItem(int productId,int orderId);

    @Query("select oi.id from order_item oi where  oi.productId =?1 and oi.orderId =?2")
    int getIdOrderItem(int productId,int orderId);

    @Modifying
    @Query(value = "delete from order_item oi where  oi.orderId = ?1 ")
    @Transactional
    void deleteListOrderItem(int orderId);
}
