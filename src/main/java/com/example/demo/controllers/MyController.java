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
        DatingUser datingUser2 = new DatingUser(2, "Nicolai Okkels", "balablabl", LocalDate.of(1996, 2, 1), 60606113, "Kvinde", "1300ji@mail.dk", "123456");
        DatingUser datingUser3 = new DatingUser(3, "Bob Bobsen", "balablabl", LocalDate.of(1996, 2, 1), 60606113, "Kvinde", "bob@mail.dk", "123456");
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
        DatingUser datingUser = new DatingUser(1, "bla bla", "bla bla bla bla", LocalDate.of(1996, 2, 1), 88888888, "Mand", "blablabla@gmail.com", "1");
        model.addAttribute("datingUser", datingUser); //Temp to test
        return "profil";
    }

    @GetMapping("/rediger")
    public String edit() {
        return "rediger";
    }
}
