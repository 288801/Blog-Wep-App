package ru.vsu.rogachev.blog.services;

import ru.vsu.rogachev.blog.entities.User;
import ru.vsu.rogachev.blog.repositories.UserRepository;
import ru.vsu.rogachev.blog.repositories.impl.UserRepositoryImpl;

import java.util.List;

public class UserService {

    private UserRepository userRepository = UserRepositoryImpl.getInstance();
    private static UserService instance;

    public static UserService getInstance() {
        if (instance == null)
            instance = new UserService();

        return instance;
    }

    private UserService() {}

    public void addUser(String username, String imageUrl, String phone_number, String email,
                        String name, String surname, String password){
        userRepository.add(new User(username, imageUrl, phone_number, email, name, surname, password));
    }

    public User getUserByEmail(String email){
        return userRepository.getById(email);
    }

    public List<User> getAllUsers(){
        return userRepository.getAll();
    }

    public void removeUserByEmail(String email){
        userRepository.removeById(email);
    }

    public void updateUser(String username, String imageUrl, String phone_number, String email,
                        String name, String surname, String password){
        userRepository.update(username, new User(username, imageUrl, phone_number, email, name, surname, password));
    }
}
