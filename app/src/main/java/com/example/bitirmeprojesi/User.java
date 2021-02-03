package com.example.bitirmeprojesi;

public class User {
    private String yorum;


    public User(String yorum) {
        this.yorum = yorum;
    }
    public User() {

    }

    public String getYorum() {
        return yorum;
    }

    public void setYorum(String yorum) {
        this.yorum = yorum;
    }
}
