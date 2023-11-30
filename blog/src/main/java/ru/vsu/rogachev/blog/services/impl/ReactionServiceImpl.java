package ru.vsu.rogachev.blog.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import ru.vsu.rogachev.blog.entities.Post;
import ru.vsu.rogachev.blog.entities.Reaction;
import ru.vsu.rogachev.blog.repositories.ReactionRepository;
import ru.vsu.rogachev.blog.services.ReactionService;

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
    public Reaction create(String username, long postId) {
        Reaction reaction = new Reaction(username, postId);
        reactionRepository.save(reaction);
        return reaction;
    }

    @Override
    public void update(Long id, String username, long postId) {
        Reaction reaction = reactionRepository.findById(id).orElseThrow();
        reaction.setUserNickname(username);
        reaction.setPostId(postId);
        reactionRepository.save(reaction);
    }

    @Override
    public void deleteById(Long id) {
        Reaction reaction = reactionRepository.findById(id).orElseThrow();
        reactionRepository.delete(reaction);
    }
}
