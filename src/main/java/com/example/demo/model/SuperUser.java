package com.example.demo.model;

public abstract class SuperUser {
    protected int ID;

    protected String email;
    protected String password;
    protected String role;

    public SuperUser(String email, String password, String role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public SuperUser(){

    }

    public abstract String getRole();
    public abstract String setRole();

    public abstract void setEmail(String email);
}
