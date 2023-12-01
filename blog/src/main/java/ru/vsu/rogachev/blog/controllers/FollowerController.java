package ru.vsu.rogachev.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.vsu.rogachev.blog.entities.Follower;
import ru.vsu.rogachev.blog.entities.Post;
import ru.vsu.rogachev.blog.services.impl.FollowerServiceImpl;
import ru.vsu.rogachev.blog.services.impl.PostServiceImpl;

import java.util.Date;

@Controller
@RequestMapping(value = "/followers")
public class FollowerController {

    @Autowired
    private FollowerServiceImpl followerService;

    @GetMapping("")
    public String followers(Model model) {
        Iterable<Follower> followers = followerService.findAll();
        model.addAttribute("followers", followers);
        return "follower/followers";
    }

    @GetMapping("/add")
    public String add(Model model) {
        return "follower/follower-add";
    }

    @GetMapping("/{id}")
    public String followerDescription(@PathVariable(value = "id") long followerId, Model model) {
        model.addAttribute("followers", followerService.findById(followerId));
        return "follower/follower-info";
    }

    @GetMapping("/{id}/edit")
    public String followerEdit(@PathVariable(value = "id") long followerId, Model model) {
        model.addAttribute("followers", followerService.findById(followerId));
        return "follower/follower-edit";
    }

    @PostMapping("/add")
    public String addFollower(@RequestParam String userNickname, @RequestParam String followerNickname, Model model) {
        followerService.create(userNickname, followerNickname);
        return "redirect:/followers";
    }

    @PostMapping("/{id}/edit")
    public String editFollower(@PathVariable(value = "id") long followerId, @RequestParam String userNickname,
                               @RequestParam String followerNickname, Model model) {
        followerService.update(followerId, userNickname, followerNickname);
        return "redirect:/followers";
    }

    @PostMapping("/{id}/remove")
    public String removePost(@PathVariable(value = "id") long followerId, Model model) {
        followerService.deleteById(followerId);
        return "redirect:/followers";
    }
}
