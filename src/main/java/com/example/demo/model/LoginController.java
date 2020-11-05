package com.example.demo.model;

//import login.data.DataFacadeImpl;


public class LoginController {

    // facade to datasource layer
    private DataFacade facade = null;

    public LoginController(DataFacade facade) {
        this.facade = facade;
    }

    public DatingUser login(String email, String password) throws LoginException {
        return facade.datingLogin(email, password);
    }

    public DatingUser createUser(String name, /*LocalDate birthdate,*/ String email, String password) throws LoginException {
        // By default, new users are customers
        DatingUser datingUser = new DatingUser(name, /*birthdate,*/ email, password);
        facade.createDatingUser(datingUser);
        return datingUser;
    }
}
