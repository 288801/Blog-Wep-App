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
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "user_nickname")
    private String userNickname;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @Column(name = "text")
    private String text;

    public Comment(String userNickname, Post post, String text) {
        this.userNickname = userNickname;
        this.post = post;
        this.text = text;
    }

    public Comment() {
    }
}
