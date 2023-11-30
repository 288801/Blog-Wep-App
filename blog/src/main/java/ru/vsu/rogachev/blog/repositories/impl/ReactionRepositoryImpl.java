package ru.vsu.rogachev.blog.repositories.impl;

import ru.vsu.rogachev.blog.db.ConnectionManager;
import ru.vsu.rogachev.blog.entities.Reaction;
import ru.vsu.rogachev.blog.repositories.ReactionRepository;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ReactionRepositoryImpl implements ReactionRepository {

    private static ConnectionManager connectionManager;
    private static ReactionRepositoryImpl instance;

    public static ReactionRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new ReactionRepositoryImpl();
            connectionManager = ConnectionManager.getInstance();
        }
        return instance;
    }

    private ReactionRepositoryImpl() {
    }

    @Override
    public List<Reaction> getAll() {
        try{
            List<Reaction> reactions = new ArrayList<>();
            ResultSet rs = connectionManager.executeSelect("SELECT * FROM `reaction`");
            while (!rs.isClosed() && rs.next()) {
                reactions.add(getAccountFromResultSet(rs));
            }
            rs.close();
            return reactions;
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void add(Reaction reaction) {
        try {
            connectionManager.executeUpdate("INSERT INTO `reaction`(`post_id`, `user_nickname`) VALUES ("
                    + reaction.getPostId() + ", '" + reaction.getUserNickname() + "')");
            return;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Reaction getById(Long id) {
        try {
            ResultSet rs = connectionManager.executeSelect("SELECT * FROM `reaction` WHERE id = " + id);
            rs.next();
            Reaction reaction = getAccountFromResultSet(rs);
            rs.close();
            return reaction;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void removeById(Long id) {
        try {
            connectionManager.executeUpdate("DELETE FROM `reaction` WHERE id = " + id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(Long id, Reaction reaction) {
        try {
            connectionManager.executeUpdate("UPDATE `reaction` SET " +
                    "`post_id`=" + reaction.getPostId() + ",`user_nickname`='" +reaction.getUserNickname() +
                    "' WHERE id = " + id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private Reaction getAccountFromResultSet(ResultSet rs) {
        try {
            long id = rs.getLong(1);
            int postId = rs.getInt(2);
            String userNickname = rs.getString(3);
            return new Reaction(userNickname, postId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
