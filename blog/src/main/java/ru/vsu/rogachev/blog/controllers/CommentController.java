package ru.vsu.rogachev.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.vsu.rogachev.blog.entities.Comment;
import ru.vsu.rogachev.blog.services.impl.CommentServiceImpl;


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

    @PostMapping("/{id}/edit")
    public String editComment(@PathVariable(value = "id") long commentId, @RequestParam String username,
                              @RequestParam String text, Model model) {
        commentService.update(commentId, username, text);
        Comment comment = commentService.findById(commentId);
        long id = comment.getPost().getId();
        return "redirect:/posts/" + id;
    }

    @PostMapping("/{id}/remove")
    public String removeComment(@PathVariable(value = "id") long commentId, Model model) {
        commentService.deleteById(commentId);
        return "redirect:/comments";
    }
}
