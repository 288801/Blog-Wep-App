package ru.vsu.rogachev.blog.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vsu.rogachev.blog.entities.Image;
import ru.vsu.rogachev.blog.repositories.ImageRepository;
import ru.vsu.rogachev.blog.services.ImageService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Override
    public Image findByUrl(String url) {
        List<Image> images = findByUrlList(url);
        if (images.size() != 0) {
            return images.get(0);
        }
        return null;
    }

    @Override
    public List<Image> findByUrlList(String url) {
        Optional<Image> images = imageRepository.findByUrl(url);
        List<Image> res = new ArrayList<>();
        images.ifPresent(res::add);
        return res;
    }

    @Override
    public Iterable<Image> findAll() {
        return imageRepository.findAll();
    }

    @Override
    public boolean existsByUrl(String url) {
        return imageRepository.existsByUrl(url);
    }

    @Override
    public Image create(String url) {
        Image image = new Image(url);
        imageRepository.save(image);
        return image;
    }

    @Override
    public void deleteByUrl(String url) {
        Image image = imageRepository.findByUrl(url).orElseThrow();
        imageRepository.delete(image);
    }
}
