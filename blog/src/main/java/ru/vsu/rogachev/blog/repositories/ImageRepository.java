package ru.vsu.rogachev.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.vsu.rogachev.blog.entities.Image;

import java.util.List;

@Repository
public interface ImageRepository  extends CrudRepository<Image, String> {
    Image findByUrl(String url);

    List<Image> findByUrlList(String url);

    List<Image> findAll();

    boolean existsByUrl(String url);

    Image create(String url);

    void deleteByUrl(String url);
}
