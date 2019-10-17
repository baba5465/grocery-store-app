package com.baba.grocerystore.Model;

public class CartItemModel {
    String itemName;
    String itemPrice;
    String itemImage;
    String itemId;
    String itemQuantity;
    String itemTotalPrice;

    public CartItemModel(String itemName, String itemPrice, String itemImage, String itemId, String itemQuantity, String itemTotalPrice) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemImage = itemImage;
        this.itemId = itemId;
        this.itemQuantity = itemQuantity;
        this.itemTotalPrice = itemTotalPrice;
    }

    public CartItemModel() {
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public String getItemImage() {
        return itemImage;
    }

    public String getItemId() {
        return itemId;
    }

    public String getItemQuantity() {
        return itemQuantity;
    }

    public String getItemTotalPrice() {
        return itemTotalPrice;
    }
}
