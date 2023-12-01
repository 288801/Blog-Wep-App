package ru.vsu.rogachev.blog.entities;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.apachecommons.CommonsLog;

import javax.persistence.*;
import java.util.Date;

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

    @Column(name = "header")
    private String header;

    @Column(name = "date")
    private Date date;

    public Post(String userNickname, String imageUrl, String text, String header, Date date) {
        this.userNickname = userNickname;
        this.imageUrl = imageUrl;
        this.text = text;
        this.header = header;
        this.date = date;
    }

    public Post() {
    }
}
