package com.example.demo.model;

public abstract class BasicUser {

    protected String userName;
    protected String password;


    public BasicUser(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

}
