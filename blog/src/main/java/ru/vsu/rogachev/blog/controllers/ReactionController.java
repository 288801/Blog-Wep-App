package ru.vsu.rogachev.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.vsu.rogachev.blog.entities.Post;
import ru.vsu.rogachev.blog.entities.Reaction;
import ru.vsu.rogachev.blog.services.impl.PostServiceImpl;
import ru.vsu.rogachev.blog.services.impl.ReactionServiceImpl;

import java.util.Date;

@Controller
@RequestMapping(value = "/reactions")
public class ReactionController {

    @Autowired
    private ReactionServiceImpl reactionService;

    @GetMapping("")
    public String reaction(Model model) {
        Iterable<Reaction> reactions = reactionService.findAll();
        model.addAttribute("reactions", reactions);
        return "reaction/reactions";
    }

    @GetMapping("/add")
    public String add(Model model) {
        return "reaction/reaction-add";
    }

    @GetMapping("/{id}")
    public String reactionDescription(@PathVariable(value = "id") long reactionId, Model model) {
        model.addAttribute("reactions", reactionService.findById(reactionId));
        return "reaction/reaction-info";
    }

    @GetMapping("/{id}/edit")
    public String reactionEdit(@PathVariable(value = "id") long reactionId, Model model) {
        model.addAttribute("reactions", reactionService.findById(reactionId));
        return "reaction/reaction-edit";
    }

    @PostMapping("/add")
    public String addReaction(@RequestParam String username, @RequestParam String postId, Model model) {
        reactionService.create(username, Long.parseLong(postId));
        return "redirect:/reactions";
    }

    @PostMapping("/{id}/edit")
    public String editReaction(@PathVariable(value = "id") long reactionId,
                               @RequestParam String username, @RequestParam String postId, Model model) {
        reactionService.update(reactionId, username, Long.parseLong(postId));
        return "redirect:/reactions";
    }

    @PostMapping("/{id}/remove")
    public String removeReaction(@PathVariable(value = "id") long reactionId, Model model) {
        reactionService.deleteById(reactionId);
        return "redirect:/reactions";
    }
}
