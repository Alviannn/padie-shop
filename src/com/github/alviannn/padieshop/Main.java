package com.github.alviannn.padieshop;

import com.github.alviannn.padieshop.models.products.Product;
import com.github.alviannn.padieshop.models.users.User;
import com.github.alviannn.padieshop.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    /**
     * All registered users will be stored here.
     */
    private final List<User> USER_LIST;
    /**
     * All existing products are stored here
     */
    private final List<Product> PRODUCT_LIST;

    private User CURRENT_USER = null;
    private final User ADMIN;
    private final Scanner scan;


    public Main() {
        this.scan = Utils.SCANNER;

        this.USER_LIST = new ArrayList<>();
        this.PRODUCT_LIST = new ArrayList<>();
        this.ADMIN = new User("admin", "", "", "admin123");

        this.showHomeMenu();
    }

    private void showHomeMenu() {
        Utils.clearScreen();

        System.out.println(
                "1. Login\n" +
                "2. Register\n" +
                "0. Exit");

        while (true) {
            String errorMessage = "Input harus diantara 0-2!\n";
            int choice = (int) Utils.scanLong(">> ", errorMessage);

            if (choice == 0) {
                System.exit(0);
            }

            switch (choice) {
                case 1:
                    this.showLoginMenu();
                    break;
                case 2:
                    this.showRegisterMenu();
                    break;
                default:
                    System.out.print(errorMessage);
                    break;
            }
        }
    }

    private void showLoginMenu() {
        while (true) {
            Utils.clearScreen();

            String uname, pwd;
            System.out.print("Username ['0' untuk kembali]: ");

            uname = scan.nextLine();
            if (uname.equals("0")) {
                return;
            }

            System.out.print("Password ['0' untuk kembali]: ");

            pwd = scan.nextLine();
            if (pwd.equals("0")) {
                return;
            }

            User found = null;
            for (User user : USER_LIST) {
                if (user.getName().equals(uname)) {
                    found = user;
                    break;
                }
            }

            if (found == null) {
                System.out.println("User dengan nama '" + uname + "' tidak dapat ditemukan!");

                Utils.scanEnter();
                continue;
            }

            if (!found.getPassword().equals(pwd)) {
                System.out.println("Password yang dimasukkan tidak sesuai!");

                Utils.scanEnter();
                continue;
            }

            CURRENT_USER = found;
            break;
        }

        // todo: go to dashboard
    }

    private void showRegisterMenu() {
        Utils.clearScreen();

        String username, fullName, email, password;

        while (true) {
            System.out.print("Username ['0' untuk kembali]: ");
            username = scan.nextLine();

            if (username.equals("0")) {
                return;
            }

            int len = username.length();
            if (len < 3 || len > 16) {
                System.out.println("Username harus memiliki panjang >= 3 dan <= 16.");
                continue;
            }

            boolean userExist = false;
            for (User user : USER_LIST) {
                if (user.getName().equals(username)) {
                    userExist = true;
                    break;
                }
            }

            if (userExist) {
                System.out.println("User dengan username ini sudah terdaftar!");
                continue;
            }

            break;
        }

        while (true) {
            System.out.print("Nama panjang ['0' untuk kembali]: ");
            fullName = scan.nextLine();

            if (fullName.equals("0")) {
                return;
            }

            int len = fullName.length();
            if (len < 3 || len > 16) {
                System.out.println("Nama panjang harus memiliki panjang >= 3 dan <= 16.");
                continue;
            }

            if (!Utils.isFullAlphabet(fullName)) {
                System.out.println("Nama panjang tidak diperbolehkan terdapat angka atau karakter spesial (cth: ?!,./')");
                continue;
            }

            break;
        }

        while (true) {
            System.out.print("Email address ['0' untuk kembali]: ");
            email = scan.nextLine();

            if (email.equals("0")) {
                return;
            }

            int len = email.length();
            if (len < 5 || len > 16) {
                System.out.println("Email harus memiliki panjang >= 3 dan <= 16.");
                continue;
            }

            boolean validEmail = email.contains("@") &&
                                 (email.endsWith(".com") || email.endsWith("net"));

            if (!validEmail) {
                System.out.println("Pastikan bahwa email memiliki '@' dan diakhiri oleh '.com' atau '.net'!");
                continue;
            }

            boolean userExist = false;
            for (User user : USER_LIST) {
                if (user.getEmailAddress().equals(email)) {
                    userExist = true;
                    break;
                }
            }

            if (userExist) {
                System.out.println("User dengan alamat email ini sudah terdaftar!");
                continue;
            }

            break;
        }

        while (true) {
            System.out.print("Password ['0' untuk kembali]: ");
            password = scan.nextLine();

            if (password.equals("0")) {
                return;
            }

            int len = password.length();
            if (len < 8 || len > 40) {
                System.out.println("Password harus memiliki panjang >= 8 dan <= 40.");
                continue;
            }

            if (!Utils.isAlphaNum(password)) {
                System.out.println("Password harus mengandung angka dan huruf.");
                continue;
            }

            break;
        }

        User newUser = new User(username, fullName, email, password);
        USER_LIST.add(newUser);

        System.out.println("User berhasil terdaftarkan!");
        Utils.scanEnter();
    }

    public static void main(String[] args) {
        new Main();
    }

}
