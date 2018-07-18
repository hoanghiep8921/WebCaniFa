package application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller

public class HomeController {
    @RequestMapping("/home")
    public String index( ){
        return "home_landing";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/createOrder")
    public String createOrder(){
        return "createOrder";
    }
    @RequestMapping("/product_detail")
    public String detailProduct(){
        return "product_detail";
    }

    @RequestMapping("/category")
    public String categoryProduct(){
        return "category";
    }
    @RequestMapping("/historyBuy")
    public String historyOrder(){
        return "historyBuy";
    }
}
