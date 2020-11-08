package com.example.demo.model;

public class AdminUser extends SuperUser {
    public AdminUser(String email, String password, String role) {
        super(email, password, role);
    }

    public String getEmail(){
        return email;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @Override
    public String getRole() {
        return this.role;
    }

    @Override
    public String setRole() {
        return "admin";
    }
    @Override
    public void setEmail(String email) {
        this.email = email;
    }
}
