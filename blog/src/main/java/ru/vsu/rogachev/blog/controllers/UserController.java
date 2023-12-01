package ru.vsu.rogachev.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.vsu.rogachev.blog.entities.Post;
import ru.vsu.rogachev.blog.entities.User;
import ru.vsu.rogachev.blog.repositories.PostRepository;
import ru.vsu.rogachev.blog.services.impl.PostServiceImpl;
import ru.vsu.rogachev.blog.services.impl.UserServiceImpl;

import java.util.Date;

@Controller
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

//    @GetMapping("/home")
//    public String sayHello() {
//        return "home";
//    }
//
//    @GetMapping("/profile")
//    public String profile(Model model) {
//        return "profile";
//    }

    @GetMapping("")
    public String users(Model model) {
        Iterable<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/add")
    public String add(Model model) {
        return "addUser";
    }

    @PostMapping("/add")
    public String addPost(@RequestParam String nickname, @RequestParam String imageUrl,
                          @RequestParam String phoneNumber, @RequestParam String email,
                          @RequestParam String name, @RequestParam String surname,
                          @RequestParam String password, Model model) {
        userService.create(nickname, imageUrl, phoneNumber, email, name, surname, password);
        return "redirect:/users";
    }
}
