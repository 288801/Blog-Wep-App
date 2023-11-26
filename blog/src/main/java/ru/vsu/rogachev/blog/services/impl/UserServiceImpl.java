package ru.vsu.rogachev.blog.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import ru.vsu.rogachev.blog.entities.User;
import ru.vsu.rogachev.blog.repositories.UserRepository;
import ru.vsu.rogachev.blog.services.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByUsername(String username) {
        Iterable<User> users = findAll();
        for(User user : users){
            if(user.getUsername().equals(username)){
                return user;
            }
        }
        return null;
    }

    @Override
    public List<User> findByUsernameList(String username) {
        Optional<User> users = userRepository.findByUsername(username);
        List<User> res = new ArrayList<>();
        users.ifPresent(res::add);
        return res;
    }

    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public User create(String name, String description) {
        User user = new User(name, description);
        userRepository.save(user);
        return user;
    }

    @Override
    public void update(Long id, String name, String description) {
        User user = userRepository.findById(id).orElseThrow();
        user.setName(name);
        user.setDescription(description);
        userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        User user = userRepository.findById(id).orElseThrow();
        userRepository.delete(user);
    }
}
