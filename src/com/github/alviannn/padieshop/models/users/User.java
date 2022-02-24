package com.github.alviannn.padieshop.models.users;

import com.github.alviannn.padieshop.models.Receipt;
import com.github.alviannn.padieshop.models.products.Product;

import java.util.ArrayList;
import java.util.List;

public class User {

    private final String name, fullName, emailAddress, password;
    /**
     * The list of receipts that the user has,
     * it acts as a shopping history.
     */
    private final List<Receipt> receipts;
    /**
     * The user's current cart
     */
    private final List<Product> cart;
    /**
     * The amount of money that the user has
     */
    private long balance;

    public User(String name, String fullName, String emailAddress, String password) {
        this.name = name;
        this.fullName = fullName;
        this.emailAddress = emailAddress;
        this.password = password;

        this.balance = 1_000;
        this.receipts = new ArrayList<>();
        this.cart = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public List<Receipt> getReceipts() {
        return receipts;
    }

    public List<Product> getCart() {
        return cart;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

}
