package com.example.demo.controllers;

import com.example.demo.data.DataFacadeImpl;
import com.example.demo.model.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
        DatingUser datingUser = (DatingUser) request.getAttribute("user", WebRequest.SCOPE_SESSION);

        if (datingUser != null) {
            return "datinguserpages/matches";
        } else
            return "redirect:/";
    }

    @GetMapping("/admin")
    public String admin(WebRequest request, Model model) {
        AdminUser adminUser = (AdminUser) request.getAttribute("user", WebRequest.SCOPE_SESSION);

        ArrayList<DatingUser> datingUsers = loginController.getAllDatingUsers();

        model.addAttribute("datingUsers", datingUsers);

        model.addAttribute("adminUser", adminUser);

        if (adminUser != null) {
            return "adminuserpages/" + adminUser.getRole();
        } else
            return "redirect:/";
    }

    @GetMapping("/profil")
    public String profile(WebRequest request, Model model) {
        DatingUser datingUser = (DatingUser) request.getAttribute("user", WebRequest.SCOPE_SESSION);
        model.addAttribute("datingUser", datingUser);

        if (datingUser != null) {
            return "datinguserpages/profil";
        } else
            return "redirect:/";
        
    }

    @GetMapping("/rediger")
    public String edit(WebRequest request) {
        DatingUser datingUser = (DatingUser) request.getAttribute("user", WebRequest.SCOPE_SESSION);
        if (datingUser != null) {
            return "datinguserpages/rediger";
        } else
            return "redirect:/";
    }

    @PostMapping("/login")
    public String loginUser(WebRequest request) throws LoginException {
        //Retrieve values from HTML form via WebRequest
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (!email.equals("admin@gmail.com")) {
            DatingUser datingUser = loginController.datingLogin(email, password);
            setSessionInfo(request, datingUser);
            System.out.println(datingUser.toString());
            return "datinguserpages/udforsk";
        } else {
            AdminUser adminUser = loginController.adminLogin(email, password);
            setSessionInfo(request, adminUser);
            System.out.println(adminUser.toString());
            return "redirect:/" + adminUser.getRole();
        }

    }

    @PostMapping("/register")
    public String createUser(WebRequest request) throws LoginException {
        //Retrieve values from HTML form via WebRequest
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String temp = request.getParameter("birthdate");
        //convert from String to LocalDate
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate birthDate = LocalDate.parse(temp, formatter);

        String password1 = request.getParameter("password1");
        String password2 = request.getParameter("password2");

        // If passwords match, work + data is delegated to logic controller
        if (password1.equals(password2)) {
            DatingUser datingUser = loginController.createDatingUser(name, email, password1, birthDate);
            setSessionInfo(request, datingUser);
            return "/udforsk";

        } else {
            throw new LoginException("De to kodeord passer ikke");
        }

    }


    @GetMapping("/udforsk")
    public String getDiscover(WebRequest request) {
        // Retrieve user object from web request (session scope)
        DatingUser datingUser = (DatingUser) request.getAttribute("user", WebRequest.SCOPE_SESSION);

        System.out.println(datingUser);

        if (datingUser != null) {
            return "datinguserpages/udforsk";
        } else
            return "redirect:/";

    }


    private void setSessionInfo(WebRequest request, SuperUser user) {
        request.setAttribute("user", user, WebRequest.SCOPE_SESSION);
        request.setAttribute("role", user.getRole(), WebRequest.SCOPE_SESSION);
    }


    @ExceptionHandler(LoginException.class)
    @PostMapping("/fejlside")
    public String anotherError(Model model, Exception exception) {
        model.addAttribute("message",exception.getMessage());
        return "/fejlside";
    }
}
