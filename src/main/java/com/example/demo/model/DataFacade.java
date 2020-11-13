package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

public interface DataFacade {

    public DatingUser datingLogin(String email, String password) throws LoginException;
    public DatingUser createDatingUser(DatingUser datingUser) throws LoginException;
    public AdminUser adminLogin(String email, String password) throws LoginException;
    public List<DatingUser> getAllDatingUsers(SuperUser loginUser);
    public void editUserInfo(DatingUser datingUser,String name, String email,String password, String gender, String description);
    public void deleteUser(int userid);
    public DatingUser updateDatingUser(int userid);
    public void sendMessage(String message, int senderid, int receiveid);
}


