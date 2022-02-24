package com.github.alviannn.padieshop.models.products;

public class FoodProduct extends Product {

    private final String expireDate;

    public FoodProduct(String name, long price, String expireDate) {
        super(name + " [F]", price + ((price * Product.FOOD_PERCENT) / 100));
        this.expireDate = expireDate;
    }

    public String getExpireDate() {
        return expireDate;
    }

}
