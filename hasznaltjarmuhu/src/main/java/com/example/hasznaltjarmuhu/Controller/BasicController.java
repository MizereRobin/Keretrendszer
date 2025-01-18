package com.example.hasznaltjarmuhu.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.hasznaltjarmuhu.Entity.User;
import javax.servlet.http.HttpSession;

import static com.example.hasznaltjarmuhu.Entity.User.AddUser;
import static com.example.hasznaltjarmuhu.db.database.LogOut;
import static com.example.hasznaltjarmuhu.db.database.Login;

@Controller
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
        LogOut(user.getUsername());
        return "home";
    }

    @PostMapping("/submit")
    public String handleFormSubmit(@RequestParam("name") String name, @RequestParam("password") String passwd, HttpSession session) {
        user = AddUser(name, passwd);
        Login(name, passwd);

        // A 'name' változó hozzáadása a session-höz
        session.setAttribute("name", name);

        // Ezután átirányíthatod a felhasználót a főoldalra (például)
        return "redirect:/home";  // vagy a megfelelő oldal, ahová szeretnéd átirányítani
    }


}
