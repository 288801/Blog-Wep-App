package ru.vsu.rogachev.blog.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vsu.rogachev.blog.entities.Comment;
import ru.vsu.rogachev.blog.entities.Post;
import ru.vsu.rogachev.blog.entities.Reaction;
import ru.vsu.rogachev.blog.repositories.ReactionRepository;
import ru.vsu.rogachev.blog.services.ReactionService;

import java.util.List;

@Service
public class ReactionServiceImpl implements ReactionService {

    @Autowired
    private ReactionRepository reactionRepository;


    @Override
    public Reaction findById(Long id) {
        Reaction reaction = reactionRepository.findById(id).get();
        return reaction;
    }

    @Override
    public Iterable<Reaction> findAll() {
        return reactionRepository.findAll();
    }

    @Override
    public Reaction create(String username, Post post) {
        Reaction reaction = new Reaction(username, post);
        reactionRepository.save(reaction);
        return reaction;
    }

    @Override
    public void update(Long id, String username, Post post) {
        Reaction reaction = reactionRepository.findById(id).orElseThrow();
        reaction.setUserNickname(username);
        reaction.setPost(post);
        reactionRepository.save(reaction);
    }

    @Override
    public void deleteById(Long id) {
        Reaction reaction = reactionRepository.findById(id).orElseThrow();
        reactionRepository.delete(reaction);
    }

    @Override
    public boolean exist(String username, Post post) {
        Iterable<Reaction> reactions = findAll();
        for(Reaction reaction : reactions){
            if(reaction.getUserNickname().equals(username) && reaction.getPost().getId() == post.getId()){
                return true;
            }
        }
        return false;
    }

    public Iterable<Reaction> getPostReactions(Long postId){
        return reactionRepository.findAll().stream().filter(o -> o.getPost().getId() == postId).toList();
    }

    public long existByPair(Long postId, String username){
        Iterable<Reaction> reactions = reactionRepository.findAll();
        for(Reaction reaction : reactions){
            if(reaction.getUserNickname().equals(username) && reaction.getPost().getId() == postId){
                return reaction.getId();
            }
        }
        return -1;
    }
}
