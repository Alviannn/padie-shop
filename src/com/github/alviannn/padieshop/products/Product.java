package com.github.alviannn.padieshop.products;

public abstract class Product {

    public final static long FOOD_PERCENT = 10;
    public final static long CLOTH_PERCENT = 25;
    public final static long TECH_PERCENT = 30;

    private final String name;
    private final long price;

    public Product(String name, long price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public long getPrice() {
        return price;
    }

}
