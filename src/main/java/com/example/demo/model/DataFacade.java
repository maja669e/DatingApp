package com.example.demo.model;

import java.util.ArrayList;

public interface DataFacade {

    public DatingUser datingLogin(String email, String password) throws LoginException;
    public DatingUser createDatingUser(DatingUser datingUser) throws LoginException;
    public AdminUser adminLogin(String email, String password) throws LoginException;
    public ArrayList<DatingUser> getAllUsers();
}


