package ru.vsu.rogachev.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.vsu.rogachev.blog.entities.User;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
    User findByUsername(String username);

    List<User> findByUsernameList(String username);

    List<User> findAll();

    boolean existsByUsername(String username);

    User create(String username, String imageUrl, String phoneNumber, String email,
                String name, String surname, String password);

    void update(String username, String imageUrl, String phoneNumber, String email,
                String name, String surname, String password);

    void deleteByUsername(String username);
}
