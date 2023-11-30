package ru.vsu.rogachev.blog.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.vsu.rogachev.blog.entities.Post;
import ru.vsu.rogachev.blog.services.PostService;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/posts")
public class PostController {

    private final PostService postService;

    @GetMapping("/")
    public String posts(Model model) {
        Iterable<Post> posts = postService.findAll();
        model.addAttribute("posts", posts);
        return "post";
    }

}
