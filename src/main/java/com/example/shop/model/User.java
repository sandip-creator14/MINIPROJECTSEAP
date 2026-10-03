package com.example.shop.model;

public class User {
    private final String name, email, phone, address, password;
    public User(String name, String email, String phone, String address, String password) {
        this.name = name; this.email = email; this.phone = phone; this.address = address; this.password = password;
    }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getAddress() { return address; }
    public boolean checkPassword(String p) { return password.equals(p); }
}
