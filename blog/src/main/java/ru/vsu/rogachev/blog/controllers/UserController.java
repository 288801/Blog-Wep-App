package ru.vsu.rogachev.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.vsu.rogachev.blog.entities.Post;
import ru.vsu.rogachev.blog.entities.User;
import ru.vsu.rogachev.blog.repositories.PostRepository;
import ru.vsu.rogachev.blog.services.impl.FollowerServiceImpl;
import ru.vsu.rogachev.blog.services.impl.PostServiceImpl;
import ru.vsu.rogachev.blog.services.impl.UserServiceImpl;

import java.security.Principal;
import java.util.Date;

@Controller
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private FollowerServiceImpl followerService;
    @Autowired
    private PostServiceImpl postService;

    @GetMapping("")
    public String users(Model model, Principal principal) {
        Iterable<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "user/users";
    }

    @GetMapping("/{username}")
    public String userDescription(@PathVariable(value = "username") String username, Model model) {
        model.addAttribute("users", userService.findByUsername(username));
        model.addAttribute("posts", postService.getUserPosts(username));
        return "user/user-info";
    }

    @PostMapping("/{username}/follow")
    public String followUser(@PathVariable(value = "username") String username, Model model, Principal principal) {
        long id = followerService.existByPair(username, principal.getName());
        if(id == -1){
            followerService.create(username, principal.getName());
        }else{
            followerService.deleteById(id);
        }
        return "redirect:/users";
    }
}
