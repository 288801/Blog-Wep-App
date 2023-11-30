package ru.vsu.rogachev.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vsu.rogachev.blog.entities.Follower;

@Repository
public interface FollowerRepository extends JpaRepository<Follower, Long> {
}
