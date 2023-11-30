package ru.vsu.rogachev.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vsu.rogachev.blog.entities.Reaction;

@Repository
public interface ReactionRepository extends JpaRepository<Reaction, Long> {
}
