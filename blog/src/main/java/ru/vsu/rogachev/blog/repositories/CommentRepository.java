package ru.vsu.rogachev.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vsu.rogachev.blog.entities.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
