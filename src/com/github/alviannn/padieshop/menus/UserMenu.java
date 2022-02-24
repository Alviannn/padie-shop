package com.github.alviannn.padieshop.menus;

import com.github.alviannn.padieshop.Main;
import com.github.alviannn.padieshop.models.Receipt;
import com.github.alviannn.padieshop.utils.Utils;

import java.util.List;

public class UserMenu extends AbstractMenu {

    private final BuyProductMenu buyProductMenu;

    public UserMenu(Main main) {
        super(main);

        this.buyProductMenu = new BuyProductMenu(main);
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
                    while (true) {
                        try {
                            buyProductMenu.showMenu();
                        } catch (RuntimeException e) {
                            // The user has finished using this menu
                            if (e.getMessage().equals("Finished")) {
                                return;
                            }
                        }
                    }
                case 2:
                    this.showShoppingHistory();
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

    private void showShoppingHistory() {
        Utils.clearScreen();

        List<Receipt> receipts = main.CURRENT_USER.getReceipts();

        String lineWithSeparator = "+-----+----------+----------------------+";

        System.out.println(lineWithSeparator);
        System.out.printf("| %-3s | %-5s | %-20s |\n", "No.", "ID Struk", "Total Harga");
        System.out.println(lineWithSeparator);

        int count = 0;
        for (Receipt receipt : receipts) {
            count++;
            System.out.printf("| %3d | %-5s | %-20s |\n",
                    count,
                    "#" + receipt.getId(),
                    Utils.formatPrice(receipt.getTotalPrice()));
        }
        System.out.println(lineWithSeparator);

        Receipt receipt;
        while (true) {
            String errorMessage = "Pilihlah struk yang tertera diantara 1-" + receipts.size() + "!";
            int idx = (int) Utils.scanLong(
                    "Struk yang dipilih [1-" + receipts.size() + " | '0' untuk kembali]: ",
                    errorMessage);

            if (idx == 0) {
                return;
            }
            if (idx < 0 || idx > receipts.size()) {
                System.out.println(errorMessage);
                continue;
            }

            receipt = receipts.get(idx - 1);
            break;
        }

        Utils.clearScreen();
        receipt.printFormatted();

        Utils.scanEnter();
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
