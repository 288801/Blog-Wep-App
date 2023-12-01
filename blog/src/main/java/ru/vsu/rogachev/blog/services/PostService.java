package ru.vsu.rogachev.blog.services;

import ru.vsu.rogachev.blog.entities.Post;

import java.util.List;

public interface PostService {

    public Post findById(Long id);
    public Iterable<Post> findAll();
    public Post create(String username, String imageUrl, String text, String header);
    public void update(Long id, String username, String imageUrl, String text, String header);
    public void deleteById(Long id);

}
