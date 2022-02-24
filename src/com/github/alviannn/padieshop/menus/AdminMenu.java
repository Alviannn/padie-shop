package com.github.alviannn.padieshop.menus;

import com.github.alviannn.padieshop.Main;
import com.github.alviannn.padieshop.models.products.ClothProduct;
import com.github.alviannn.padieshop.models.products.FoodProduct;
import com.github.alviannn.padieshop.models.products.Product;
import com.github.alviannn.padieshop.models.products.TechnologyProduct;
import com.github.alviannn.padieshop.utils.Utils;

public class AdminMenu extends AbstractMenu {

    public AdminMenu(Main main) {
        super(main);
    }

    @Override
    public void showMenu() {
        Utils.clearScreen();

        System.out.println(
                Utils.APP_HEADER +
                "Halo, Admin\n" +
                "\n" +
                "Produk yang ingin ditambahkan\n" +
                "1. Produk makanan   / Food\n" +
                "2. Produk pakaian   / Cloth\n" +
                "3. Produk teknologi / Technology\n" +
                "0. Logout");

        int choice;
        while (true) {
            String errorMessage = "Pilihlah pilihan antara 0-3!";
            choice = (int) Utils.scanLong(">> ", errorMessage);

            if (choice == 0) {
                main.CURRENT_USER = null;
            }

            if (choice < 0 || choice > 3) {
                System.out.println(errorMessage);
                continue;
            }

            break;
        }

        Utils.clearScreen();

        String name;
        long price;

        System.out.println(
                Utils.APP_HEADER +
                "Lengkapilah data produk!");

        while (true) {
            System.out.print("Nama produk ['0' untuk kembali]: ");
            name = scan.nextLine();

            if (name.equals("0")) {
                return;
            }
            if (name.length() < 3 || name.length() > 16) {
                System.out.println("Nama produk harus memiliki panjang >= 3 dan <= 16.");
                continue;
            }

            break;
        }

        while (true) {
            price = Utils.scanLong(
                    "Harga produk ['0' untuk kembali]: ",
                    "Hanya dapat memasukkan harga nominal bulat.");

            if (price == 0) {
                return;
            }
            if (price < 1_000) {
                System.out.println("Harga produk harus >= Rp 1.000,00");
                continue;
            }

            break;
        }

        Product product;
        switch (choice) {
            case 1: {
                System.out.print("Tanggal expire: ");
                String expireDate = scan.nextLine();

                product = new FoodProduct(name, price, expireDate);
                break;
            }
            case 2: {
                String size;

                while (true) {
                    System.out.print("Size pakaian [S | M | L | XL]: ");
                    size = scan.nextLine();

                    boolean validSize = size.equals("S") || size.equals("M") || size.equals("L") || size.equals("XL");
                    if (!validSize) {
                        System.out.println("Size pakaian harus bernilai S, M, L, XL.");
                        continue;
                    }

                    break;
                }

                product = new ClothProduct(name, price, size);
                break;
            }
            case 3: {
                System.out.print("Versi produk: ");
                String versi = scan.nextLine();

                product = new TechnologyProduct(name, price, versi);
                break;
            }
            default:
                // not possible
                return;
        }

        main.PRODUCT_LIST.add(product);
        System.out.println("Produk telah ditambahkan!");
    }

}
