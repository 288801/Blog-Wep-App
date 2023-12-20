package ru.vsu.rogachev.blog.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vsu.rogachev.blog.entities.Follower;
import ru.vsu.rogachev.blog.entities.Post;
import ru.vsu.rogachev.blog.entities.User;
import ru.vsu.rogachev.blog.repositories.FollowerRepository;
import ru.vsu.rogachev.blog.services.FollowerService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class FollowerServiceImpl implements FollowerService {

    @Autowired
    private FollowerRepository followerRepository;
    @Autowired
    private UserServiceImpl userService;

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

    public Iterable<User> getFollowers(User user){
        Iterable<Follower> followers = findAll();
        List<User> result = new ArrayList<>();
        for(Follower follower : followers){
            if(follower.getUserNickname().equals(user.getUsername())){
                result.add(userService.findByUsername(follower.getFollowerNickname()));
            }
        }

        return result;
    }

    public Set<String> getSubscriptions(User user) {
        Iterable<Follower> followers = findAll();
        Set<String> result = new HashSet<>();
        for (Follower follower : followers) {
            if (follower.getFollowerNickname().equals(user.getUsername())) {
                result.add(follower.getUserNickname());
            }
        }

        return result;
    }

    public long existByPair(String username, String followerName){
        Iterable<Follower> followers = findAll();
        for(Follower follower : followers){
            if(follower.getUserNickname().equals(username) && follower.getFollowerNickname().equals(followerName)){
                return follower.getId();
            }
        }

        return -1;
    }
}
