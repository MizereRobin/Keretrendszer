package com.example.hasznaltjarmuhu.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;

import com.example.hasznaltjarmuhu.db.Database;
import com.example.hasznaltjarmuhu.Entity.User;

import java.util.*;

@Controller
public class BasicController {

    @GetMapping({"/", "/home"})
    public String showHomePage() {
        return "home";
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }

    @PostMapping("/register")
    public String handleRegistration(@RequestParam("name") String name,
                                     @RequestParam("password") String password,
                                     Model model) {
        if (Database.register(name, password)) {
            return "redirect:/login?registered=true";
        } else {
            model.addAttribute("error", "A regisztráció sikertelen. A felhasználónév már foglalt lehet.");
            return "register";
        }
    }


    @GetMapping("/hirdetesek")
    public String getHirdetesek(Model model) {
        model.addAttribute("ads", Database.getAllAds());
        return "hirdetesek";
    }

    @PostMapping("/hirdet")
    public String postAd(@RequestParam String manufacturer,
                         @RequestParam String carModel,
                         @RequestParam int year,
                         @RequestParam double price,
                         HttpSession session,
                         Model model) {

        String userName = (String) session.getAttribute("name");
        if (userName == null) {
            model.addAttribute("error", "Kérjük jelentkezzen be a hirdetés feladásához.");
            return "redirect:/login";
        }

        int userId = Database.getUserIdByName(userName);
        if (userId == -1) {
            model.addAttribute("error", "Nem található felhasználói azonosító.");
            return "hirdet";
        }

        if (!Database.postAd(manufacturer, carModel, year, price, userId)) {
            model.addAttribute("error", "Sikertelen hirdetésfeladás. Kérjük próbálja újra.");
            return "hirdet";
        }

        return "redirect:/hirdetesek";
    }

    @GetMapping("/logout")
    public String showLogout(HttpSession session) {
        session.invalidate();
        return "home";
    }

    @PostMapping("/login")
    public String handleFormSubmit(HttpSession session,
                                   @RequestParam("name") String name,
                                   @RequestParam("password") String passwd) {
        //User user = AddUser(name, passwd);
        if (Database.login(name, passwd, session)) {
            System.out.println("Sikeres bejelentkezés");
            return "home";
        } else {
            System.out.println("Sikertelen bejelentkezés");
            return "login";
        }
    }



    @GetMapping("/users")
    public String listUsers(Model model) {
        List<Map<String, Object>> users = Database.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

}