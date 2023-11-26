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
    @Autowired
    private ImageServiceImpl imageService;

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
    public User create(String username, String imageUrl, String phoneNumber, String email,
                       String name, String surname, String password) {
        User user = new User(username, imageService.findByUrl(imageUrl), phoneNumber, email, name, surname, password);
        userRepository.save(user);
        return user;
    }

    @Override
    public void update(String username, String imageUrl, String phoneNumber, String email,
                       String name, String surname, String password) {
        User user = userRepository.findByUsername(username).orElseThrow();
        user.setUsername(username);
        user.setImage(imageService.findByUrl(imageUrl));
        user.setPhone_number(phoneNumber);
        user.setEmail(email);
        user.setName(name);
        user.setSurname(surname);
        user.setPassword(password);
        userRepository.save(user);
    }

    @Override
    public void deleteByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow();
        userRepository.delete(user);
    }
}
