package com.example.firebase;


public class User {
    public int id;
    public String email;
    public String urlImage;

    public User(String email, String urlImage) {
        this.email = email;
        this.urlImage = urlImage;
    }

    public User() {
    }
}
