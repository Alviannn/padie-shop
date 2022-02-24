package com.github.alviannn.padieshop;

import com.github.alviannn.padieshop.menus.AdminMenu;
import com.github.alviannn.padieshop.menus.HomeMenu;
import com.github.alviannn.padieshop.menus.UserMenu;
import com.github.alviannn.padieshop.models.products.ClothProduct;
import com.github.alviannn.padieshop.models.products.FoodProduct;
import com.github.alviannn.padieshop.models.products.Product;
import com.github.alviannn.padieshop.models.products.TechnologyProduct;
import com.github.alviannn.padieshop.models.User;

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

    /**
     * Filling default values to the app for testing purposes.
     */
    private void fillWithDefault() {
        USER_LIST.add(new User("alvian", "Alvian DQ", "alvianndq@gmail.com", "hello123"));

        PRODUCT_LIST.add(new FoodProduct("Milo", 8_000, "12 Des 2022"));
        PRODUCT_LIST.add(new ClothProduct("Jaket Anime", 130_000, "XL"));
        PRODUCT_LIST.add(new TechnologyProduct("Logitech", 520_000, "G231 Prodigy"));
    }

    public Main() {
        this.USER_LIST = new ArrayList<>();
        this.PRODUCT_LIST = new ArrayList<>();
        this.ADMIN = new User("admin", "", "", "admin123");

        this.fillWithDefault();

        HomeMenu homeMenu = new HomeMenu(this);
        UserMenu userMenu = new UserMenu(this);
        AdminMenu adminMenu = new AdminMenu(this);

        while (true) {
            if (CURRENT_USER == null) {
                homeMenu.showMenu();
            } else {
                if (CURRENT_USER.getName().equals("admin")) {
                    adminMenu.showMenu();
                } else {
                    userMenu.showMenu();
                }
            }
        }
    }

    public static void main(String[] args) {
        new Main();
    }

}
