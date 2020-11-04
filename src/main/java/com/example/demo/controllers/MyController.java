package com.example.demo.controllers;

import com.example.demo.model.DatingUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;

@Controller
public class MyController {

    @GetMapping("/")
    public String login(){
        return "index";
    }

    @GetMapping("/matches")
    public String matches(){
        return "matches";
    }

    @GetMapping("/udforsk")
    public String explore(){
        return "udforsk";
    }

    @GetMapping("/admin")
    public String admin(){
        return "admin";
    }

    @GetMapping("/profil")
    public String profile(Model model){
        DatingUser datingUser = new DatingUser("bla bla", "bla bla bla bla", LocalDate.of(1996,2,1), 88888888, "Mand", "blablabla@gmail.com", "1");
        model.addAttribute("datingUser", datingUser); //Temp to test
        return "profil";
    }

    @GetMapping("/rediger")
    public String edit(){
        return "rediger";
    }
}
