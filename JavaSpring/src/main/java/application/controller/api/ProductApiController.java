package application.controller.api;

import application.data.model.Product;
import application.data.service.CategoryService;
import application.data.service.ProductService;
import application.data.service.ProductStatusService;
import application.model.*;
import application.model.Product.ProductCategory;
import application.model.Product.ProductDataModel;
import application.model.Product.ProductDeleteDataModel;
import application.model.Product.ProductDetailModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/product")
public class ProductApiController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductStatusService productStatusService;

    @GetMapping("/count")
    public long countProduct(){
        return productService.getTotalProducts();
    }

    @GetMapping("/hot")
    public BaseApiResult productHot(){
        DataApiResult result = new DataApiResult();
        try {
            List<Product> productList = productService.getProductByPriceBetween(1000, 1500);
            List<ProductDetailModel> productDetailModels= new ArrayList<>();
            if(productList==null){
                result.setSuccess(false);
                result.setMessage("Can't find this product");
            }else{
                result.setMessage("Good job");
                result.setSuccess(true);
                for(int i=0;i<productList.size();++i) {
                    ModelMapper modelMapper = new ModelMapper();
                    ProductDetailModel productDetailModel = modelMapper.map(productList.get(i), ProductDetailModel.class);
                    productDetailModels.add(productDetailModel);
                }
                result.setData(productDetailModels);
            }
        }
        catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());

        }
        return result;
    }

    @GetMapping("/cheap")
    public BaseApiResult productCheap(){
        DataApiResult result = new DataApiResult();
        try {
            List<Product> productList = productService.getProductByPriceBetween(100, 500);
            List<ProductDetailModel> productDetailModels= new ArrayList<>();
            if(productList==null){
                result.setSuccess(false);
                result.setMessage("Can't find this product");
            }else{
                result.setMessage("Good job");
                result.setSuccess(true);
                for(int i=0;i<productList.size();++i) {
                    ModelMapper modelMapper = new ModelMapper();
                    ProductDetailModel productDetailModel = modelMapper.map(productList.get(i), ProductDetailModel.class);
                    productDetailModels.add(productDetailModel);
                }
                result.setData(productDetailModels);
            }
        }
        catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());

        }
        return result;
    }

    @GetMapping("/detail/{productId}")
    public BaseApiResult detailProduct(@PathVariable int productId) {


        DataApiResult result = new DataApiResult();
        try{
            Product existProduct = productService.findOne(productId);
            if(existProduct == null) {
                result.setSuccess(false);
                result.setMessage("Can't find this product");
            } else {
                result.setSuccess(true);
                ModelMapper modelMapper = new ModelMapper();
                ProductDetailModel productDetailModel = modelMapper.map(existProduct, ProductDetailModel.class);
                result.setData(productDetailModel);
            }
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    @GetMapping("/detailCate/{categoryId}")
    public BaseApiResult detailProductCategory(@PathVariable int categoryId) {


        DataApiResult result = new DataApiResult();
        try{
            List<ProductCategory> existListProduct = productService.getListProductByCategory(categoryId);
            if(existListProduct == null) {
                result.setData(0);
                result.setSuccess(false);
                result.setMessage("Can't find this product");
            } else {
                result.setSuccess(true);
                result.setData(existListProduct);
                result.setMessage("OK");
            }
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    @GetMapping("/getAll")
    public BaseApiResult getAllProduct() {
        DataApiResult result = new DataApiResult();
        List<Product> existListProduct = productService.getAll();
        result.setData(existListProduct);
        result.setMessage("OK");
        result.setSuccess(true);
        return  result;
    }

    @PostMapping("/create-product")
    public BaseApiResult createProduct(@RequestBody ProductDataModel product) {
        DataApiResult result = new DataApiResult();

        try {
            if(!"".equals(product.getName()) && !"".equals(product.getDescription()) && !"".equals(product.getImage())) {
                ModelMapper modelMapper = new ModelMapper();
                Product productEntity = modelMapper.map(product, Product.class);
                productEntity.setCategory(product.getCategory());
                productEntity.setProductStatus(product.getProductStatus());
                productEntity.setCreatedDate(new Date());
                productEntity.setUpdateDate(new Date());
                productService.addNewProduct(productEntity);
                result.setSuccess(true);
                result.setMessage("Save product successfully: " + productEntity.getId());
                result.setData(productEntity.getId());
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

    @CrossOrigin
    @PostMapping("/update-product/{productId}")
    public BaseApiResult updateProduct(@PathVariable int productId, @RequestBody ProductDataModel product) {
        BaseApiResult result = new BaseApiResult();

        try {
            if(!"".equals(product.getName()) && !"".equals(product.getDescription()) && !"".equals(product.getImage())) {
                // check existed product
                Product existProduct = productService.findOne(productId);
                if(existProduct == null) {
                    result.setSuccess(false);
                    result.setMessage("Invalid model");
                } else {
                    existProduct.setImage(product.getImage());
                    existProduct.setName(product.getName());
                    existProduct.setUpdateDate(new Date());
                    existProduct.setDescription(product.getDescription());
                    existProduct.setCategory(product.getCategory());
                    existProduct.setProductStatus(product.getProductStatus());
                    productService.updateProduct(existProduct);
                    result.setSuccess(true);
                    result.setMessage("Update product successfully");
                }
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

    @GetMapping("/delete-product/{id}")
    public BaseApiResult deleteProduct(@PathVariable int id) {
        BaseApiResult result = new BaseApiResult();

        try {
                productService.deleteProduct(id);
                result.setSuccess(true);
                result.setMessage("Delete product successfully");

        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }

        return result;
    }

}
