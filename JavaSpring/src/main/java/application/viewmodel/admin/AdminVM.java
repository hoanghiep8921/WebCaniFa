package application.viewmodel.admin;

import application.model.Category.CategoryDataModel;
import application.viewmodel.common.ProductVM;

import java.util.ArrayList;

public class AdminVM {
    private String messageTotalProducts;
    private ArrayList<ProductVM> listPagingProducts;
    private ArrayList<CategoryDataModel> listPagingCategory;
    private int totalPagingItems;
    private int currentPage;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public ArrayList<CategoryDataModel> getListPagingCategory() {
        return listPagingCategory;
    }

    public void setListPagingCategory(ArrayList<CategoryDataModel> listPagingCategory) {
        this.listPagingCategory = listPagingCategory;
    }

    public String getMessageTotalProducts() {
        return messageTotalProducts;
    }

    public void setMessageTotalProducts(String messageTotalProducts) {
        this.messageTotalProducts = messageTotalProducts;
    }

    public ArrayList<ProductVM> getListPagingProducts() {
        return listPagingProducts;
    }

    public void setListPagingProducts(ArrayList<ProductVM> listPagingProducts) {
        this.listPagingProducts = listPagingProducts;
    }

    public int getTotalPagingItems() {
        return totalPagingItems;
    }

    public void setTotalPagingItems(int totalPagingItems) {
        this.totalPagingItems = totalPagingItems;
    }
}
