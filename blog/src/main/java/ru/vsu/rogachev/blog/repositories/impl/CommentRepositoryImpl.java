package ru.vsu.rogachev.blog.repositories.impl;

import ru.vsu.rogachev.blog.db.ConnectionManager;
import ru.vsu.rogachev.blog.entities.Comment;
import ru.vsu.rogachev.blog.entities.Post;
import ru.vsu.rogachev.blog.entities.Reaction;
import ru.vsu.rogachev.blog.repositories.CommentRepository;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CommentRepositoryImpl implements CommentRepository {

    private static ConnectionManager connectionManager;
    private static CommentRepositoryImpl instance;

    public static CommentRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new CommentRepositoryImpl();
            connectionManager = ConnectionManager.getInstance();
        }
        return instance;
    }

    private CommentRepositoryImpl() {
    }

    @Override
    public List<Comment> getAll() {
        try{
            List<Comment> comments = new ArrayList<>();
            ResultSet rs = connectionManager.executeSelect("SELECT * FROM `comment`");
            while (!rs.isClosed() && rs.next()) {
                comments.add(getAccountFromResultSet(rs));
            }
            rs.close();
            return comments;
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void add(Comment comment) {
        try {
            connectionManager.executeUpdate("INSERT INTO `comment`(`user_nickname`, `post_id`, `text`) VALUES ('"
                    + comment.getUserNickname() + "', " + comment.getPostId() + ", '" + comment.getText() + "')");
            return;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Comment getById(Long id) {
        try {
            ResultSet rs = connectionManager.executeSelect("SELECT * FROM `comment` WHERE id = " + id);
            rs.next();
            Comment comment = getAccountFromResultSet(rs);
            rs.close();
            return comment;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void removeById(Long id) {
        try {
            connectionManager.executeUpdate("DELETE FROM `comment` WHERE id = " + id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(Long id, Comment comment) {
        try {
            connectionManager.executeUpdate("UPDATE `comment` SET `user_nickname`='" + comment.getUserNickname() + "'," +
                    "`post_id`=" + comment.getPostId() + "" +
                    ",`text`='" + comment.getText() + "' WHERE id = " + id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private Comment getAccountFromResultSet(ResultSet rs) {
        try {
            long id = rs.getLong(1);
            String userNickname = rs.getString(2);
            long postId = rs.getLong(3);
            String text = rs.getString(4);
            return new Comment(userNickname, postId, text);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
