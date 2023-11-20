package ru.vsu.rogachev.blog.models;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Data
@Getter
@Setter
@ToString
@Entity
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JoinColumn(name = "user_nickname")
    @Column(name = "user_nickname")
    private User user;

    @Column(name = "image_url")
    @JoinColumn(name = "image_url")
    private Image image;

    @Column(name = "text")
    private String text;
}
