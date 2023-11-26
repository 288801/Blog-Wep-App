package ru.vsu.rogachev.blog.repositories;

import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vsu.rogachev.blog.entities.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

}
