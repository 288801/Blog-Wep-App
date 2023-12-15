package ru.vsu.rogachev.blog.services;

import ru.vsu.rogachev.blog.entities.Post;
import ru.vsu.rogachev.blog.entities.Reaction;

public interface ReactionService {

    public Reaction findById(Long id);
    public Iterable<Reaction> findAll();
    public Reaction create(String username, Post post);
    public void update(Long id, String username, Post post);
    public void deleteById(Long id);
    public boolean exist(String username, Post post);

}
