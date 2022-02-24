package com.github.alviannn.padieshop.utils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Utils {

    public final static Scanner SCANNER = new Scanner(System.in);

    /**
     * Clears the console screen that works on any OS.
     *
     * NOTE: this doesn't work on IDE (ex: IntellIJ, Eclipse)
     */
    public static void clearScreen() {
        String osName = System.getenv("OS").toUpperCase();

        try {
            if (osName.contains("WIN")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static long scanLong(String msg, String errorMsg) {
        System.out.print(msg);
        long result;

        while (true) {
            try {
                result = SCANNER.nextLong();
                break;
            } catch (InputMismatchException ignored) {
                System.out.print(errorMsg);
            } finally {
                SCANNER.nextLine();
            }
        }

        return result;
    }

    /**
     * Creates the effect of requiring the user to press enter to continue the flow.
     */
    public static void scanEnter() {
        System.out.print("Press enter to continue...");
        SCANNER.nextLine();
    }

    /**
     * Determines the {@param str} is only filled with alphabet.
     */
    public static boolean isFullAlphabet(String str) {
        char[] chars = str.toCharArray();
        for (char c : chars) {
            boolean isAlpha = !Character.isDigit(c) && (Character.isLetter(c) || c == ' ');

            if (!isAlpha) {
                return false;
            }
        }
        return true;
    }

    /**
     * Determines the {@param str} is a combination of alphabet and numeric.
     *
     * Alphabet check is ignored since it's there by default.
     */
    public static boolean isAlphaNum(String str) {
        char[] chars = str.toCharArray();
        boolean hasNum = false;

        for (char c : chars) {
            if (Character.isDigit(c)) {
                hasNum = true;
            }

            if (hasNum) {
                return true;
            }
        }

        return false;
    }

}
