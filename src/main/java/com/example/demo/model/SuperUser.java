package com.example.demo.model;

public abstract class SuperUser {

    protected String email;
    protected String password;

    public SuperUser(String email, String password) {
        this.email = email;
        this.password = password;
    }

}
