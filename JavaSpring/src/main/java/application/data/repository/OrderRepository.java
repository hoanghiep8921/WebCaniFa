package application.data.repository;

import application.data.model.Order;
import application.data.model.Product;
import application.data.model.ProductHot;

import application.data.model.ProductInOrder;
import application.model.Order.OrderByTime;
import application.model.Order.OrderDetailModel;
import application.model.Order.OrderHistory;
import application.model.Product.ProductDataModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.ArrayList;


public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Query(value = " select o.order_id from order_detail o where o.user_id=?1 and o.status_id=4 ",nativeQuery = true)
    int getOrderIdByUserId(int userId);

    @Query("select count(o.id) from order_detail o")
    long getTotalOrder();

    @Query(value = "select sum((l.price * l.quantity) - (l.price * l.quantity * l.sale_number / 100)) as tongtien from ( select oi.product_id,p.price,oi.quantity,s.sale_number from product p inner join order_item oi on(p.product_id = oi.product_id ) inner join order_detail o on ( oi.order_id = o.order_id ) inner join sale s on ( s.id = o.sale_id ) where month(o.created_date) = ?1 and year(o.created_date) = ?2 group by oi.product_id) l",nativeQuery =true)
    int getTotalPriceOnMonth ( int month,int year);



    @Query(value = "select count(oi.product_id) FROM order_item oi inner join order_detail o on(oi.order_id=o.order_id) where o.status_id=?1 and o.user_id=?2",nativeQuery =true)
    int countProductInOrder (int status,int userId);

    @Query(value = " select "
            + " new application.data.model.ProductInOrder( oi.productId , oi.quantity ,p.price , p.name , p.image , o.detail ) "
            + " from order_detail o,order_item oi, product p"
            + " where oi.orderId = o.id "
            + " and oi.productId = p.id "
            + " and o.status = ?1 "
            + " and o.user = ?2 ")
    ArrayList < ProductInOrder > detailProductInOrder(int statusId, int userId );

    @Query(value = " select "
            + " new application.data.model.ProductHot(p.id, p.name, sum( oi.quantity ) ) "
            + " from product p ,order_item oi ,order_detail o "
            + " where oi.productId = p.id "
            + " and oi.orderId = o.id "
            + " group by p.id  "
            + " order by sum(oi.quantity) desc")
    ArrayList<ProductHot> listProductBuyHot();

    @Query(value = " select "
            + " new application.model.Order.OrderByTime(o.id, o.user, sum( oi.quantity ),o.createdDate,o.address,o.detail ) "
            + " from order_item oi ,order_detail o "
            + " where oi.orderId = o.id "
            + " and SUBSTRING(o.createdDate, 1, 4)=?1 "
            + " group by o.id  "
            + " order by sum(oi.quantity) desc")
    ArrayList<OrderByTime> listOrderByTime(String year);

    @Query("select new application.model.Order.OrderDetailModel(o.id, o.user,o.address,o.detail) from order_detail o where o.status =?1")
    ArrayList<OrderDetailModel> listOrderStatus (int status);

    @Query("select count(o.id) from order_detail o where o.status=?1")
    int countOrderStatus(int status);

    @Query("select new application.data.model.ProductInOrder(p.id,oi.quantity,p.price,p.name,p.image,o.detail) from order_detail o,order_item oi,product p where o.id = ?1 and o.user=?2 and o.id=oi.orderId and p.id = oi.productId and o.status = ?3 ")
    ArrayList<ProductInOrder> listProductInOrder(int orderId,int userId,int status);

    @Query("select new application.model.Order.OrderHistory(od.id,od.user,od.status,os.description,od.createdDate,od.address,od.detail,s.saleNumber) from order_detail od,order_status os,sale s where od.status = os.id and od.saleId = s.id and od.user=?1 and (od.status = 3 or  od.status = 2 or od.status = 1)")
    ArrayList<OrderHistory> historyOrder(int userId);

    @Modifying
    @Query(value = " update order_detail o set o.status=?1 where o.status = ?2  ")
    @Transactional
    void updateStatusOrderAll(int statusN,int statusO);
}
