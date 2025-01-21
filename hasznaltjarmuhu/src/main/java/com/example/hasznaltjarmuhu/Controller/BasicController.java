package com.example.hasznaltjarmuhu.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.hasznaltjarmuhu.Entity.User;
import jakarta.servlet.http.HttpSession;


import static com.example.hasznaltjarmuhu.Entity.User.AddUser;
import com.example.hasznaltjarmuhu.db.Database;

@Controller
//@SessionAttributes("name")
public class BasicController {

    private User user;


    @GetMapping("/")
    public String showHomePage() {
        return "home";
    }

    @GetMapping("/home")
    public String ShowHomePage() {
        return "home";
    }

    @GetMapping("/login")
    public String showLoginPage(){
        return "login";
    }
    @GetMapping("/register")
    public String showRegisterPage(){
        return "register";
    }

    @GetMapping("/hirdetesek")
    public String showAdsPage(){
        return "hirdetesek";
    }

    @GetMapping("/hirdet")
    public String showNewAdPage(){
        return "hirdet";
    }

    @GetMapping("/logout")
    public String showLogout(HttpSession session){
        session.invalidate();
        return "home";
    }

    @PostMapping("/submit")
    public String handleFormSubmit(HttpSession session, @RequestParam("name") String name, @RequestParam("password") String passwd) {
        user = AddUser(name, passwd);
        if(login(name, passwd)){System.out.println("Sikeres bejelentkezés");}else{System.out.println("Sikertelen bejelentkezés");}
        session.setAttribute("name", name);
        session.setAttribute("role", "user");


        return "redirect:/home";
    }



}
