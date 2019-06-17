package com.hrd.springsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {

    @GetMapping("/custom403")
    public String error(){
        return "error403";
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
