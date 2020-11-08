package com.example.demo.controllers;

import com.example.demo.data.DataFacadeImpl;
import com.example.demo.data.UserMapper;
import com.example.demo.model.*;
import org.apache.catalina.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;


import java.time.LocalDate;
import java.util.ArrayList;

@Controller
public class MyController {

    //use case controller (GRASP Controller) - injects concrete facade instance into controller
    private LoginController loginController = new LoginController(new DataFacadeImpl());

    @GetMapping("/")
    public String login() {
        return "index";
    }

    @GetMapping("/matches")
    public String matches(WebRequest request) {
        SuperUser user = (DatingUser) request.getAttribute("datingUser", WebRequest.SCOPE_SESSION);

        if (user != null) {
            return "/matches";
        } else
            return "redirect:/";
    }

    @GetMapping("/admin")
    public String admin(WebRequest request, Model model) {
        AdminUser adminUser = (AdminUser) request.getAttribute("adminUser", WebRequest.SCOPE_SESSION);
        UserMapper userMapper = new UserMapper();

        ArrayList<DatingUser> datingUsers = new ArrayList<>();

        datingUsers.add(userMapper.getAllUsers());

        model.addAttribute("datingUsers", datingUsers);

       // AdminUser adminUser = new AdminUser("admin@gmail.com", "1", "admin");

        model.addAttribute("adminUser", adminUser);
        return "admin";
    }

    @GetMapping("/profil")
    public String profile(WebRequest request, Model model) {
        DatingUser datingUser = (DatingUser) request.getAttribute("datingUser", WebRequest.SCOPE_SESSION);
        model.addAttribute("datingUser", datingUser);

        System.out.println(datingUser);
        if (datingUser != null) {
            return "/profil";
        } else
            return "redirect:/";
    }

    @GetMapping("/rediger")
    public String edit() {
        return "rediger";
    }

    @PostMapping("/login")
    public String loginUser(WebRequest request) throws LoginException {
        //Retrieve values from HTML form via WebRequest
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (!email.equals("admin@gmail.com")) {
            DatingUser datingUser = loginController.datingLogin(email, password);
            setDatingSessionInfo(request, datingUser);
            System.out.println(datingUser.toString());
            return "/udforsk";
        } else {
            AdminUser adminUser = loginController.adminLogin(email, password);
            setAdminSessionInfo(request, adminUser);
            System.out.println(adminUser.toString());
            return "/" + adminUser.getRole();
        }
    }

    @PostMapping("/register")
    public String createUser(WebRequest request) throws LoginException {
        //Retrieve values from HTML form via WebRequest
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        //String birthdate = request.getParameter("birthdate");
        LocalDate birthdate = LocalDate.of(1996, 2, 2);
        String password1 = request.getParameter("password1");
        String password2 = request.getParameter("password2");

        // If passwords match, work + data is delegated to logic controller
        if (password1.equals(password2)) {
            DatingUser datingUser = loginController.createDatingUser(name, email, password1, birthdate);
            setDatingSessionInfo(request, datingUser);
            return "/udforsk";

        } else {
            throw new LoginException("De to kodeord passer ikke");
        }

    }


    @GetMapping("/udforsk")
    public String getDiscover(WebRequest request) {
        // Retrieve user object from web request (session scope)
        DatingUser datingUser = (DatingUser) request.getAttribute("datingUser", WebRequest.SCOPE_SESSION);

        // If user object is found on session, i.e. user is logged in, she/he can see home page
        if (datingUser != null) {
            return "/udforsk";
        } else
            return "redirect:/";
    }


    private void setDatingSessionInfo(WebRequest request, DatingUser datingUser) {
        request.setAttribute("datinguser", datingUser, WebRequest.SCOPE_SESSION);
        request.setAttribute("role", datingUser.getRole(), WebRequest.SCOPE_SESSION);
    }

    private void setAdminSessionInfo(WebRequest request, AdminUser adminUser) {
        request.setAttribute("adminuser", adminUser, WebRequest.SCOPE_SESSION);
        request.setAttribute("role", adminUser.getRole(), WebRequest.SCOPE_SESSION);
    }


    /*@ExceptionHandler(Exception.class)
    public String anotherError(Model model, Exception exception) {
        model.addAttribute("message",exception.getMessage());
        return "fejlside";
    }*/
}
