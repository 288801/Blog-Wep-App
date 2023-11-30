package ru.vsu.rogachev.blog.services;

import org.springframework.stereotype.Service;
import ru.vsu.rogachev.blog.entities.Post;
import ru.vsu.rogachev.blog.repositories.PostRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;

    private final ImageService imageService;

    private final UserService userService;

    public PostService(PostRepository postRepository, ImageService imageService, UserService userService) {
        this.postRepository = postRepository;
        this.imageService = imageService;
        this.userService = userService;
    }

    public Post findById(Long id) {
        List<Post> posts = findByIdList(id);
        if (posts.size() != 0) {
            return posts.get(0);
        }
        return null;
    }

    public List<Post> findByIdList(Long id) {
        Optional<Post> posts = postRepository.findById(id);
        List<Post> res = new ArrayList<>();
        posts.ifPresent(res::add);
        return res;
    }

    public Iterable<Post> findAll() {
        return postRepository.findAll();
    }

    public boolean existsById(Long id) {
        return postRepository.existsById(id);
    }

    public Post create(String username, String imageUrl, String text) {
        Post post = new Post(username, imageUrl, text);
        postRepository.save(post);
        return post;
    }

    public void update(Long id, String username, String imageUrl, String text) {
        Post post = postRepository.findById(id).orElseThrow();
        post.setUserNickname(username);
        post.setImageUrl(imageUrl);
        post.setText(text);
        postRepository.save(post);
    }

    public void deleteById(Long id) {
        Post post = postRepository.findById(id).orElseThrow();
        postRepository.delete(post);
    }

}
