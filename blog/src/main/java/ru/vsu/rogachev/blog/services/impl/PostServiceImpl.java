package ru.vsu.rogachev.blog.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.vsu.rogachev.blog.entities.Post;
import ru.vsu.rogachev.blog.repositories.PostRepository;
import ru.vsu.rogachev.blog.services.PostService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public Post findById(Long id) {
        List<Post> illnesses = findByIdList(id);
        if (illnesses.size() != 0) {
            return illnesses.get(0);
        }
        return null;
    }

    @Override
    public List<Post> findByIdList(Long id) {
        Optional<Post> posts = postRepository.findById(id);
        List<Post> res = new ArrayList<>();
        posts.ifPresent(res::add);
        return res;
    }

    @Override
    public Iterable<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public boolean existsById(Long id) {
        return postRepository.existsById(id);
    }

    @Override
    public Post create(String username, String imageUrl, String text) {
        Post illness = new Post(username, imageUrl, text);
        postRepository.save(illness);
        return illness;
    }

    @Override
    public void update(Long id, String username, String imageUrl, String text) {
        Post illness = postRepository.findById(id).orElseThrow();
        illness.setName(name);
        illness.setDescription(description);
        postRepository.save(illness);
    }

    @Override
    public void deleteById(Long id) {
        Post illness = postRepository.findById(id).orElseThrow();
        postRepository.delete(illness);
    }

}
