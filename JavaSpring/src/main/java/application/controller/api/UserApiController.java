package application.controller.api;

import application.data.model.Order;
import application.data.model.User;
import application.data.service.OrderService;
import application.data.service.OrderStatusService;
import application.data.service.UserServiceImpl;
import application.model.BaseApiResult;
import application.model.DataApiResult;
import application.model.Product.LoginModel;
import application.model.User.UserModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@CrossOrigin
@RestController
@RequestMapping("/api/user")
public class UserApiController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderStatusService orderStatusService;

    @GetMapping("/login")
    public BaseApiResult userLogin(@RequestParam(value="email", required=true) String email){
        DataApiResult result = new DataApiResult();
        User exitsUser= userService.findUserByEmail(email);

        if(exitsUser!=null){
            result.setSuccess(true);
            LoginModel loginModel= new LoginModel();
            loginModel.setUserId(exitsUser.getId());
            loginModel.setOrderId( orderService.getOrderIdByUserId(exitsUser.getId()));
            result.setData(loginModel);
            result.setMessage("Đăng nhập thành công");
        }
        else {
            result.setMessage("Đăng nhập thất bại");
            result.setData(0);
            result.setSuccess(false);
        }
            return result;
    }
    @GetMapping("/getUserAll")
    public BaseApiResult getAll(){
            DataApiResult result = new DataApiResult();
            result.setData(userService.getAll());
            result.setMessage("200");
       result.setSuccess(true);
        return result;
    }

    @GetMapping("/getRole")
    public BaseApiResult getRole(){
        DataApiResult result = new DataApiResult();
        result.setData(userService.getRole());
        result.setMessage("200");
        result.setSuccess(true);
        return result;
    }

    @GetMapping("/detail")
    public BaseApiResult userDetail(@RequestParam(value="id", required=true) int id){
        DataApiResult result = new DataApiResult();
        User exitsUser= userService.findUserById(id);
        UserModel userModel = new UserModel();
        userModel.setAddress(exitsUser.getAddress());
        userModel.setEmail(exitsUser.getEmail());
        userModel.setName(exitsUser.getName());
        userModel.setPassword(exitsUser.getPassword());
        if(exitsUser!=null){
            result.setSuccess(true);
            result.setData(userModel);
            result.setMessage("200");
        }
        else {
            result.setMessage("201");
            result.setData(0);
            result.setSuccess(false);
        }
        return result;
    }

    @PostMapping("/register")
    public BaseApiResult userRegister(@RequestBody UserModel user){
        DataApiResult result = new DataApiResult();
        User userExists = userService.findUserByEmail(user.getEmail());
        if(userExists!=null){
            result.setMessage("Đã tồn tài tài khoản này");
            result.setData(0);
            result.setSuccess(false);
        }else
        {
            ModelMapper modelMapper = new ModelMapper();
            User userEntity = modelMapper.map(user, User.class);
            userEntity.setAddress(user.getAddress());
            userEntity.setCreatedDate(new Date());
            userEntity.setUpdateDate(new Date());
            userEntity.setEmail(user.getEmail());
            userEntity.setName(user.getName());
            userEntity.setPassword(user.getPassword());
            userService.saveUserClient(userEntity);

            Order orderDataModel = new Order();
            orderDataModel.setUser(userEntity.getId());
            orderDataModel.setStatus(4);
            orderDataModel.setSaleId(4);
            orderDataModel.setCreatedDate(new Date());


            orderService.addNewOrder(orderDataModel);
            result.setSuccess(true);
            result.setMessage("Đăng ký thành công");
            result.setData(userEntity.getId());
        }

        return result;
    }
}
