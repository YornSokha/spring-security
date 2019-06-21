package com.hrd.springsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SecurityController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/custom403")
    public String error() {
        return "error403";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }


    @GetMapping("/bc/{password}")
    @ResponseBody
    public String getPassword(@PathVariable String password) {
        return passwordEncoder.encode(password);
    }
}
