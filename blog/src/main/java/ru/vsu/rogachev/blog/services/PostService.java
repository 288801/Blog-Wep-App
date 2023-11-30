package ru.vsu.rogachev.blog.services;

import ru.vsu.rogachev.blog.entities.Post;
import ru.vsu.rogachev.blog.entities.User;
import ru.vsu.rogachev.blog.repositories.PostRepository;
import ru.vsu.rogachev.blog.repositories.UserRepository;
import ru.vsu.rogachev.blog.repositories.impl.PostRepositoryImpl;
import ru.vsu.rogachev.blog.repositories.impl.UserRepositoryImpl;

import java.util.List;

public class PostService {

    private PostRepository postRepository = PostRepositoryImpl.getInstance();
    private static PostService instance;

    public static PostService getInstance() {
        if (instance == null)
            instance = new PostService();

        return instance;
    }

    private PostService() {}

    public void addPost(String userNickname, String imageUrl, String text){
        postRepository.add(new Post(userNickname, imageUrl, text));
    }

    public Post getPostById(Long id){
        return postRepository.getById(id);
    }

    public List<Post> getAllPosts(){
        return postRepository.getAll();
    }

    public void removePostById(Long id){
        postRepository.removeById(id);
    }

    public void updatePost(Long id, String userNickname, String imageUrl, String text){
        postRepository.update(id, new Post(userNickname, imageUrl, text));
    }
}
