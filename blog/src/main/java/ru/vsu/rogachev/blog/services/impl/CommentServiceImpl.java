package ru.vsu.rogachev.blog.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vsu.rogachev.blog.entities.Comment;
import ru.vsu.rogachev.blog.entities.Post;
import ru.vsu.rogachev.blog.repositories.CommentRepository;
import ru.vsu.rogachev.blog.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Comment findById(Long id) {
        Comment comment = commentRepository.findById(id).get();
        return comment;
    }

    @Override
    public Iterable<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public Comment create(String username, long postId, String text) {
        Comment comment = new Comment(username, postId, text);
        commentRepository.save(comment);
        return comment;
    }

    @Override
    public void update(Long id, String username, long postId, String text) {
        Comment comment = commentRepository.findById(id).orElseThrow();
        comment.setUserNickname(username);
        comment.setPostId(postId);
        comment.setText(text);
        commentRepository.save(comment);
    }

    @Override
    public void deleteById(Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow();
        commentRepository.delete(comment);
    }
}
