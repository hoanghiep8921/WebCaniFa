package application.data.service;


import application.data.model.PaginableItemList;
import application.data.model.Product;
import application.data.repository.ProductRepository;
import application.model.Product.ProductCategory;
import application.viewmodel.common.ProductVM;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {

    private static final Logger logger = LogManager.getLogger(ProductService.class);

    @Autowired
    private ProductRepository productRepository;

    public List<ProductCategory> getListProductByCategory(int categoryId){
        return productRepository.getListProductByCategory(categoryId);
    }

    public void addNewProduct(Product product) {
        productRepository.save(product);
    }

    public List<Product> getProductByPriceBetween(int a,int b){
        return productRepository.getProductByPriceBetween(a,b);
    }

    @Transactional
    public void addNewListProducts(List<Product> listProducts) {
        productRepository.save(listProducts);
    }

    public long getTotalProducts() {
        return productRepository.getTotalProducts();
    }

    public PaginableItemList<Product> getListProducts(int pageSize, int pageNumber) {
        PaginableItemList<Product> paginableItemList = new PaginableItemList<>();
        paginableItemList.setPageSize(pageSize);
        paginableItemList.setPageNumber(pageNumber);

        Page<Product> pages = productRepository.findAll(new PageRequest(pageNumber, pageSize));
        paginableItemList.setTotalProducts(pages.getTotalElements());
        paginableItemList.setListData(pages.getContent());
        return paginableItemList;
    }
    public List<Product> getAll(){
        return productRepository.findAll();
    }

    public Product findOne(int productId) {
        return productRepository.getOne(productId);
    }

    public boolean updateProduct(Product product) {
        try {
            productRepository.save(product);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return false;
    }

    public void deleteProduct(int productId) {
        productRepository.delete(productId);
    }
    public List<ProductVM> getListPagingProduct(Pageable pageable){

        return productRepository.getListPagingProduct(pageable);
    }
}
