package ru.vsu.rogachev.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.vsu.rogachev.blog.entities.Comment;
import ru.vsu.rogachev.blog.entities.Post;
import ru.vsu.rogachev.blog.services.impl.CommentServiceImpl;
import ru.vsu.rogachev.blog.services.impl.PostServiceImpl;

import java.util.Date;

@Controller
@RequestMapping(value = "/comments")
public class CommentController {

    @Autowired
    private CommentServiceImpl commentService;

    @GetMapping("")
    public String comments(Model model) {
        Iterable<Comment> comments = commentService.findAll();
        model.addAttribute("comments", comments);
        return "comment/comments";
    }

    @GetMapping("/add")
    public String add(Model model) {
        return "comment/comment-add";
    }

    @GetMapping("/{id}")
    public String commentDescription(@PathVariable(value = "id") long commentId, Model model) {
        model.addAttribute("comments", commentService.findById(commentId));
        return "comment/comment-info";
    }

    @GetMapping("/{id}/edit")
    public String commentEdit(@PathVariable(value = "id") long commentId, Model model) {
        model.addAttribute("comments", commentService.findById(commentId));
        return "comment/comment-edit";
    }

    @PostMapping("/add")
    public String addComment(@RequestParam String username, @RequestParam String postId,
                          @RequestParam String text, Model model) {
        commentService.create(username, Long.parseLong(postId), text);
        return "redirect:/comments";
    }

    @PostMapping("/{id}/edit")
    public String editComment(@PathVariable(value = "id") long commentId, @RequestParam String username,
                              @RequestParam String postId,
                              @RequestParam String text, Model model) {
        commentService.update(commentId, username, Long.parseLong(postId), text);
        return "redirect:/comments";
    }

    @PostMapping("/{id}/remove")
    public String removeComment(@PathVariable(value = "id") long commentId, Model model) {
        commentService.deleteById(commentId);
        return "redirect:/comments";
    }
}
