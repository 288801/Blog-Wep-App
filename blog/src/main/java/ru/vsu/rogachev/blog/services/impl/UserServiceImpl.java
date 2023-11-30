package ru.vsu.rogachev.blog.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vsu.rogachev.blog.entities.User;
import ru.vsu.rogachev.blog.repositories.UserRepository;
import ru.vsu.rogachev.blog.services.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public User findByUsername(String username) {
        Iterable<User> users = findAll();
        for(User user : users){
            if(user.getUsername().equals(username)){
                return user;
            }
        }
        return null;
    }

    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    public User create(String username, String imageUrl, String phoneNumber, String email,
                       String name, String surname, String password) {
        User user = new User(username, imageUrl, phoneNumber, email, name, surname, password);
        userRepository.save(user);
        return user;
    }

    public void update(String username, String imageUrl, String phoneNumber, String email,
                       String name, String surname, String password) {
        Optional<User> user = userRepository.findById(username);
        user.get().setUsername(username);
        user.get().setImageUrl(imageUrl);
        user.get().setPhone_number(phoneNumber);
        user.get().setEmail(email);
        user.get().setName(name);
        user.get().setSurname(surname);
        user.get().setPassword(password);
        userRepository.save(user.get());
    }


    public void deleteByUsername(String username) {
        Optional<User> user = userRepository.findById(username);
        userRepository.delete(user.get());
    }
}
