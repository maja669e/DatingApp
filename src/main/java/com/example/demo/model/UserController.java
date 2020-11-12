package com.example.demo.model;

//import login.data.DataFacadeImpl;


import java.time.LocalDate;
import java.util.ArrayList;

public class UserController {

    // facade to datasource layer
    private DataFacade facade = null;

    public UserController(DataFacade facade) {
        this.facade = facade;
    }

    public DatingUser datingLogin(String email, String password) throws LoginException {
        return facade.datingLogin(email, password);
    }

    public AdminUser adminLogin(String email, String password) throws LoginException {
        return facade.adminLogin(email, password);
    }

    public DatingUser createDatingUser(String name, String email, String password, LocalDate birthdate) throws LoginException {
        // By default, new users are dating users
        DatingUser datingUser = new DatingUser(name, email, password, birthdate, "datinguser", "","",0);
        facade.createDatingUser(datingUser);
        return datingUser;
    }

    public void editUserInfo(DatingUser datingUser, String name, String email, String password, String gender, String description){
       facade.editUserInfo(datingUser, name, email, password, gender, description);
    }

    public void deleteUser(int userid){
        facade.deleteUser(userid);
    }

    public ArrayList<DatingUser> getAllDatingUsers(SuperUser loginUser){
        return facade.getAllDatingUsers(loginUser);
    }

    public DatingUser updateDatingUser(int userid){
        return facade.updateDatingUser(userid);
    }

    public void sendMessage(String message, int senderid, int receiveid){
        facade.sendMessage(message,senderid,receiveid);
    }
}
