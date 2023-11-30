package ru.vsu.rogachev.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.vsu.rogachev.blog.entities.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

}
