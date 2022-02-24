package com.github.alviannn.padieshop.models;

import com.github.alviannn.padieshop.models.products.ClothProduct;
import com.github.alviannn.padieshop.models.products.FoodProduct;
import com.github.alviannn.padieshop.models.products.Product;
import com.github.alviannn.padieshop.models.products.TechnologyProduct;
import com.github.alviannn.padieshop.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class Receipt {

    /** For the possibility of always increasing the receipt ID,
     * we need a global ID that tracks it. */
    public static int CURRENT_ID = 0;

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

    public long getTotalPrice() {
        long totalPrice = 0;

        for (Product product : products) {
            totalPrice += product.getPrice();
        }

        return totalPrice;
    }

    public void printFormatted() {
        System.out.println(
                "Padie Shop\n" +
                "-------------------------------------\n" +
                "ID: #" + id + "\n");

        int count = 0;
        for (Product product : products) {
            count++;

            System.out.printf("%d. %s - %s\n",
                    count, product.getName(), Utils.formatPrice(product.getPrice()));

            if (product instanceof FoodProduct) {
                System.out.println("   - Expire date: " + ((FoodProduct) product).getExpireDate());
            } else if (product instanceof ClothProduct) {
                System.out.println("   - Size: " + ((ClothProduct) product).getSize());
            } else if (product instanceof TechnologyProduct) {
                System.out.println("   - Version: " + ((TechnologyProduct) product).getVersion());
            }
        }

        System.out.println(
                "-------------------------------------\n" +
                "Quantity   : " + products.size() + "\n" +
                "Total Price: " + Utils.formatPrice(this.getTotalPrice()) + "\n" +
                "-------------------------------------");
    }

}
