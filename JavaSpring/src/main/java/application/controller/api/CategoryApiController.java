package application.controller.api;


import application.data.model.Category;
import application.data.service.CategoryService;
import application.data.service.ProductService;
import application.model.*;
import application.model.Category.CategoryDataModel;
import application.model.Category.CategoryDeleteDataModel;
import application.model.Category.CategoryDetailModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/category")
public class CategoryApiController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @GetMapping("/count")
    public long countProduct(){
        return categoryService.getTotalCategory();
    }

    @GetMapping("/listProduct/{categoryId}")
    public BaseApiResult listProductByCategory(@PathVariable int categoryId){
        DataApiResult result = new DataApiResult();
        result.setData(productService.getListProductByCategory(categoryId));
        result.setSuccess(true);
        result.setMessage("OK");
        return result;
    }

    @PostMapping("/create-category")
    public BaseApiResult createProduct(@RequestBody CategoryDataModel category) {

        DataApiResult result = new DataApiResult();
        try {
            if (!"".equals(category.getName())
                    && !"".equals(category.getDescription())) {
                ModelMapper modelMapper = new ModelMapper();
                Category categoryEntity = modelMapper.map(category, Category.class);
                categoryService.addNewCategory(categoryEntity);
                result.setSuccess(true);
                result.setMessage("Save category successfully :"+categoryEntity.getId());
                result.setData(categoryEntity.getId());
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
    @GetMapping("/detail/{categoryId}")
    public BaseApiResult detailProduct(@PathVariable int categoryId){
        DataApiResult result = new DataApiResult();
        try{
            Category existCategory= categoryService.findOne(categoryId);
            if(existCategory==null){
                result.setSuccess(false);
                result.setMessage("Can't find category");
            }else{
                result.setSuccess(true);
                ModelMapper modelMapper= new ModelMapper();
                CategoryDetailModel categoryDataModel= modelMapper.map(existCategory,CategoryDetailModel.class);
                result.setData(categoryDataModel);
            }

        }catch(Exception e){
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return  result;
    }

    @CrossOrigin
    @PostMapping("/update-category/{categoryId}")
    public BaseApiResult updateCategory(@PathVariable int categoryId, @RequestBody CategoryDataModel category) {
        BaseApiResult result = new BaseApiResult();

        try {
            if(!"".equals(category.getName()) && !"".equals(category.getDescription())) {
                Category existCategory = categoryService.findOne(categoryId);
                if(existCategory == null) {
                    result.setSuccess(false);
                    result.setMessage("Invalid model");
                } else {

                    existCategory.setName(category.getName());
                    existCategory.setDescription(category.getDescription());
                    categoryService.updateCategory(existCategory);
                    result.setSuccess(true);
                    result.setMessage("Update category successfully");
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

    @CrossOrigin
    @PostMapping("/delete-category")
    public BaseApiResult deleteCategory(@RequestBody CategoryDeleteDataModel category) {
        BaseApiResult result = new BaseApiResult();

        try {
            if(categoryService.deleteCategory(category.getCategoryId())) {
                result.setSuccess(true);
                result.setMessage("Delete category successfully");
            }
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }

        return result;
    }
}
