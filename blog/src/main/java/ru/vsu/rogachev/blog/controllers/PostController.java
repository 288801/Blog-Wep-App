package ru.vsu.rogachev.blog.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.vsu.rogachev.blog.entities.Post;
import ru.vsu.rogachev.blog.repositories.PostRepository;
import ru.vsu.rogachev.blog.services.impl.PostServiceImpl;

import java.util.Date;

@Controller
//@RequestMapping(value = "/posts")
public class PostController {

    @Autowired
    private PostServiceImpl postService;
    @Autowired
    private PostRepository postRepository;

    @GetMapping("/posts")
    public String posts(Model model) {
        Iterable<Post> posts = postService.findAll();
        model.addAttribute("posts", posts);
        return "posts";
    }

    @GetMapping("/posts/add")
    public String add(Model model) {
        return "addingPost";
    }

    @PostMapping("/posts/add")
    public String addPost(@RequestParam String title, @RequestParam String txt, Model model) {
        Post post = new Post("egor4444ik", null, txt, title, new Date(System.currentTimeMillis()));
        postService.create(post.getUserNickname(), post.getImageUrl(), post.getText(), post.getHeader());
        return "redirect:/posts";
    }
}
