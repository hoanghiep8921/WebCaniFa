package application.data.service;

import application.data.model.ProductStatus;
import application.data.repository.ProductStatusRepository;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductStatusService {

    private static final Logger logger = LogManager.getLogger(ProductStatusService.class);
    @Autowired
    private ProductStatusRepository productStatusRepository;

    public List<ProductStatus> getListProductStatus(){
        try{
            return productStatusRepository.findAll();
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            return new ArrayList<>();
        }
    }
    public ProductStatus getOne(int productStatusId){
        return productStatusRepository.getOne(productStatusId);
    }
    public void addNewCategory(ProductStatus productStatus) {
        productStatusRepository.save(productStatus);
    }

    @Transactional
    public void addNewListProductStatus(List<ProductStatus> listProductStatuses) {
        productStatusRepository.save(listProductStatuses);
    }


    public ProductStatus findOne(int productStatusId) {
        return productStatusRepository.getOne(productStatusId);
    }

    public boolean updateProductStatus(ProductStatus productStatus) {
        try {
            productStatusRepository.save(productStatus);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return false;
    }

    public boolean deleteProductStatus (int productStatusId) {
        try {
            productStatusRepository.delete(productStatusId);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return false;
    }
}

