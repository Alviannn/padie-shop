package com.github.alviannn.padieshop.menus;

import com.github.alviannn.padieshop.Main;
import com.github.alviannn.padieshop.models.User;
import com.github.alviannn.padieshop.utils.Utils;

public class HomeMenu extends AbstractMenu {

    public HomeMenu(Main main) {
        super(main);
    }

    @Override
    public void showMenu() {
        Utils.clearScreen();

        System.out.println(
                Utils.APP_HEADER +
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
                    return;
                case 2:
                    this.showRegisterMenu();
                    return;
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
            if (uname.equals("admin")) {
                found = main.ADMIN;
            } else {
                for (User user : main.USER_LIST) {
                    if (user.getName().equals(uname)) {
                        found = user;
                        break;
                    }
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

            main.CURRENT_USER = found;

            System.out.println("Berhasil login!");
            Utils.scanEnter();

            break;
        }
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
            for (User user : main.USER_LIST) {
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
            if (len < 5 || len > 30) {
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
            for (User user : main.USER_LIST) {
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
        main.USER_LIST.add(newUser);

        System.out.println("User berhasil terdaftarkan!");
        Utils.scanEnter();
    }

}
