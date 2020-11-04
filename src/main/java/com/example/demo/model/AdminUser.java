package com.example.demo.model;

public class AdminUser extends SuperUser {
    private int ID;
    private String name;

    public AdminUser(int ID, String name, String userName, String password) {
        super(userName, password);
        this.ID = ID;
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
