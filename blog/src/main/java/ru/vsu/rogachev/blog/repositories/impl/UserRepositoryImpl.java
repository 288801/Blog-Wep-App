package ru.vsu.rogachev.blog.repositories.impl;

import ru.vsu.rogachev.blog.db.ConnectionManager;
import ru.vsu.rogachev.blog.entities.User;
import ru.vsu.rogachev.blog.repositories.UserRepository;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    private static ConnectionManager connectionManager;
    private static UserRepositoryImpl instance;

    public static UserRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new UserRepositoryImpl();
            connectionManager = ConnectionManager.getInstance();
        }
        return instance;
    }

    private UserRepositoryImpl() {
    }

    @Override
    public List<User> getAll() {
        try{
            List<User> users = new ArrayList<>();
            ResultSet rs = connectionManager.executeSelect("SELECT * FROM `user`");
            while (!rs.isClosed() && rs.next()) {
                users.add(getUserFromResultSet(rs));
            }
            rs.close();
            return users;
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void add(User user) {
        try {
            connectionManager.executeUpdate("INSERT INTO `user`(`nickname`, `photo_url`, `phone_number`," +
                    " `email`, `name`, `surname`, `password`) VALUES ( '"
                    + user.getUsername() + "', '" + user.getImageUrl() + "', '" + user.getPhone_number()
                    + "', '" + user.getEmail() + "', '" + user.getName() + "', '" + user.getSurname()
                    + "', '" + user.getPassword() + "')");
            return;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public User getById(String nickname) {
        try {
            ResultSet rs = connectionManager.executeSelect("SELECT * FROM `user` WHERE nickname = '" + nickname + "'");
            rs.next();
            User user = getUserFromResultSet(rs);
            rs.close();
            return user;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void removeById(String nickname) {
        try {
            connectionManager.executeUpdate("DELETE FROM `user` WHERE nickname = '" + nickname + "'");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(String nickname, User user) {
        try {
            connectionManager.executeUpdate("UPDATE `user` SET `nickname`='" + user.getUsername() + "'," +
                    "`photo_url`='" + user.getImageUrl() + "',`phone_number`='" + user.getPhone_number() + "'," +
                    "`email`='" + user.getEmail() + "',`name`='" + user.getName() + "',`surname`='" + user.getSurname() + "'," +
                    "`password`='" + user.getPassword() + "' WHERE nickname = '" + nickname + "'");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private User getUserFromResultSet(ResultSet rs) {
        try {
            String nickname = rs.getString(1);
            String photoUrl = rs.getString(2);
            String phoneNumber  = rs.getString(3);
            String email = rs.getString(4);
            String name = rs.getString(5);
            String surname = rs.getString(5);
            String password = rs.getString(5);

            return new User(nickname, photoUrl, phoneNumber, email, name, surname, password);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
