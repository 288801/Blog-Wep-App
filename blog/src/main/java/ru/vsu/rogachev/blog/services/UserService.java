package ru.vsu.rogachev.blog.services;

import ru.vsu.rogachev.blog.entities.Post;
import ru.vsu.rogachev.blog.entities.User;

import java.util.List;

public interface UserService {

    User findByUsername(String username);

    List<User> findByUsernameList(String username);

    Iterable<User> findAll();

    boolean existsByUsername(String username);

    User create(String name, String description);

    void update(Long id, String name, String description);

    void deleteById(Long id);

}
