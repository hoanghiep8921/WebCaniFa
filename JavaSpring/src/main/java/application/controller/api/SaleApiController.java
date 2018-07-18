package application.controller.api;

import application.data.model.Sale;
import application.data.service.SaleService;
import application.model.BaseApiResult;
import application.model.DataApiResult;
import application.model.Sale.DateSale;
import application.model.Sale.SaleDetailModel;
import application.model.Sale.SaleModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@CrossOrigin
@RestController
@RequestMapping("/api/sale")
public class SaleApiController {

    @Autowired
    private SaleService saleService;

    @PostMapping("/create-sale")
    public BaseApiResult createSale(@RequestBody SaleModel saleModel){
        DataApiResult result = new DataApiResult();
        try {
            if (!"".equals(saleModel.getCode())&&!"".equals(saleModel.getContent())&&!"".equals(saleModel.getEndDate())&& !"".equals(saleModel.getSaleNumber())) {
                ModelMapper modelMapper = new ModelMapper();
                Sale saleEntity = modelMapper.map(saleModel, Sale.class);
                saleEntity.setCreateDate(new Date());
                saleEntity.setStatus(0);
                saleService.updateSaleAll();
                saleService.addNewSale(saleEntity);
                result.setSuccess(true);
                result.setMessage("Save sale successfully :"+saleEntity.getId());
                result.setData(saleEntity.getId());
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

    @GetMapping("/update-category/{saleId}")
    public BaseApiResult updateCategory(@PathVariable int saleId) {
        DataApiResult result = new DataApiResult();
        Sale existSale = saleService.findOne(saleId);
        if(existSale.getStatus()==0){
            existSale.setStatus(1);
            saleService.updateSale(existSale);
        }else{
            existSale.setStatus(0);
            saleService.updateSale(existSale);
        }
        result.setSuccess(true);
        result.setMessage("Update category successfully");
        return result;
    }

    @GetMapping("/listSale")
        public BaseApiResult updateCategory() {
            DataApiResult result= new DataApiResult();
            result.setData(saleService.getAll());
            result.setMessage("OK");
            result.setSuccess(true);
            return result;

        }


    @GetMapping("/detailSale/{saleId}")
    public BaseApiResult detailSale(@PathVariable int saleId) {
        DataApiResult result = new DataApiResult();
        Sale existSale = saleService.findOne(saleId);
        SaleDetailModel saleDetailModel= new SaleDetailModel(existSale.getId(),existSale.getContent(),existSale.getSaleNumber(),existSale.getCode(),existSale.getCreateDate().toString(),existSale.getEndDate().toString(),existSale.getStatus());
        result.setData(saleDetailModel);
        result.setSuccess(true);
        result.setMessage("successfully");
        return result;
    }

    @PostMapping("/detail")
    public BaseApiResult detail(@RequestBody DateSale date){
        DataApiResult result = new DataApiResult();
        try{
            if(saleService.checkSale(date.getDate())==1){
                int idSale = saleService.findOneSale(date.getDate());
                Sale existSale = saleService.findOne(idSale);
                result.setData(existSale);
                result.setSuccess(true);
                result.setMessage("Đang trong thời gian khuyến mãi");
            }else{
                result.setMessage("Không trong thời gian khuyến mãi");
                result.setSuccess(false);
                result.setData(0);
            }

        }catch(Exception e){
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return  result;
    }
}
