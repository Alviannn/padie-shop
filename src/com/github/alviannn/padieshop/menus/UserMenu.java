package com.github.alviannn.padieshop.menus;

import com.github.alviannn.padieshop.Main;
import com.github.alviannn.padieshop.utils.Utils;

public class UserMenu extends AbstractMenu {

    public UserMenu(Main main) {
        super(main);
    }

    @Override
    public void showMenu() {
        Utils.clearScreen();

        System.out.println(
                Utils.APP_HEADER +
                "Halo, " + main.CURRENT_USER.getFullName() + "\n"
                + "\n" +
                "1. Beli Produk\n" +
                "2. History Pembelian\n" +
                "3. Tambah uang\n" +
                "4. Cek uang\n" +
                "5. Logout\n" +
                "0. Exit");

        while (true) {
            String errorMessage = "Input harus diantara 0-5!\n";
            int choice = (int) Utils.scanLong(">> ", errorMessage);

            if (choice == 0) {
                System.exit(0);
            }

            switch (choice) {
                case 1:
                    return;
                case 2:
                    return;
                case 3:
                    this.addBalance();
                    return;
                case 4:
                    this.showCurrentBalance();
                    return;
                case 5:
                    main.CURRENT_USER = null;
                    return;
                default:
                    System.out.print(errorMessage);
                    break;
            }
        }
    }

    private void addBalance() {
        System.out.print(Utils.APP_HEADER);

        while (true) {
            long additionBalance = Utils.scanLong(
                    "Uang yang ditambah ['0' untuk keluar]: ",
                    "Nominal uang harus dalam bentuk angka bulat.\n");

            if (additionBalance == 0) {
                return;
            }
            if (additionBalance < 0) {
                System.out.println("Nominal uang harus lebih dari 0!");
                continue;
            }

            long currentBalance = main.CURRENT_USER.getBalance();
            main.CURRENT_USER.setBalance(currentBalance + additionBalance);

            System.out.println("Nominal uang " + Utils.formatPrice(currentBalance) + " telah ditambahkan!");
            Utils.scanEnter();
        }
    }

    private void showCurrentBalance() {
        Utils.clearScreen();

        long currentBalance = main.CURRENT_USER.getBalance();
        System.out.println(
                Utils.APP_HEADER +
                "Uang yang anda miliki pada saat ini adalah:");
        System.out.println(Utils.formatPrice(currentBalance));

        Utils.scanEnter();
    }

}
