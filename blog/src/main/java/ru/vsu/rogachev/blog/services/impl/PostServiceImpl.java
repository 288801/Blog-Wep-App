package ru.vsu.rogachev.blog.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vsu.rogachev.blog.entities.Comment;
import ru.vsu.rogachev.blog.entities.Post;
import ru.vsu.rogachev.blog.repositories.PostRepository;
import ru.vsu.rogachev.blog.services.PostService;

import java.util.*;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private FollowerServiceImpl followerService;
    @Autowired
    private UserServiceImpl userService;

    public Post findById(Long id) {
        Post post = postRepository.findById(id).get();
        return post;
    }

    public Iterable<Post> findAll() {
        return postRepository.findAll();
    }

    public Post create(String username, String imageUrl, String text, String header) {
        Post post = new Post(username, imageUrl, text, header,  new Date(System.currentTimeMillis()));
        postRepository.save(post);
        return post;
    }

    public void update(Long id, String imageUrl, String text, String header) {
        Post post = postRepository.findById(id).orElseThrow();
        post.setImageUrl(imageUrl);
        post.setText(text);
        post.setHeader(header);
        postRepository.save(post);
    }

    public void deleteById(Long id) {
        Post post = postRepository.findById(id).orElseThrow();
        postRepository.delete(post);
    }

    public Iterable<Post> getUserPosts(String username){
        List<Post> res = new ArrayList<>();
        Iterable<Post> posts = findAll();

        for(Post post : posts){
            if(post.getUserNickname().equals(username)){
                res.add(post);
            }
        }

        return res;
    }

    public Iterable<Post> getSubscriptionsPosts(String username){
        List<Post> res = new ArrayList<>();
        Iterable<Post> posts = findAll();
        Set<String> subscriptions = followerService.getSubscriptions(userService.findByUsername(username));

        for(Post post : posts){
            if(subscriptions.contains(post.getUserNickname())){
                res.add(post);
            }
        }

        return res;
    }

}
