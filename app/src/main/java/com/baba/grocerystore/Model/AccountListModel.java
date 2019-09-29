package com.baba.grocerystore.Model;

public class AccountListModel {
    int image;
    String text;

    public AccountListModel(int image, String text) {
        this.image = image;
        this.text = text;
    }

    public AccountListModel() {
    }

    public int getImage() {
        return image;
    }

    public String getText() {
        return text;
    }
}
