package ru.vsu.rogachev.blog.repositories.impl;

import ru.vsu.rogachev.blog.db.ConnectionManager;
import ru.vsu.rogachev.blog.entities.Post;
import ru.vsu.rogachev.blog.entities.Reaction;
import ru.vsu.rogachev.blog.repositories.PostRepository;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PostRepositoryImpl implements PostRepository {

    private static ConnectionManager connectionManager;
    private static PostRepositoryImpl instance;

    public static PostRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new PostRepositoryImpl();
            connectionManager = ConnectionManager.getInstance();
        }
        return instance;
    }

    private PostRepositoryImpl() {
    }

    @Override
    public List<Post> getAll() {
        try{
            List<Post> posts = new ArrayList<>();
            ResultSet rs = connectionManager.executeSelect("SELECT * FROM `post`");
            while (!rs.isClosed() && rs.next()) {
                posts.add(getAccountFromResultSet(rs));
            }
            rs.close();
            return posts;
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void add(Post post) {
        try {
            connectionManager.executeUpdate("INSERT INTO `post`(`user_nickname`, `image_url`, `text`) VALUES ('"
                    + post.getUserNickname() + "', '" + post.getImageUrl() + "', '" + post.getText() + "')");
            return;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Post getById(Long id) {
        try {
            ResultSet rs = connectionManager.executeSelect("SELECT * FROM `post` WHERE id = " + id);
            rs.next();
            Post post = getAccountFromResultSet(rs);
            rs.close();
            return post;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void removeById(Long id) {
        try {
            connectionManager.executeUpdate("DELETE FROM `post` WHERE id = " + id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(Long id, Post post) {
        try {
            connectionManager.executeUpdate("UPDATE `post` SET `user_nickname`='" + post.getUserNickname() + "'," +
                    "`image_url`='" + post.getImageUrl() + "'" +
                            ",`text`='" + post.getText() + "' WHERE id = " + id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private Post getAccountFromResultSet(ResultSet rs) {
        try {
            long id = rs.getLong(1);
            String userNickname = rs.getString(2);
            String imageUrl = rs.getString(3);
            String text = rs.getString(4);
            return new Post(userNickname, imageUrl, text);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
