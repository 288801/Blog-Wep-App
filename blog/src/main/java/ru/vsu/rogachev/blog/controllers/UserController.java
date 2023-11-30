package ru.vsu.rogachev.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/home")
    public String sayHello() {
        return "home";
    }

    @GetMapping("/profile")
    public String profile(Model model) {
        return "profile";
    }
}
