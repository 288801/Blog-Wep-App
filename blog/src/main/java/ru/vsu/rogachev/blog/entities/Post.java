package ru.vsu.rogachev.blog.entities;


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

    @Column(name = "user_nickname")
    private String userNickname;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "text")
    private String text;

    public Post(String userNickname, String imageUrl, String text) {
        this.userNickname = userNickname;
        this.imageUrl = imageUrl;
        this.text = text;
    }
}
