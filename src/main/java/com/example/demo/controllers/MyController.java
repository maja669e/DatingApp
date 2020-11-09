package com.example.demo.controllers;

import com.example.demo.data.DataFacadeImpl;
import com.example.demo.data.UserMapper;
import com.example.demo.model.*;
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
        DatingUser datingUser = (DatingUser) request.getAttribute("datingUser", WebRequest.SCOPE_SESSION);

        if (datingUser != null) {
            return "/matches";
        } else
            return "redirect:/";
    }

    @GetMapping("/admin")
    public String admin(WebRequest request, Model model) {
        //AdminUser adminUser = (AdminUser) request.getAttribute("adminUser", WebRequest.SCOPE_SESSION);

        ArrayList<DatingUser> datingUsers = loginController.getAllDatingUsers();

        System.out.println("test: " + datingUsers);

        model.addAttribute("datingUsers", datingUsers);

        AdminUser adminUser2 = new AdminUser("adminuser@gmail.com", "1", "admin");
        model.addAttribute("adminUser", adminUser2);


        return "admin";
    }

    @GetMapping("/profil")
    public String profile(WebRequest request, Model model) {
        //DatingUser datingUser = (DatingUser) request.getAttribute("datingUser", WebRequest.SCOPE_SESSION);
        DatingUser datingUser = new DatingUser("test", "test@test.dk", "1", LocalDate.of(1996,2,1),"datinguser", "test test test", "test");
        datingUser.setID(7);
        model.addAttribute("datingUser", datingUser);
        System.out.println(datingUser);

        if (datingUser != null) {
            return "/profil";
        } else
            return "redirect:/";
        
    }

    @GetMapping("/rediger")
    public String edit() {
        //DatingUser datingUser = (DatingUser) request.getAttribute("datingUser", WebRequest.SCOPE_SESSION);
        DatingUser datingUser = new DatingUser("test", "test@test.dk", "1", LocalDate.of(1996,2,1),"datinguser", "test test test", "test");
        return "rediger";
    }

    @PostMapping("/login")
    public String loginUser(WebRequest request) throws LoginException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (!email.equals("admin@gmail.com")) {
            DatingUser datingUser = loginController.datingLogin(email, password);
            setSessionInfo(request, datingUser);
            System.out.println(datingUser.toString());
            return "redirect:/udforsk";
        } else {
            AdminUser adminUser = loginController.adminLogin(email, password);
            setSessionInfo(request, adminUser);
            System.out.println(adminUser.toString());
            return "redirect:/" + adminUser.getRole();
        }
    }

    @PostMapping("/register")
    public String createUser(WebRequest request) throws LoginException {
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        //String birthdate = request.getParameter("birthdate");
        LocalDate birthdate = LocalDate.of(1996, 2, 2);
        String password1 = request.getParameter("password1");
        String password2 = request.getParameter("password2");

        if (password1.equals(password2)) {
            DatingUser datingUser = loginController.createDatingUser(name, email, password1, birthdate);
            setSessionInfo(request, datingUser);
            return "/udforsk";

        } else {
            throw new LoginException("De to kodeord passer ikke");
        }

    }


    @GetMapping("/udforsk")
    public String getDiscover(WebRequest request) {
        DatingUser datingUser = (DatingUser) request.getAttribute("datingUser", WebRequest.SCOPE_SESSION);

        System.out.println(datingUser);
/*
        if (datingUser != null) {
            return "/udforsk";
        } else
            return "redirect:/";

 */

        return "/udforsk";
    }


    private void setSessionInfo(WebRequest request, SuperUser user) {
        request.setAttribute("user", user, WebRequest.SCOPE_SESSION);
    }


    @ExceptionHandler(LoginException.class)
    @PostMapping("/fejlside")
    public String anotherError(Model model, Exception exception) {
        model.addAttribute("message",exception.getMessage());
        return "/fejlside";
    }
}
