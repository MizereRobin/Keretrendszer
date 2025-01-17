package com.example.hasznaltjarmuhu.Controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BasicController {

    private User user;

    /*
    @GetMapping("/")
    public String showHomePage() {
        return "home";
    }
     */

    @GetMapping("/login")
    public String showLoginPage(){
        return "login";
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
    public String showLogout(){
        Logout(user.name, user.password);
        return "home";
    }

    @PostMapping("/submit")
    public void handleFormSubmit(@RequestParam("name") String name,
                                   @RequestParam("password") String password) {
        user = new User(name, password);
        Login(name, password);
        ShowHomePage();
    }


}
