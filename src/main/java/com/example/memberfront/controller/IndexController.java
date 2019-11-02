package com.example.memberfront.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
@Slf4j
public class IndexController {
    @GetMapping("/")
    public String index(Model model, Principal principal) {
        if (principal == null) {
            model.addAttribute("isLogin", false);
            model.addAttribute("username", "not logged in");
        } else {
            model.addAttribute("isLogin", true);
            model.addAttribute("username", principal.getName());
        }
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
