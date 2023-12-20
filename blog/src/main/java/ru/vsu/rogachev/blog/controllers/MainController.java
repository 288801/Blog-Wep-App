package ru.vsu.rogachev.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.vsu.rogachev.blog.security.PersonDetails;
import ru.vsu.rogachev.blog.services.impl.PostServiceImpl;
import ru.vsu.rogachev.blog.services.impl.UserServiceImpl;

import java.security.Principal;


@Controller
public class MainController {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private PostServiceImpl postService;

    @GetMapping("/")
    public String greeting(Model model) {
        model.addAttribute("title", "Главная страница");
        return "greeting";
    }

    @GetMapping("/home")
    public String sayHello() {
        return "home";
    }

    @GetMapping("/profile")
    public String profile(Model model, Principal principal) {
        model.addAttribute("users", userService.findByUsername(principal.getName()));
        model.addAttribute("posts", postService.getUserPosts(principal.getName()));
        return "profile";
    }
}
