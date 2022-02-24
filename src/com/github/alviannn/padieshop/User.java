package com.github.alviannn.padieshop;

public class User {

    private final String name, fullName, emailAddress, password;

    public User(String name, String fullName, String emailAddress, String password) {
        this.name = name;
        this.fullName = fullName;
        this.emailAddress = emailAddress;
        this.password = password;
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

}
