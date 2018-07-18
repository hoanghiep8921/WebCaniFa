package application.controller.web;

import application.constant.Constant;
import application.data.model.Category;
import application.data.model.PaginableItemList;
import application.data.model.Product;
import application.data.model.ProductStatus;
import application.data.service.CategoryService;
import application.data.service.OrderService;
import application.data.service.ProductService;
import application.data.service.ProductStatusService;
import application.model.Category.CategoryDataModel;
import application.model.Product.ProductDetailModel;
import application.viewmodel.admin.AdminVM;
import application.viewmodel.common.ProductVM;
import application.viewmodel.homelanding.BannerVM;
import application.viewmodel.homelanding.HomeLandingVM;
import application.viewmodel.homelanding.MenuItemVM;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path="/")
public class HomeController extends BaseController {

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductStatusService productStatusService;

    @Autowired
    private OrderService orderService;

    @RequestMapping(path = "admin", method = RequestMethod.GET)
    public String homeAdmin(Model model){

        model.addAttribute("countProduct",productService.getTotalProducts());
        model.addAttribute("countCategory",categoryService.getTotalCategory());
        model.addAttribute("countOrder",orderService.getTotalOrder());
        model.addAttribute("productHot",orderService.listProductBuyHot());
        List priceByMonth2017 = new ArrayList();
        for(int i=1;i<=12;++i){
            priceByMonth2017.add(orderService.getTotalPriceOnMonth(i,2017));
        }
        model.addAttribute("totalPrice2017",priceByMonth2017);
        List priceByMonth2018 = new ArrayList();
        for(int i=1;i<=12;++i){
            if(i<8) {
                priceByMonth2018.add(orderService.getTotalPriceOnMonth(i, 2018));
            }else{
                priceByMonth2018.add(0);

            }
        }
        model.addAttribute("totalPrice2018",priceByMonth2018);
        model.addAttribute("countOrderCreated",orderService.countOrderStatus(1));
        model.addAttribute("listCategory",categoryService.getListAllCategory());

        return "admin";
    }

    @RequestMapping(path = "admin/order", method = RequestMethod.GET)
    public String listOrder(Model model){
        model.addAttribute("listOrderCreated",orderService.listOrderStatus(1));
        model.addAttribute("listOrderShip",orderService.listOrderStatus(2));
        model.addAttribute("listOrderDone",orderService.listOrderStatus(3));
        return "admin_list_order";
    }

    @RequestMapping(path = "admin/notification", method = RequestMethod.GET)
    public String notification(Model model){
        model.addAttribute("listOrderCreated",orderService.listOrderStatus(1));
        return "notification";
    }

    @RequestMapping(path = "admin/thongke", method = RequestMethod.GET)
    public String thongke(Model model){
        model.addAttribute("listOrderCreated",orderService.listOrderStatus(1));
        return "thongke";
    }
    @RequestMapping(path = "admin/sale", method = RequestMethod.GET)
    public String sale(Model model){
        return "sale";
    }

    @RequestMapping(path = "admin/employee", method = RequestMethod.GET)
    public String employee(Model model){
        model.addAttribute("listOrderCreated",orderService.listOrderStatus(1));
        return "employee";
    }

    @RequestMapping(path = "admin/list-product", method = RequestMethod.GET)
    public String admin(Model model,@RequestParam(value="pageNumber", required=false) int pageNumber) {

        int pageSize = Constant.DEFAULT_PAGE_SIZE;
        long totalProducts = productService.getTotalProducts();

        model.addAttribute("totalProducts",totalProducts);
        try {
            List<Category> listCategory = categoryService.getListAllCategory();
            model.addAttribute("listCategory",listCategory);
            List<ProductStatus> listStatus = productStatusService.getListProductStatus();
            model.addAttribute("listStatus",listStatus);
            List<ProductVM> listProducts = productService.getListPagingProduct(new PageRequest(pageNumber-1,10));
            model.addAttribute("listProducts",listProducts);

            int totalPages = 0;
            if(totalProducts % pageSize == 0) {
                totalPages = (int)(totalProducts / pageSize);
            } else {
                totalPages = (int)(totalProducts / pageSize) + 1;
            }
            model.addAttribute("totalPages",totalPages);
        } catch (Exception e) {

        }
        model.addAttribute("pageNumber",pageNumber);
        return "admin_list_product";
    }

    @GetMapping("/detail/{productId}")
    public String detailProduct(Model model,@PathVariable int productId) {
        ProductDetailModel vm = new ProductDetailModel();
        Product existProduct = productService.findOne(productId);
        ModelMapper modelMapper = new ModelMapper();
        vm=modelMapper.map(existProduct,ProductDetailModel.class);
        model.addAttribute("vm",vm);
        return "detail";
    }



    @GetMapping(path="/")
    public String landing(Model model, HttpServletResponse response , @RequestHeader("User-Agent")String userAgent, HttpServletRequest request) {

//        response.addCookie(new Cookie("current-page","Cookie From Java Code - Home landing") );
//        System.out.println("============");
//        System.out.println(userAgent);
//        System.out.println("IP: "+request.getRemoteUser());

        HomeLandingVM vm = new HomeLandingVM();
        this.setLayoutHeaderVM(vm);
        ArrayList<BannerVM> listBanners = new ArrayList<>();
        listBanners.add(new BannerVM("https://www.w3schools.com/bootstrap/la.jpg", "Los Angeles"));
        listBanners.add(new BannerVM("https://www.w3schools.com/bootstrap/chicago.jpg", "Chicago"));
        listBanners.add(new BannerVM("https://www.w3schools.com/bootstrap/ny.jpg", "New York"));

        ArrayList<MenuItemVM> listVtMenuItems = new ArrayList<>();
        listVtMenuItems.add(new MenuItemVM("Menu aside 01", "/"));
        listVtMenuItems.add(new MenuItemVM("Menu aside 02", "/"));
        listVtMenuItems.add(new MenuItemVM("Menu aside 03", "/"));
        listVtMenuItems.add(new MenuItemVM("Menu aside 04", "/"));
        listVtMenuItems.add(new MenuItemVM("Menu aside 05", "/"));

        PaginableItemList<Product> paginableItemListHot = productService.getListProducts(8, 0);
        ArrayList<ProductVM> listHotProductVMs = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        for(Product product : paginableItemListHot.getListData()) {
            ProductVM productVM = modelMapper.map(product, ProductVM.class);
            listHotProductVMs.add(productVM);
        }

        PaginableItemList<Product> paginableItemListTrend = productService.getListProducts(8, 1);
        ArrayList<ProductVM> listTrendProductVMs = new ArrayList<>();
        for(Product product : paginableItemListTrend.getListData()) {
            ProductVM productVM = modelMapper.map(product, ProductVM.class);
            listTrendProductVMs.add(productVM);
        }

        vm.setListBanners(listBanners);
        vm.setListVtMenuItemsAside(listVtMenuItems);
        vm.setListHotProducts(listHotProductVMs);
        vm.setListTrendProducts(listTrendProductVMs);

        model.addAttribute("vm", vm);
        return "admin_list_product";
    }

}
