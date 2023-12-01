package ru.vsu.rogachev.blog.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vsu.rogachev.blog.entities.Follower;
import ru.vsu.rogachev.blog.entities.Post;
import ru.vsu.rogachev.blog.repositories.FollowerRepository;
import ru.vsu.rogachev.blog.services.FollowerService;

@Service
public class FollowerServiceImpl implements FollowerService {

    @Autowired
    private FollowerRepository followerRepository;

    @Override
    public Follower findById(Long id) {
        Follower follower = followerRepository.findById(id).get();
        return follower;
    }

    @Override
    public Iterable<Follower> findAll() {
        return followerRepository.findAll();
    }

    @Override
    public Follower create(String username, String followerNickname) {
        Follower follower = new Follower(username, followerNickname);
        followerRepository.save(follower);
        return follower;
    }

    @Override
    public void update(Long id, String username, String followerNickname) {
        Follower follower = followerRepository.findById(id).orElseThrow();
        follower.setUserNickname(username);
        follower.setFollowerNickname(followerNickname);
        followerRepository.save(follower);
    }

    @Override
    public void deleteById(Long id) {
        Follower follower = followerRepository.findById(id).orElseThrow();
        followerRepository.delete(follower);
    }
}
