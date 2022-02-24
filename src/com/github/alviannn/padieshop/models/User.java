package com.github.alviannn.padieshop.models;

import java.util.ArrayList;
import java.util.List;

public class User {

    private final String name, fullName, emailAddress, password;
    /**
     * The list of receipts that the user has,
     * it acts as a shopping history.
     */
    private final List<Receipt> receipts;

    public User(String name, String fullName, String emailAddress, String password) {
        this.name = name;
        this.fullName = fullName;
        this.emailAddress = emailAddress;
        this.password = password;

        this.receipts = new ArrayList<>();
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

}
