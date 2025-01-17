package com.example.hasznaltjarmuhu.Controller;

@Controller
public class Controller {

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
}
