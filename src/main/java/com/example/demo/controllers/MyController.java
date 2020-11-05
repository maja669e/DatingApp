package com.example.demo.controllers;

import com.example.demo.data.DataFacadeImpl;
import com.example.demo.model.AdminUser;
import com.example.demo.model.DatingUser;
import com.example.demo.model.LoginController;
import com.example.demo.model.LoginException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;


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
    public String matches() {
        return "matches";
    }

    @GetMapping("/udforsk")
    public String explore() {
        return "udforsk";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        ArrayList<DatingUser> datingUsers = new ArrayList<>();
        DatingUser datingUser1 = new DatingUser("Maja Bijedic",/*LocalDate.of(1998, 6, 6),*/ "afifdi@mail.dk", "123456");
        DatingUser datingUser2 = new DatingUser("Nicolai Okkels",/* LocalDate.of(1996, 2, 1),*/ "1300ji@mail.dk", "123456");
        DatingUser datingUser3 = new DatingUser("Bob Bobsen",/* LocalDate.of(1996, 2, 1), */"bob@mail.dk", "123456");
        datingUsers.add(datingUser1);
        datingUsers.add(datingUser2);
        datingUsers.add(datingUser3);
        for (int i = 0; i < datingUsers.size(); i++) {
            datingUsers.get(i).getID();
            datingUsers.get(i).getName();
            datingUsers.get(i).getEmail();
            model.addAttribute("datingUsers", datingUsers); //Temp to test
        }

        AdminUser adminUser = new AdminUser(1, "Phuc Nguyen", "phuc", "1234");
        model.addAttribute("adminUser", adminUser);
        return "admin";
    }


    @GetMapping("/profil")
    public String profile(Model model) {
        DatingUser datingUser = new DatingUser("bla bla", /*LocalDate.of(1996, 2, 1),*/ "blablabla@gmail.com", "1");
        model.addAttribute("datingUser", datingUser); //Temp to test
        return "profil";
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

        DatingUser datingUser = loginController.login(email, password);
        setSessionInfo(request, datingUser);

        return "/udforsk";
    }


    @PostMapping("/registrer")
    public String createUser(WebRequest request) throws LoginException {
        //Retrieve values from HTML form via WebRequest
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        //LocalDate birthdate = request.getParameter("birthdate");
        String password1 = request.getParameter("password1");
        String password2 = request.getParameter("password2");

        // If passwords match, work + data is delegated to logic controller
        if (password1.equals(password2)) {
            DatingUser datingUser = loginController.createUser(name, /*birthdate, */ email, password1);
            setSessionInfo(request, datingUser);
            return "/udforsk";

        } else {
            throw new LoginException("De to kodeord passer ikke");
        }

    }


    @GetMapping("/udforsk")
    public String getDiscover(WebRequest request) {
        // Retrieve user object from web request (session scope)
        DatingUser datingUser = (DatingUser) request.getAttribute("datingUSer", WebRequest.SCOPE_SESSION);

        // If user object is found on session, i.e. user is logged in, she/he can see home page
        if (datingUser != null) {
            return "/udforsk";
        }
        else
            return "redirect:/";
    }


    private void setSessionInfo(WebRequest request, DatingUser datingUser) {
        request.setAttribute("user", datingUser, WebRequest.SCOPE_SESSION);
    }


    @ExceptionHandler(Exception.class)
    public String anotherError(Model model, Exception exception) {
        model.addAttribute("message",exception.getMessage());
        return "/fejlside";
    }
}
