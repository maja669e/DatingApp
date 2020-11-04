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
    public ModelAndView profile(ModelAndView modelAndView){
        modelAndView.addObject("datingUser", new DatingUser("Nicolai Okkels", "bla bla bla bla", LocalDate.of(1996,2,1), 20959300, "Mand", "heteroseksuel", "blablabla@gmail.com", "010296")); //Temp to test
        return modelAndView;
    }
}
