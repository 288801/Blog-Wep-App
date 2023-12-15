package ru.vsu.rogachev.blog.services;

import ru.vsu.rogachev.blog.entities.Comment;
import ru.vsu.rogachev.blog.entities.Follower;
import ru.vsu.rogachev.blog.entities.Post;
import ru.vsu.rogachev.blog.entities.Reaction;

public interface CommentService {

    public Comment findById(Long id);
    public Iterable<Comment> findAll();
    public Comment create(String username, Post post, String text);
    public void update(Long id, String username, String text);
    public void deleteById(Long id);

}
