package com.github.alviannn.padieshop;

import com.github.alviannn.padieshop.menus.HomeMenu;
import com.github.alviannn.padieshop.models.products.Product;
import com.github.alviannn.padieshop.models.users.User;

import java.util.ArrayList;
import java.util.List;

public class Main {

    /**
     * All registered users will be stored here.
     */
    public final List<User> USER_LIST;
    /**
     * All existing products are stored here
     */
    public final List<Product> PRODUCT_LIST;

    public User CURRENT_USER = null;
    public final User ADMIN;

    public Main() {
        this.USER_LIST = new ArrayList<>();
        this.PRODUCT_LIST = new ArrayList<>();
        this.ADMIN = new User("admin", "", "", "admin123");

        HomeMenu homeMenu = new HomeMenu(this);

        while (true) {
            if (CURRENT_USER == null) {
                homeMenu.showMenu();
            } else {
                if (CURRENT_USER.getName().equals("admin")) {
                    // todo: admin menu
                } else {
                    // todo: user menu
                }
            }
        }
    }

    public static void main(String[] args) {
        new Main();
    }

}
