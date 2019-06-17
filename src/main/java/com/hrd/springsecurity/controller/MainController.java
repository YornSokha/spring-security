package com.hrd.springsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/admin")
    public String admin(){
        return "admin";
    }

    @GetMapping("/admin/profile")
    public String adminProfile(){
        return "admin-profile";
    }

    @GetMapping("/user")
    public String user(){
        return "user";
    }

    @GetMapping("/error403")
    public String error403(){
        return "error403";
    }
}
