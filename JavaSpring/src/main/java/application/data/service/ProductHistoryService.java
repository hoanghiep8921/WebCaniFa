package application.data.service;

import application.data.model.ProductHistory;
import application.data.repository.ProductHistoryRepository;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductHistoryService {

    private static final Logger logger = LogManager.getLogger(ProductHistoryService.class);
    @Autowired
    private ProductHistoryRepository productHistoryRepository;

    public List<ProductHistory> getListProductHistory(){
        try{
            return productHistoryRepository.findAll();
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            return new ArrayList<>();
        }
    }
    public ProductHistory getOne(int productHistoryId){
        return productHistoryRepository.getOne(productHistoryId);
    }
    public void addNewProductHistory(ProductHistory productHistory) {
        productHistoryRepository.save(productHistory);
    }

    @Transactional
    public void addNewListProductHistory(List<ProductHistory> listProductHistoryes) {
        productHistoryRepository.save(listProductHistoryes);
    }


    public ProductHistory findOne(int productHistoryId) {
        return productHistoryRepository.getOne(productHistoryId);
    }

    public boolean updateProductHistory(ProductHistory productHistory) {
        try {
            productHistoryRepository.save(productHistory);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return false;
    }

    public boolean deleteProductHistory (int productHistoryId) {
        try {
            productHistoryRepository.delete(productHistoryId);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return false;
    }
}

