package ru.vsu.rogachev.blog.repositories.impl;

import ru.vsu.rogachev.blog.db.ConnectionManager;
import ru.vsu.rogachev.blog.entities.Follower;
import ru.vsu.rogachev.blog.entities.Reaction;
import ru.vsu.rogachev.blog.repositories.FollowerRepository;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FollowerRepositoryImpl implements FollowerRepository {

    private static ConnectionManager connectionManager;
    private static FollowerRepositoryImpl instance;

    public static FollowerRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new FollowerRepositoryImpl();
            connectionManager = ConnectionManager.getInstance();
        }
        return instance;
    }

    private FollowerRepositoryImpl() {
    }

    @Override
    public List<Follower> getAll() {
        try{
            List<Follower> followers = new ArrayList<>();
            ResultSet rs = connectionManager.executeSelect("SELECT * FROM `follower`");
            while (!rs.isClosed() && rs.next()) {
                followers.add(getAccountFromResultSet(rs));
            }
            rs.close();
            return followers;
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void add(Follower follower) {
        try {
            connectionManager.executeUpdate("INSERT INTO `follower`(`user_nickname`, `follower_nickname`) VALUES ('"
                    + follower.getUserNickname() + "', '" + follower.getFollowerNickname() + "')");
            return;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Follower getById(Long id) {
        try {
            ResultSet rs = connectionManager.executeSelect("SELECT * FROM `follower` WHERE id = " + id);
            rs.next();
            Follower follower = getAccountFromResultSet(rs);
            rs.close();
            return follower;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void removeById(Long id) {
        try {
            connectionManager.executeUpdate("DELETE FROM `follower` WHERE id = " + id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(Long id, Follower follower) {
        try {
            connectionManager.executeUpdate("UPDATE `follower` SET " +
                    "`user_nickname`='" + follower.getUserNickname() + "',`follower_nickname`='"
                    + follower.getFollowerNickname() + "' WHERE id = " + id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private Follower getAccountFromResultSet(ResultSet rs) {
        try {
            long id = rs.getLong(1);
            String userNickname = rs.getString(2);
            String followerNickname = rs.getString(3);
            return new Follower(userNickname, followerNickname);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
