package com.github.alviannn.padieshop.models.products;

public class TechnologyProduct extends Product {

    private final String version;

    public TechnologyProduct(String name, long price, String version) {
        super(name, price + ((price * Product.TECH_PERCENT) / 100));
        this.version = version;
    }

    public String getVersion() {
        return version;
    }

}