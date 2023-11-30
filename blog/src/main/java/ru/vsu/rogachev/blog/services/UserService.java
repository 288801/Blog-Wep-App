package ru.vsu.rogachev.blog.services;

import ru.vsu.rogachev.blog.entities.User;

import java.util.List;

public interface UserService {

    public User findByUsername(String username);
    public Iterable<User> findAll();
    public User create(String username, String imageUrl, String phoneNumber, String email,
                       String name, String surname, String password);
    public void update(String username, String imageUrl, String phoneNumber, String email,
                       String name, String surname, String password);
    public void deleteByUsername(String username);

}
