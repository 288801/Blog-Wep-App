package ru.vsu.rogachev.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.vsu.rogachev.blog.entities.Post;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {
    Optional<Post> findById(Long id);

    List<Post> findByIdList(Long id);

    List<Post> findAll();

    boolean existsById(Long id);

    Post create(String username, String imageUrl, String text);

    void update(Long id, String username, String imageUrl, String text);

    void deleteById(Long id);
}
