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
    public String matches() {
        return "matches";
    }

   /* @GetMapping("/udforsk")
    public String explore() {
        return "udforsk";
    }*/

    @GetMapping("/admin")
    public String admin(WebRequest request, Model model) {
        // AdminUser adminUser = (AdminUser) request.getAttribute("adminUser", WebRequest.SCOPE_SESSION);
        UserMapper userMapper = new UserMapper();
        DatingUser datingUser = userMapper.getAllUsers();
        ArrayList<DatingUser> list = new ArrayList<>();
        list.add(datingUser);
        for (int i = 0; i < list.size(); i++) {
            list.get(i).getID();
            list.get(i).getEmail();
        }
        model.addAttribute("datingUsers", list);


        AdminUser adminUser = new AdminUser("admin@gmail.com", "1", "admin");
        model.addAttribute("adminUser", adminUser);
        return "admin";
    }


    @GetMapping("/profil")
    public String profile(Model model) {
        DatingUser datingUser = new DatingUser("bla bla", "blablabla@gmail.com", "1", LocalDate.of(1996, 2, 1), "Dating user");
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

        DatingUser datingUser = loginController.datingLogin(email, password);
        AdminUser adminUser = loginController.adminLogin(email, password);

        if (!datingUser.getRole().equals("admin")) {
            //   setSessionInfo(request, datingUser);
            return "redirect:udforsk";
        } else {
            // setSessionInfo(request, adminUser);
            return "redirect:admin";
        }
    }

   /* @GetMapping("/test")
    @ResponseBody
    public String test() throws LoginException{
        LocalDate birthdate = LocalDate.of(1998, 6, 6);
        DatingUser datingUser = loginController.createUser("Maja", birthdate, "hej@live.dk", "hej");
        return datingUser.toString();

    }*/

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
            /*
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd/");
            LocalDate localDate = LocalDate.parse(birthdate, formatter);

             */
            DatingUser datingUser = loginController.createDatingUser(name, email, password1, birthdate);
            // setSessionInfo(request, datingUser);
            return "/udforsk";

        } else {
            throw new LoginException("De to kodeord passer ikke");
        }

    }


    @GetMapping("/udforsk")
    public String getDiscover(WebRequest request) {
        // Retrieve user object from web request (session scope)
        // DatingUser datingUser = (DatingUser) request.getAttribute("datingUser", WebRequest.SCOPE_SESSION);

        // If user object is found on session, i.e. user is logged in, she/he can see home page
      /* DatingUser datingUser = null;
        if (datingUser != null) {
            return "redirect:udforsk";
        }
        else
            return "redirect:/";*/
        return "udforsk";
    }

/*
    private void setSessionInfo(WebRequest request, SuperUser user) {
        request.setAttribute("user", user, WebRequest.SCOPE_SESSION);
        request.setAttribute("role", user.getRole(), WebRequest.SCOPE_SESSION);
    }*/


    /*@ExceptionHandler(Exception.class)
    public String anotherError(Model model, Exception exception) {
        model.addAttribute("message",exception.getMessage());
        return "fejlside";
    }*/
}
