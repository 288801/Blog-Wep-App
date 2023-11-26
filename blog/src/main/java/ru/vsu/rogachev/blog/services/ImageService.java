package ru.vsu.rogachev.blog.services;

import ru.vsu.rogachev.blog.entities.Image;
import ru.vsu.rogachev.blog.entities.User;

import java.util.List;

public interface ImageService {
    Image findByUrl(String url);

    List<Image> findByUrlList(String url);

    Iterable<Image> findAll();

    boolean existsByUrl(String url);

    Image create(String url);

    void deleteByUrl(String url);
}
