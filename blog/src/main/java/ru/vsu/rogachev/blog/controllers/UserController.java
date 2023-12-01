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

    @GetMapping("")
    public String users(Model model) {
        Iterable<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "user/users";
    }

    @GetMapping("/add")
    public String add(Model model) {
        return "user/user-add";
    }

    @GetMapping("/{username}")
    public String userDescription(@PathVariable(value = "username") String username, Model model) {
        model.addAttribute("users", userService.findByUsername(username));
        return "user/user-info";
    }

    @GetMapping("/{username}/edit")
    public String userEdit(@PathVariable(value = "username") String username, Model model) {
        model.addAttribute("users", userService.findByUsername(username));
        return "user/user-edit";
    }

    @PostMapping("/add")
    public String addUser(@RequestParam String nickname, @RequestParam String imageUrl,
                          @RequestParam String phoneNumber, @RequestParam String email,
                          @RequestParam String name, @RequestParam String surname,
                          @RequestParam String password, Model model) {
        userService.create(nickname, imageUrl, phoneNumber, email, name, surname, password);
        return "redirect:/users";
    }

    @PostMapping("/{username}/edit")
    public String editUser(@PathVariable(value = "username") String username,
                           @RequestParam String imageUrl,
                           @RequestParam String phoneNumber, @RequestParam String email,
                           @RequestParam String name, @RequestParam String surname,
                           @RequestParam String password, Model model) {
        userService.update(username, imageUrl, phoneNumber, email, name, surname, password);
        return "redirect:/users";
    }

    @PostMapping("/{username}/remove")
    public String removeUser(@PathVariable(value = "username") String username, Model model) {
        userService.deleteByUsername(username);
        return "redirect:/users";
    }
}
