package com.example.demo.model;

//import login.data.DataFacadeImpl;


import java.time.LocalDate;
import java.util.ArrayList;

public class LoginController {

    // facade to datasource layer
    private DataFacade facade = null;

    public LoginController(DataFacade facade) {
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
        DatingUser datingUser = new DatingUser(name, email, password, birthdate, "datinguser", "","");
        facade.createDatingUser(datingUser);
        return datingUser;
    }


}
