package application.viewmodel.common;

import application.viewmodel.homelanding.MenuItemVM;

import java.util.ArrayList;

/**
 * Created by ManhNguyen on 1/30/18.
 */
public abstract class LayoutHeaderVM {
    private LogoVM logo;
    private ArrayList<MenuItemVM> listHrMenuItems;

    public LogoVM getLogo() {
        return logo;
    }

    public void setLogo(LogoVM logo) {
        this.logo = logo;
    }

    public ArrayList<MenuItemVM> getListHrMenuItems() {
        return listHrMenuItems;
    }

    public void setListHrMenuItems(ArrayList<MenuItemVM> listHrMenuItems) {
        this.listHrMenuItems = listHrMenuItems;
    }
}
