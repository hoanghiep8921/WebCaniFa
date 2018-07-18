package application.data.service;


import application.data.model.Category;
import application.data.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    private static final Logger logger = LogManager.getLogger(CategoryService.class);
    @Autowired
    private CategoryRepository categoryRepository;

    public long getTotalCategory() {
        return categoryRepository.getTotalCategory();
    }
    public List<Category> getListAllCategory(){
        try{
            return categoryRepository.findAll();
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            return new ArrayList<>();
        }
    }

    public Category getOne(int categoryId){
        return categoryRepository.getOne(categoryId);
    }
    public void addNewCategory(Category category) {
        categoryRepository.save(category);
    }

    @Transactional
    public void addNewListCategory(List<Category> listCategorys) {
        categoryRepository.save(listCategorys);
    }


    public Category findOne(int categoryId) {
        return categoryRepository.getOne(categoryId);
    }

    public boolean updateCategory(Category category) {
        try {
            categoryRepository.save(category);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return false;
    }

    public boolean deleteCategory(int categoryId) {
        try {
            categoryRepository.delete(categoryId);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return false;
    }
}

