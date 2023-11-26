package ru.vsu.rogachev.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vsu.rogachev.blog.entities.Image;
import ru.vsu.rogachev.blog.entities.Post;
import ru.vsu.rogachev.blog.entities.User;

import java.util.Optional;

@Repository
public interface ImageRepository  extends JpaRepository<Image, String> {
    Optional<Image> findByUrl(String url);
    boolean existsByUrl(String url);
}
