package com.github.alviannn.padieshop;

import com.github.alviannn.padieshop.products.Product;

import java.util.ArrayList;
import java.util.List;

public class Receipt {

    private final int id;
    private final List<Product> products;

    public Receipt(int id) {
        this.id = id;
        this.products = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public List<Product> getProducts() {
        return products;
    }

}
