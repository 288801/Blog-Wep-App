package ru.vsu.rogachev.blog.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vsu.rogachev.blog.entities.Image;
import ru.vsu.rogachev.blog.repositories.ImageRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    public Image findByUrl(String url) {
        List<Image> images = findByUrlList(url);
        if (images.size() != 0) {
            return images.get(0);
        }
        return null;
    }

    public List<Image> findByUrlList(String url) {
        Optional<Image> images = Optional.ofNullable(imageRepository.findByUrl(url));
        List<Image> res = new ArrayList<>();
        images.ifPresent(res::add);
        return res;
    }

    public Iterable<Image> findAll() {
        return imageRepository.findAll();
    }


    public boolean existsByUrl(String url) {
        return imageRepository.existsByUrl(url);
    }

    public Image create(String url) {
        Image image = new Image(url);
        imageRepository.save(image);
        return image;
    }

    public void deleteByUrl(String url) {
        Image image = imageRepository.findByUrl(url);
        imageRepository.delete(image);
    }
}
