package ru.vsu.rogachev.blog.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.vsu.rogachev.blog.entities.Post;
import ru.vsu.rogachev.blog.repositories.PostRepository;
import ru.vsu.rogachev.blog.services.impl.PostServiceImpl;

import java.util.Date;

@Controller
@RequestMapping(value = "/posts")
public class PostController {

    @Autowired
    private PostServiceImpl postService;
    @Autowired
    private PostRepository postRepository;

    @GetMapping("")
    public String posts(Model model) {
        Iterable<Post> posts = postService.findAll();
        model.addAttribute("posts", posts);
        return "post/posts";
    }

    @GetMapping("/add")
    public String add(Model model) {
        return "post/post-add";
    }

    @GetMapping("/{id}")
    public String postDescription(@PathVariable(value = "id") long postId, Model model) {
        model.addAttribute("posts", postService.findById(postId));
        return "post/post-description";
    }

    @GetMapping("/{id}/edit")
    public String postEdit(@PathVariable(value = "id") long postId, Model model) {
        model.addAttribute("posts", postService.findById(postId));
        return "post/post-edit";
    }

    @PostMapping("/add")
    public String addPost(@RequestParam String title, @RequestParam String txt,
                          @RequestParam String imageUrl, Model model) {
        Post post = new Post("egor4444ik", null, txt, title, new Date(System.currentTimeMillis()));
        postService.create(post.getUserNickname(), post.getImageUrl(), post.getText(), post.getHeader());
        return "redirect:/posts";
    }

    @PostMapping("/{id}/edit")
    public String editPost(@PathVariable(value = "id") long postId, @RequestParam String title,
                           @RequestParam String txt, @RequestParam String imageUrl, Model model) {
        postService.update(postId, imageUrl, txt, title);
        return "redirect:/posts";
    }

    @PostMapping("/{id}/remove")
    public String removePost(@PathVariable(value = "id") long postId, Model model) {
        postService.deleteById(postId);
        return "redirect:/posts";
    }
}
