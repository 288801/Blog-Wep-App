package ru.vsu.rogachev.blog.services;

import ru.vsu.rogachev.blog.entities.Follower;
import ru.vsu.rogachev.blog.entities.Reaction;

public interface FollowerService {

    public Follower findById(Long id);
    public Iterable<Follower> findAll();
    public Follower create(String username, String followerNickname);
    public void update(Long id, String username, String followerNickname);
    public void deleteById(Long id);

}
