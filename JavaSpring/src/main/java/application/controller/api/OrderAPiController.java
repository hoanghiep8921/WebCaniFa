package application.controller.api;

import application.data.model.Order;
import application.data.model.OrderItem;
import application.data.model.Product;
import application.data.model.ProductHot;
import application.data.service.*;
import application.model.*;
import application.model.Order.*;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/order")
public class    OrderAPiController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private SaleService saleService;

    @Autowired
    private ProductService productService;

    @GetMapping("/count")
    public long countOrder(){
        return orderService.getTotalOrder();
    }

    @GetMapping("/countproduct")
    public BaseApiResult countProductInOrder(@RequestParam(value="status", required=true) int status,@RequestParam(value="uid", required=true) int uid){
        DataApiResult result = new DataApiResult();
        result.setData(orderService.countProductInOrder(status,uid));
        result.setMessage("200");
        result.setSuccess(true);
        return result;
    }

    @GetMapping("/productHot")
    public ArrayList<ProductHot> getListProductHot() throws SQLException {
        ArrayList<ProductHot> productHotList = new ArrayList<>();
        productHotList = orderService.listProductBuyHot();
        return productHotList;
    }

    @GetMapping("/detailOrder/{orderId}")
    public BaseApiResult detaiOrderId(@PathVariable int orderId) {
        DataApiResult result = new DataApiResult();
        result.setData(saleService.findOne(orderService.getOne(orderId).getSaleId()));
        result.setSuccess(true);
        result.setMessage("200");
        return result;
    }

    @GetMapping("/detailHistoryOrder/{userId}")
    public BaseApiResult historyOrder(@PathVariable int userId) {
        DataApiResult result = new DataApiResult();
        ArrayList<OrderHistory> template = orderService.historyOrder(userId);
        ArrayList<OrderConver> listOrder = new ArrayList<>();
        for(OrderHistory o : template){
            OrderConver orderConver = new OrderConver(o.getOrderId(),o.getUserId(),o.getStatusId(),o.getDescription(),o.getCreated().toString(),o.getAddress(),o.getDetail(),o.getSaleNumber());
            listOrder.add(orderConver);
        }
        result.setData(listOrder);
        result.setSuccess(true);
        result.setMessage("Danh sách order by userId");
        return result;
    }

    @GetMapping("/detail")
    public BaseApiResult detaiOrder(@RequestParam(value="statusId", required=true) int statusId,@RequestParam(value="userId", required=true) int userId) {
        DataApiResult result = new DataApiResult();
        result.setData(orderService.detailProductInOrder(statusId,userId));
        result.setSuccess(true);
        result.setMessage("200");
        return result;
    }
    @GetMapping("/listProductOrder")
    public BaseApiResult listProductInOrder(@RequestParam(value="orderId", required=true) int orderId,@RequestParam(value="userId", required=true) int userId,@RequestParam(value="statusId", required=true) int statusId) {
        DataApiResult result = new DataApiResult();
        result.setData(orderService.listProductInOrder(orderId,userId,statusId));
        result.setSuccess(true);
        result.setMessage("200");
        return result;
    }
    @GetMapping("/listOrderTime/{year}")
    public BaseApiResult listProductInOrder(@PathVariable Integer year) {
        DataApiResult result = new DataApiResult();
        ArrayList<OrderByTime> list = orderService.listOrderByTime(year.toString());

        ArrayList<OrderByDateTime> data = new ArrayList<>();
        for(OrderByTime o : list){
            OrderByDateTime dateTime= new OrderByDateTime(o.getId(),o.getName(),o.getSoluong(),o.getCreate().toString(),o.getAddress(),o.getDetail());
            data.add(dateTime);
        }
        result.setData(data);
        result.setSuccess(true);
        result.setMessage("200");
        return result;
    }

    @PostMapping("/create-order")
    public BaseApiResult createOrder(@RequestBody OrderDataModel order) {
        DataApiResult result = new DataApiResult();

        try {
            if(!"".equals(order.getAddress()) && !"".equals(order.getUser())) {
                ModelMapper modelMapper = new ModelMapper();
                Order orderEntity = modelMapper.map(order, Order.class);
                orderEntity.setStatus(4);
                orderEntity.setCreatedDate(new Date());
                orderService.addNewOrder(orderEntity);
                result.setSuccess(true);
                result.setMessage("Save order successfully: " + orderEntity.getId());
                result.setData(orderEntity.getId());
            } else {
                result.setSuccess(false);
                result.setMessage("Invalid model");
            }
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }

        return result;
    }

    @GetMapping("/addProduct/{orderId}")
    public BaseApiResult addProductInOrder(@PathVariable int orderId,@RequestParam(value="productId", required=true) int productId,@RequestParam(value="quantity", required=true) int quantity){
        DataApiResult result= new DataApiResult();
        OrderItem orderItem = new OrderItem();
        int idExits=orderItemService.checkIdOrderItem(productId,orderId);
        if(idExits>0){
            OrderItem exitsOrderItem = orderItemService.getOne(orderItemService.getIdOrderItem(productId,orderId));
            exitsOrderItem.setQuantity(exitsOrderItem.getQuantity()+ quantity);
            Product productExits =  productService.findOne(productId);
            productExits.setQuantity(productExits.getQuantity()-quantity);
            if(productService.updateProduct(productExits)) {
                orderItemService.addNewOrderItem(exitsOrderItem);
                result.setSuccess(true);
                result.setMessage("Add quantity Success");
                result.setData(1);
            }else{
                result.setSuccess(false);
                result.setMessage("Add quantity false");
                result.setData(0);
            }
        }else {
            orderItem.setOrderId(orderId);
            orderItem.setProductId(productId);
            orderItem.setQuantity(quantity);
            orderItemService.addNewOrderItem(orderItem);
            Product productExits =  productService.findOne(productId);
            productExits.setQuantity(productExits.getQuantity()-quantity);
            if(productService.updateProduct(productExits)) {
                result.setSuccess(true);
                result.setMessage("Add product Success");
                result.setData(1);
            }else{
                result.setSuccess(false);
                result.setMessage("Add product false");
                result.setData(0);
            }
        }

            return result;
    }
    @GetMapping("/minusProduct/{orderId}")
    public BaseApiResult minusProductInOrder(@PathVariable int orderId,@RequestParam(value="productId", required=true) int productId,@RequestParam(value="quantity", required=true) int quantity){
        DataApiResult result= new DataApiResult();
        OrderItem orderItem = new OrderItem();
        int idExits=orderItemService.checkIdOrderItem(productId,orderId);
        if(idExits>0) {
            OrderItem exitsOrderItem = orderItemService.getOne(orderItemService.getIdOrderItem(productId, orderId));
            exitsOrderItem.setQuantity(exitsOrderItem.getQuantity() - quantity);
            Product productExits =  productService.findOne(productId);
            productExits.setQuantity(productExits.getQuantity()+quantity);
            if(productService.updateProduct(productExits)){
                orderItemService.addNewOrderItem(exitsOrderItem);
                result.setSuccess(true);
                result.setMessage("Minus quantity Success");
                result.setData(1);
            }else{

            }
        }else{
            result.setSuccess(false);
            result.setMessage("Minus quantity false");
            result.setData(0);
        }
        return result;
    }

    @GetMapping("/deleteProduct")
    public BaseApiResult deleteProductInOrder(@RequestParam(value="productId", required=true) int productId,@RequestParam(value="orderId", required=true) int orderId){
        DataApiResult result = new DataApiResult();
        OrderItem exitsOrderItem = orderItemService.getOne(orderItemService.getIdOrderItem(productId, orderId));
        Product productExits =  productService.findOne(productId);
        productExits.setQuantity(productExits.getQuantity()+exitsOrderItem.getQuantity());
        if(productService.updateProduct(productExits)) {
            orderItemService.deleteProductInOrder(productId, orderId);
            result.setData(1);
            result.setSuccess(true);
            result.setMessage("200");

        }else{
            result.setData(0);
            result.setSuccess(false);
            result.setMessage("Fail");
        }
        return result;
    }

    @GetMapping("/update-status-order")
    public BaseApiResult updateOrder(@RequestParam(value="statusN", required=true) int statusN,@RequestParam(value="statusO", required=true) int statusO) {
        BaseApiResult result = new BaseApiResult();
        orderService.updateStatusOrderAll(statusN,statusO);
        result.setMessage("success");
        result.setSuccess(true);
        return  result;
    }
    @PostMapping("/update-order/{orderId}")
    public BaseApiResult updateOrder(@PathVariable int orderId, @RequestBody OrderDataModel order) {
        BaseApiResult result = new BaseApiResult();

        try {
                Order existOrder = orderService.getOne(orderId);
                if(existOrder == null) {
                    result.setSuccess(false);
                    result.setMessage("Invalid model");
                } else {
                    if(order.getUser()>0) {
                        existOrder.setUser(order.getUser());
                    }
                    System.out.println(order.getAddress());
                    if(order.getAddress()!=null){
                        existOrder.setAddress(order.getAddress());
                    }
                    System.out.println(order.getDetail());
                    if(order.getDetail()!=null) {
                        existOrder.setDetail(order.getDetail());
                    }
                    if(order.getStatus()>0) {
                        if(existOrder.getStatus()==4){

                            Order orderDataModel = new Order();
                            orderDataModel.setUser(existOrder.getUser());
                            orderDataModel.setStatus(4);
                            orderDataModel.setCreatedDate(new Date());
                            orderService.addNewOrder(orderDataModel);
                            existOrder.setCreatedDate(new Date());
                        }
                        existOrder.setStatus(order.getStatus());
                    }
                    if(order.getSaleId()>0) {
                        existOrder.setSaleId(order.getSaleId());
                    }
                    orderService.updateOrder(existOrder);
                    result.setSuccess(true);
                    result.setMessage("Update order successfully");
                }
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }

        return result;
    }

    @PostMapping("/delete-order")
    public BaseApiResult deleteOrder(@RequestBody OrderDeleteDataModel order) {
        BaseApiResult result = new BaseApiResult();

        try {
            orderItemService.deleteListOrderItem(order.getOrderId());
            if(orderService.deleteOrder(order.getOrderId())) {
                result.setSuccess(true);
                result.setMessage("Delete order successfully");
            }
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }
}
