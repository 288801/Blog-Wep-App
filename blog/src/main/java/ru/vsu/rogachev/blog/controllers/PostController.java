package ru.vsu.rogachev.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.vsu.rogachev.blog.entities.Post;
import ru.vsu.rogachev.blog.security.PersonDetails;
import ru.vsu.rogachev.blog.services.impl.CommentServiceImpl;
import ru.vsu.rogachev.blog.services.impl.PostServiceImpl;
import ru.vsu.rogachev.blog.services.impl.ReactionServiceImpl;

import java.security.Principal;
import java.security.Security;
import java.util.Date;

@Controller
@RequestMapping(value = "/posts")
public class PostController {

    @Autowired
    private PostServiceImpl postService;
    @Autowired
    private CommentServiceImpl commentService;
    @Autowired
    private ReactionServiceImpl reactionService;

    @GetMapping("")
    public String posts(Model model) {
        Iterable<Post> posts = postService.findAll();
        model.addAttribute("posts", posts);
        return "post/posts";
    }

    @GetMapping("/add")
    public String add(Model model) {
        PersonDetails personDetails = (PersonDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return "post/post-add";
    }

    @GetMapping("/{id}")
    public String postDescription(@PathVariable(value = "id") long postId, Model model) {
        model.addAttribute("posts", postService.findById(postId));
        model.addAttribute("comments", commentService.getPostComments(postId));
        model.addAttribute("reactions", reactionService.getPostReactions(postId));
        return "post/post-description";
    }

    @GetMapping("/{id}/edit")
    public String postEdit(@PathVariable(value = "id") long postId, Model model) {
        model.addAttribute("posts", postService.findById(postId));
        return "post/post-edit";
    }

    @GetMapping("/{id}/comment")
    public String commentAdd(@PathVariable(value = "id") long postId, Model model) {
        model.addAttribute("id", postId);
        return "comment/comment-add";
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

    @PostMapping("/{id}/comment")
    public String addComment(@PathVariable(value = "id") long postId, @RequestParam String username,
                              @RequestParam String text, Model model) {
        commentService.create(username, postId, text);
        return "redirect:/posts";
    }
}
