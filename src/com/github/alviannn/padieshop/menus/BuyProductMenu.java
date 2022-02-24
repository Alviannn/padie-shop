package com.github.alviannn.padieshop.menus;

import com.github.alviannn.padieshop.Main;
import com.github.alviannn.padieshop.models.products.Product;
import com.github.alviannn.padieshop.models.users.User;
import com.github.alviannn.padieshop.utils.Utils;

import java.util.List;

public class BuyProductMenu extends AbstractMenu {

    public BuyProductMenu(Main main) {
        super(main);
    }

    @Override
    public void showMenu() {
        Utils.clearScreen();

        System.out.println(
                Utils.APP_HEADER +
                "1. Pilih produk\n" +
                "2. Checkout\n" +
                "3. Kembali");

        while (true) {
            String errorMessage = "Input harus diantara 0-3!\n";
            int choice = (int) Utils.scanLong(">> ", errorMessage);

            if (choice == 0) {
                System.exit(0);
            }

            switch (choice) {
                case 1:
                    this.chooseProduct();
                    return;
                case 2:
                    this.checkoutCart();
                    return;
                case 3:
                    // Throw an error that will be caught by the UserMenu,
                    // this is to tell the user is coming back to the UserMenu
                    throw new RuntimeException("Finished!");
                default:
                    System.out.print(errorMessage);
                    break;
            }
        }
    }

    private void chooseProduct() {
        Utils.clearScreen();

        String lineWithSeparator = "+-----+------------------+----------------------+";

        System.out.println(lineWithSeparator);
        System.out.printf("| %-3s | %-16s | %-20s |\n", "No.", "Nama Produk", "Harga Produk");
        System.out.println(lineWithSeparator);

        int count = 0;
        for (Product product : main.PRODUCT_LIST) {
            count++;

            System.out.printf("| %3d | %-16s | %-20s |\n",
                    count, product.getName(), Utils.formatPrice(product.getPrice()));
        }

        System.out.println(lineWithSeparator);
        Product chosenProduct;

        while (true) {
            String errorMessage = "Pilihlah produk dengan antara nomor 1-" + main.PRODUCT_LIST.size() + ".";

            int idx = (int) Utils.scanLong(
                    "No. produk yang dipilih [1-" + main.PRODUCT_LIST.size() + " | '0' untuk kembali]: ",
                    errorMessage);

            if (idx == 0) {
                return;
            }
            if (idx < 0 || idx > main.PRODUCT_LIST.size()) {
                System.out.println(errorMessage);
                continue;
            }

            chosenProduct = main.PRODUCT_LIST.get(idx - 1);
            break;
        }

        main.CURRENT_USER.getCart().add(chosenProduct);

        System.out.println("Produk terpilih telah ditambahkan pada keranjang!");
        Utils.scanEnter();
    }

    private void checkoutCart() {
        Utils.clearScreen();

        User user = main.CURRENT_USER;
        List<Product> cart = user.getCart();

        long currentBalance = user.getBalance();
        long totalPrice = 0;

        for (Product product : cart) {
            totalPrice += product.getPrice();
        }

        if (currentBalance < totalPrice) {
            System.out.println("Anda tidak memiliki uang yang cukup!");
        } else {
            // todo: print receipt
        }

        Utils.scanEnter();
    }

    public static void main(String[] args) {
        Main main = new Main();
        new BuyProductMenu(main).chooseProduct();
    }

}