package com.baba.grocerystore.Model;

public class UserDetails {
    String name, email, number, address, city;

    public UserDetails(String name, String email, String number, String address, String city) {
        this.name = name;
        this.email = email;
        this.number = number;
        this.address = address;
        this.city = city;
    }

    public UserDetails() {
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getNumber() {
        return number;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }
}
