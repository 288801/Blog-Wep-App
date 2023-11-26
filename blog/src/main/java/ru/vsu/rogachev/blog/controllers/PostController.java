package ru.vsu.rogachev.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.vsu.rogachev.blog.entities.Post;
import ru.vsu.rogachev.blog.services.impl.PostServiceImpl;

@Controller
@RequestMapping(value = "/posts")
public class PostController {

    @Autowired
    private PostServiceImpl postService;

    @GetMapping("/")
    public String posts(Model model) {
        Iterable<Post> posts = postService.findAll();
        model.addAttribute("posts", posts);
        return "post";
    }

}
