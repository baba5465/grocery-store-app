package com.baba.grocerystore.Model;

public class OrderDetails {
    String userName, userAdd, userNumber, userEmail, userId, itemName, itemPrice, paymentStatus, quantity, totalAmount, orderDate, orderTime;

    public OrderDetails(String userName, String userAdd, String userNumber, String userEmail, String userId, String itemName, String itemPrice, String paymentStatus, String quantity, String totalAmount, String orderDate, String orderTime) {
        this.userName = userName;
        this.userAdd = userAdd;
        this.userNumber = userNumber;
        this.userEmail = userEmail;
        this.userId = userId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.paymentStatus = paymentStatus;
        this.quantity = quantity;
        this.totalAmount = totalAmount;
        this.orderDate = orderDate;
        this.orderTime = orderTime;
    }

    public OrderDetails(String itemName, String itemPrice, String paymentStatus, String quantity, String orderDate) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.paymentStatus = paymentStatus;
        this.quantity = quantity;
        this.orderDate = orderDate;
    }

    public OrderDetails() {
    }

    public String getUserName() {
        return userName;
    }

    public String getUserAdd() {
        return userAdd;
    }

    public String getUserNumber() {
        return userNumber;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserId() {
        return userId;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public String getOrderTime() {
        return orderTime;
    }
}
