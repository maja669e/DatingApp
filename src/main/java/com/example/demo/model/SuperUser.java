package com.example.demo.model;

public abstract class SuperUser {

    protected String userName;
    protected String password;

    public SuperUser(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

}
