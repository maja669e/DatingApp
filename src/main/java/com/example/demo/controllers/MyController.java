package com.example.demo.controllers;

import com.example.demo.data.DataFacadeImpl;
import com.example.demo.model.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@Controller
public class MyController {

    //use case controller (GRASP Controller) - injects concrete facade instance into controller
    private LoginController loginController = new LoginController(new DataFacadeImpl());

    @ExceptionHandler(LoginException.class)
    @GetMapping("/")
    public String login(Model model, Exception exception) {
        model.addAttribute("message", exception.getMessage());
        return "index";
    }

    @GetMapping("/matches")
    public String matches(WebRequest request, Model model) {
        DatingUser datingUser = (DatingUser) request.getAttribute("user", WebRequest.SCOPE_SESSION); //session of user
        CandidateList candidateList = (CandidateList) request.getAttribute("candidateList", WebRequest.SCOPE_SESSION);

        if (datingUser == null) {
            return "redirect:/";
        } else{
            ArrayList<DatingUser> candidates = candidateList.getCandidates();

            model.addAttribute("candidates", candidates);
            model.addAttribute("datingUser", datingUser);
            return "datinguserpages/matches";
        }
    }

    @PostMapping("addToCandidates")
    public String addToCandidates(WebRequest request, RedirectAttributes redirectAttributes) {
        DatingUser datingUser = (DatingUser) request.getAttribute("user", WebRequest.SCOPE_SESSION);
        CandidateList candidateList = (CandidateList) request.getAttribute("candidateList", WebRequest.SCOPE_SESSION);
        int userid = Integer.parseInt(request.getParameter("userid"));

        ArrayList<DatingUser> allDatingUsers = loginController.getAllDatingUsers(datingUser);
        candidateList.addCandidate(allDatingUsers, userid);

        DatingUser candidate = candidateList.getCandidate(allDatingUsers, userid);
        redirectAttributes.addFlashAttribute("message", candidate.getName() + " er nu tilføjet til matches");
        return "redirect:/udforsk";
    }

    @PostMapping("removeCandidate")
    public String removeCandidate(WebRequest request) {
        CandidateList candidateList = (CandidateList) request.getAttribute("candidateList", WebRequest.SCOPE_SESSION);
        int candidateid = Integer.parseInt(request.getParameter("candidateid"));

        ArrayList<DatingUser> candidates = candidateList.getCandidates();
        candidateList.removeCandidate(candidates, candidateid);

        return "redirect:/matches";
    }

    @GetMapping("/admin")
    public String admin(WebRequest request, Model model) {
        AdminUser adminUser = (AdminUser) request.getAttribute("user", WebRequest.SCOPE_SESSION);

        if (adminUser == null) {
            return "redirect:/";
        } else {
            ArrayList<DatingUser> datingUsers = loginController.getAllDatingUsers(adminUser);

            model.addAttribute("datingUsers", datingUsers);
            model.addAttribute("adminUser", adminUser);
            return "adminuserpages/" + adminUser.getRole();
        }
    }

    @GetMapping("/profil")
    public String profile(WebRequest request, Model model) {
        DatingUser datingUser = (DatingUser) request.getAttribute("user", WebRequest.SCOPE_SESSION);

        if (datingUser == null) {
            return "redirect:/";
        } else {
            DatingUser updatedDatingUser = loginController.updateDatingUser(datingUser.getID());

            model.addAttribute("datingUser", updatedDatingUser);

            return "datinguserpages/profil";
        }
    }

    @GetMapping("/rediger")
    public String edit(WebRequest request, Model model) {
        DatingUser datingUser = (DatingUser) request.getAttribute("user", WebRequest.SCOPE_SESSION);

        DatingUser updatedDatingUser = loginController.updateDatingUser(datingUser.getID());

        model.addAttribute("datingUser", updatedDatingUser);

        return "datinguserpages/rediger";
    }

    @PostMapping("redigeretprofil")
    public String postEdit(WebRequest request) throws LoginException {
        DatingUser datingUser = (DatingUser) request.getAttribute("user", WebRequest.SCOPE_SESSION);
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String gender = request.getParameter("gender");
        String decription = request.getParameter("description");
        loginController.editUser(datingUser, name, email, gender, decription);

        return "redirect:/profil";
    }

    @PostMapping("deleteDatingUser")
    public String delete(WebRequest request, RedirectAttributes redirectAttributes) throws LoginException {
        int userid = Integer.parseInt(request.getParameter("userid"));
        loginController.deleteUser(userid);
        redirectAttributes.addFlashAttribute("message", "ID nr: " + userid + " er nu slettet");
        return "redirect:/admin";
    }

    @PostMapping("/login")
    public String loginUser(WebRequest request) throws LoginException {
        //Retrieve values from HTML form via WebRequest
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (!email.equals("admin@gmail.com")) {
            DatingUser datingUser = loginController.datingLogin(email, password);
            CandidateList candidateList = new CandidateList();
            setSessionCandidates(request, candidateList);
            setSessionInfo(request, datingUser);
            return "redirect:/udforsk";
        } else {
            AdminUser adminUser = loginController.adminLogin(email, password);
            setSessionInfo(request, adminUser);
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
            return "redirect:/udforsk";

        } else {
            throw new LoginException("De to kodeord passer ikke");
        }

    }


    @GetMapping("/udforsk")
    public String getDiscover(WebRequest request, Model model) {
        // Retrieve user object from web request (session scope)
        DatingUser datingUser = (DatingUser) request.getAttribute("user", WebRequest.SCOPE_SESSION);

        if (datingUser == null) {
            return "redirect:/";
        } else {
            ArrayList<DatingUser> datingUsers = loginController.getAllDatingUsers(datingUser);

            model.addAttribute("datingUsers", datingUsers);

            return "datinguserpages/udforsk";
        }
    }


    @PostMapping("sendMessage")
    public String sendMessage(WebRequest request) {
        DatingUser datingUser = (DatingUser) request.getAttribute("user", WebRequest.SCOPE_SESSION);
        String message = request.getParameter("message");
        int candidateid = Integer.parseInt(request.getParameter("candidateid"));
        System.out.println(candidateid);

        loginController.sendMessage(message, datingUser.getID(), candidateid);

        return "redirect:/matches";

    }


    @GetMapping("/beskeder")
    public String messages(WebRequest request, Model model) {
        DatingUser datingUser = (DatingUser) request.getAttribute("user", WebRequest.SCOPE_SESSION);
        CandidateList candidateList = (CandidateList) request.getAttribute("candidateList", WebRequest.SCOPE_SESSION);

        if (datingUser == null) {
            return "redirect:/";
        } else {

            int candidateid = Integer.parseInt(request.getParameter("candidateid"));
            ArrayList<DatingUser> candidates = candidateList.getCandidates();
            DatingUser candidate = candidateList.getCandidate(candidates, candidateid);

            model.addAttribute("datingUser", datingUser);
            model.addAttribute("candidate", candidate);
            return "datinguserpages/beskeder";
        }
    }


    private void setSessionInfo(WebRequest request, SuperUser user) {
        request.setAttribute("user", user, WebRequest.SCOPE_SESSION);
    }

    private void setSessionCandidates(WebRequest request, CandidateList candidateList) {
        request.setAttribute("candidateList", candidateList, WebRequest.SCOPE_SESSION);
    }
}
