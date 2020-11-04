package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
    public String profile(){
        return "profil";
    }
}
