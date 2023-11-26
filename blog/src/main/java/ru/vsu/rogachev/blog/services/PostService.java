package ru.vsu.rogachev.blog.services;

import ru.vsu.rogachev.blog.entities.Post;

import java.util.List;

public interface PostService {

    Post findById(Long id);

    List<Post> findByIdList(Long id);

    Iterable<Post> findAll();

    boolean existsById(Long id);

    Post create(String username, String imageUrl, String text);

    void update(Long id, String username, String imageUrl, String text);

    void deleteById(Long id);


}
