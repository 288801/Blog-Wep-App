package ru.vsu.rogachev.blog.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vsu.rogachev.blog.entities.User;
import ru.vsu.rogachev.blog.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ImageService imageService;

    public User findByUsername(String username) {
        Iterable<User> users = findAll();
        for(User user : users){
            if(user.getUsername().equals(username)){
                return user;
            }
        }
        return null;
    }

    public List<User> findByUsernameList(String username) {
        Optional<User> users = Optional.ofNullable(userRepository.findByUsername(username));
        List<User> res = new ArrayList<>();
        users.ifPresent(res::add);
        return res;
    }

    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public User create(String username, String imageUrl, String phoneNumber, String email,
                       String name, String surname, String password) {
        User user = new User(username, imageUrl, phoneNumber, email, name, surname, password);
        userRepository.save(user);
        return user;
    }

    public void update(String username, String imageUrl, String phoneNumber, String email,
                       String name, String surname, String password) {
        User user = userRepository.findByUsername(username);
        user.setUsername(username);
        user.setImageUrl(imageUrl);
        user.setPhone_number(phoneNumber);
        user.setEmail(email);
        user.setName(name);
        user.setSurname(surname);
        user.setPassword(password);
        userRepository.save(user);
    }


    public void deleteByUsername(String username) {
        User user = userRepository.findByUsername(username);
        userRepository.delete(user);
    }
}
