package com.github.alviannn.padieshop.products;

public class ClothProduct extends Product {

    private final String size;

    public ClothProduct(String name, long price, String size) {
        super(name, price + ((price * Product.CLOTH_PERCENT) / 100));
        this.size = size;
    }

    public String getSize() {
        return size;
    }

}
