package com.example.demo.model;

public abstract class SuperUser {
    protected int ID;
    protected String email;
    protected String password;
    protected String role;
    protected String name;

    public SuperUser(String name ,String email, String password, String role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public abstract String getRole();
    public abstract String setRole();

}
