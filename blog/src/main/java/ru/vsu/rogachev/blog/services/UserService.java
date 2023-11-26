package ru.vsu.rogachev.blog.services;

import ru.vsu.rogachev.blog.entities.Post;
import ru.vsu.rogachev.blog.entities.User;

import java.util.List;

public interface UserService {

    User findByUsername(String username);

    List<User> findByUsernameList(String username);

    Iterable<User> findAll();

    boolean existsByUsername(String username);

    User create(String username, String imageUrl, String phoneNumber, String email,
                String name, String surname, String password);

    void update(String username, String imageUrl, String phoneNumber, String email,
                String name, String surname, String password);

    void deleteByUsername(String username);

}
