package application.data.service;

import application.data.model.Sale;
import application.data.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SaleService {
    @Autowired
    private SaleRepository saleRepository;

    public void addNewSale(Sale sale){
        saleRepository.save(sale);
    }
    public Sale findOne(int saleId){
        return saleRepository.findOne(saleId);
    }

    public boolean updateSale(Sale sale){
        try {
            saleRepository.save(sale);
            return true;
        } catch (Exception e) {

        }
        return false;
    }
    public int findOneSale(Date date){
        return saleRepository.findOneSale(date);
    }
    public int checkSale(Date date){
        return saleRepository.checkSale(date);
    }

    public void updateSaleAll(){
        saleRepository.updateSaleAll();
    }

    public List<Sale> getAll(){
        return saleRepository.findAll();
    }
}
