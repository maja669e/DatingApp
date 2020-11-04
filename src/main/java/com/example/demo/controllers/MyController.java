package com.example.demo.controllers;

import com.example.demo.model.AdminUser;
import com.example.demo.model.DatingUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.ArrayList;

@Controller
public class MyController {

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

        DatingUser datingUser1 = new DatingUser(1, "Maja Bijedic", "balablabl", LocalDate.of(1998, 6, 6), 60606113, "Kvinde", "afifdi@mail.dk", "123456");
        datingUsers.add(datingUser1);
        DatingUser datingUser2 = new DatingUser(2, "Maja Bijedic", "balablabl", LocalDate.of(1998, 6, 6), 60606113, "Kvinde", "afifdi@mail.dk", "123456");
        datingUsers.add(datingUser2);
        for (int i = 0; i < datingUsers.size(); i++) {
            model.addAttribute("datingUser", datingUser1); //Temp to test
            model.addAttribute("datingUser", datingUser2); //Temp to test
        }

        AdminUser adminUser = new AdminUser(1, "Phuc Nguyen", "phuc", "1234");
        model.addAttribute("adminUser", adminUser);
        return "admin";
    }


    @GetMapping("/profil")
<<<<<<< HEAD
    public String profile(Model model) {
        DatingUser datingUser = new DatingUser(1, "Nicolai Okkels", "bla bla bla bla", LocalDate.of(1996, 2, 1), 20959300, "Mand", "blablabla@gmail.com", "010296");
=======
    public String profile(Model model){
        DatingUser datingUser = new DatingUser("bla bla", "bla bla bla bla", LocalDate.of(1996,2,1), 88888888, "Mand", "blablabla@gmail.com", "1");
>>>>>>> 5ea56d6e37f8e87b50685b84374b8d3bb3c5aec1
        model.addAttribute("datingUser", datingUser); //Temp to test
        return "profil";
    }

    @GetMapping("/rediger")
    public String edit(){
        return "rediger";
    }
}
