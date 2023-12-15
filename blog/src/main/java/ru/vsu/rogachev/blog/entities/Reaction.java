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
@Table(name = "reaction")
public class Reaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "user_nickname")
    private String userNickname;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    public Reaction(String userNickname, Post post) {
        this.userNickname = userNickname;
        this.post = post;
    }

    public Reaction() {
    }
}
