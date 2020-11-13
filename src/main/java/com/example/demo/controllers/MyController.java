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
import java.util.List;

@Controller
public class MyController {

    //use case controller (GRASP Controller) - injects concrete facade instance into controller
    private UserController userController = new UserController(new DataFacadeImpl());

    @ExceptionHandler(LoginException.class)
    @GetMapping("/")
    public String login(Model model, Exception exception) {
        model.addAttribute("message", exception.getMessage());
        return "index";
    }

    @GetMapping("/kandidater")
    public String candidates(WebRequest request, Model model) {
        // Retrieve user object from web request (session scope)
        DatingUser datingUser = (DatingUser) request.getAttribute("user", WebRequest.SCOPE_SESSION); //session of user
        CandidateList candidateList = (CandidateList) request.getAttribute("candidateList", WebRequest.SCOPE_SESSION);

        //Checks if user is logged in
        if (datingUser == null) {
            return "redirect:/";
        } else {
            List<DatingUser> candidates = candidateList.getCandidates();

            model.addAttribute("candidates", candidates);
            model.addAttribute("datingUser", datingUser);

            return "datinguserpages/kandidater";
        }
    }

    @PostMapping("addToCandidates")
    public String addToCandidates(WebRequest request, RedirectAttributes redirectAttributes) {
        // Retrieve user object from web request (session scope)
        DatingUser datingUser = (DatingUser) request.getAttribute("user", WebRequest.SCOPE_SESSION);
        CandidateList candidateList = (CandidateList) request.getAttribute("candidateList", WebRequest.SCOPE_SESSION);
        //Retrieve values from HTML form via WebRequest
        int candidateid = Integer.parseInt(request.getParameter("candidateid"));

        //Get all datingusers, excludes the logged in user
        List<DatingUser> allDatingUsers = userController.getAllDatingUsers(datingUser);
        for (DatingUser candidate : allDatingUsers) {
            if (candidate.getID() == candidateid) {
                candidateList.addCandidate(candidate);
            }
        }
        DatingUser candidate = candidateList.getCandidate(candidateid);

        //returns a message, when candidate is added
        redirectAttributes.addFlashAttribute("message", candidate.getName() + " er nu tilføjet til kandidater");
        return "redirect:/udforsk";
    }

    @PostMapping("removeCandidate")
    public String removeCandidate(WebRequest request) {
        // Retrieve user object from web request (session scope)
        CandidateList candidateList = (CandidateList) request.getAttribute("candidateList", WebRequest.SCOPE_SESSION);
        //Retrieve values from HTML form via WebRequest
        int candidateid = Integer.parseInt(request.getParameter("candidateid"));

        List<DatingUser> candidates = candidateList.getCandidates();
        candidateList.removeCandidate(candidates, candidateid);

        return "redirect:/kandidater";
    }

    @GetMapping("/admin")
    public String admin(WebRequest request, Model model) {
        // Retrieve user object from web request (session scope)
        AdminUser adminUser = (AdminUser) request.getAttribute("user", WebRequest.SCOPE_SESSION);

        //Checks if user is logged in
        if (adminUser == null) {
            return "redirect:/";
        } else {
            //Get all datingusers, excludes the logged in user
            List<DatingUser> datingUsers = userController.getAllDatingUsers(adminUser);

            model.addAttribute("datingUsers", datingUsers);
            model.addAttribute("adminUser", adminUser);
            return "adminuserpages/" + adminUser.getRole();
        }
    }

    @GetMapping("/profil")
    public String profile(WebRequest request, Model model) {
        // Retrieve user object from web request (session scope)
        DatingUser datingUser = (DatingUser) request.getAttribute("user", WebRequest.SCOPE_SESSION);

        //Checks if user is logged in
        if (datingUser == null) {
            return "redirect:/";
        } else {
            //update datinguser info
            DatingUser updatedDatingUser = userController.updateDatingUser(datingUser.getID());

            model.addAttribute("datingUser", updatedDatingUser);

            return "datinguserpages/profil";
        }
    }

    @GetMapping("/rediger")
    public String edit(WebRequest request, Model model) {
        // Retrieve user object from web request (session scope)
        DatingUser datingUser = (DatingUser) request.getAttribute("user", WebRequest.SCOPE_SESSION);

        //update datinguser info
        DatingUser updatedDatingUser = userController.updateDatingUser(datingUser.getID());

        model.addAttribute("datingUser", updatedDatingUser);

        return "datinguserpages/rediger";
    }

    @PostMapping("redigeretprofil")
    public String postEdit(WebRequest request) {
        // Retrieve user object from web request (session scope)
        DatingUser datingUser = (DatingUser) request.getAttribute("user", WebRequest.SCOPE_SESSION);
        //Retrieve values from HTML form via WebRequest
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String gender = request.getParameter("gender");
        String decription = request.getParameter("description");

        userController.editUserInfo(datingUser, name, email, password, gender, decription);

        return "redirect:/profil";
    }

    @PostMapping("deleteDatingUser")
    public String delete(WebRequest request, RedirectAttributes redirectAttributes) {
        //Retrieve values from HTML form via WebRequest
        int userid = Integer.parseInt(request.getParameter("userid"));

        userController.deleteUser(userid);

        //returns a message, when datinguser is removed
        redirectAttributes.addFlashAttribute("message", "ID nr: " + userid + " er nu slettet");

        return "redirect:/admin";
    }

    @PostMapping("/login")
    public String loginUser(WebRequest request) throws LoginException {
        //Retrieve values from HTML form via WebRequest
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (!email.equals("admin@gmail.com")) {
            DatingUser datingUser = userController.datingLogin(email, password);
            CandidateList candidateList = new CandidateList(); //When user logs in candidatelist is empty (database could be used instead)
            setSessionCandidates(request, candidateList);
            setSessionInfo(request, datingUser);
            return "redirect:/udforsk";
        } else {
            AdminUser adminUser = userController.adminLogin(email, password);
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
            DatingUser datingUser = userController.createDatingUser(name, email, password1, birthDate);
            CandidateList candidateList = new CandidateList(); //When user logs in candidatelist is empty (database could be used instead)
            setSessionCandidates(request, candidateList);
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

        //Checks if user is logged in
        if (datingUser == null) {
            return "redirect:/";
        } else {
            //Get all datingusers, excludes the logged in user
            List<DatingUser> datingUsers = userController.getAllDatingUsers(datingUser);

            model.addAttribute("datingUsers", datingUsers);

            return "datinguserpages/udforsk";
        }
    }


    @PostMapping("sendMessage")
    public String sendMessage(WebRequest request) {
        // Retrieve user object from web request (session scope)
        DatingUser datingUser = (DatingUser) request.getAttribute("user", WebRequest.SCOPE_SESSION);
        //Retrieve values from HTML form via WebRequest
        String message = request.getParameter("message");
        int candidateid = Integer.parseInt(request.getParameter("candidateid"));

        userController.sendMessage(message, datingUser.getID(), candidateid);

        return "redirect:/kandidater";

    }


    @GetMapping("/beskeder")
    public String messages(WebRequest request, Model model) {
        // Retrieve user object from web request (session scope)
        DatingUser datingUser = (DatingUser) request.getAttribute("user", WebRequest.SCOPE_SESSION);
        CandidateList candidateList = (CandidateList) request.getAttribute("candidateList", WebRequest.SCOPE_SESSION);

        //Checks if user is logged in
        if (datingUser == null) {
            return "redirect:/";
        } else {
            // Retrieve user object from web request (session scope)
            int candidateid = Integer.parseInt(request.getParameter("candidateid"));

            //sends message through candidates
            DatingUser candidate = candidateList.getCandidate(candidateid);

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
