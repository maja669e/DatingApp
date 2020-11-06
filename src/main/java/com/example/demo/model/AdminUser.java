package com.example.demo.model;

public class AdminUser extends SuperUser {
    private String name;

    public AdminUser(String name, String email, String password) {
        super(email, password);
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
