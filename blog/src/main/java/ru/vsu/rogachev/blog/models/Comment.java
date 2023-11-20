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
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JoinColumn(name = "user_nickname")
    @Column(name = "user_nickname")
    private User user;

    @JoinColumn(name = "post_id")
    @Column(name = "post_id")
    private Post post;

    @Column(name = "text")
    private String text;
}
