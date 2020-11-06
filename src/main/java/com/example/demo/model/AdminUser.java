package com.example.demo.model;

public class AdminUser extends SuperUser {
    public AdminUser(String name, String email, String password, String role) {
        super(name, email, password, role);
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

    @Override
    public String getRole() {
        return this.role;
    }

    @Override
    public String setRole() {
        return "admin";
    }
}
