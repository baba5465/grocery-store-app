package com.baba.grocerystore.Model;

public class ProductModel {
    private int productImage;
    private String productPrice;
    private String productName;

    public ProductModel(int productImage, String productPrice, String productName) {
        this.productImage = productImage;
        this.productPrice = productPrice;
        this.productName = productName;
    }

    public ProductModel() {
    }

    public int getProductImage() {
        return productImage;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public String getProductName() {
        return productName;
    }
}
