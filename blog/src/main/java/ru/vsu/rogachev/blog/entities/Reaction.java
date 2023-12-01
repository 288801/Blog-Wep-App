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

    @Column(name = "post_id")
    private long postId;

    public Reaction(String userNickname, long postId) {
        this.userNickname = userNickname;
        this.postId = postId;
    }

    public Reaction() {
    }
}
