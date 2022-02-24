package com.github.alviannn.padieshop.menus;

import com.github.alviannn.padieshop.Main;
import com.github.alviannn.padieshop.utils.Utils;

import java.util.Scanner;

public abstract class AbstractMenu {

    protected final Main main;
    protected final Scanner scan;

    public AbstractMenu(Main main) {
        this.main = main;
        this.scan = Utils.SCANNER;
    }

    /**
     * Shows the menu view to the user
     */
    public abstract void showMenu();

}
