package ru.vsu.rogachev.blog.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vsu.rogachev.blog.entities.Post;
import ru.vsu.rogachev.blog.repositories.PostRepository;
import ru.vsu.rogachev.blog.services.PostService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

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

    public void update(Long id, String username, String imageUrl, String text, String header) {
        Post post = postRepository.findById(id).orElseThrow();
        post.setUserNickname(username);
        post.setImageUrl(imageUrl);
        post.setText(text);
        post.setHeader(header);
        postRepository.save(post);
    }

    public void deleteById(Long id) {
        Post post = postRepository.findById(id).orElseThrow();
        postRepository.delete(post);
    }

}
