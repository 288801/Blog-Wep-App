package ru.vsu.rogachev.blog.entities;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@Getter
@Setter
@ToString
@Entity
@Table(name = "follower")
public class Follower {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    @Column(name = "user_nickname")
    private String userNickname;

    @NotEmpty
    @Column(name = "follower_nickname")
    private String followerNickname;

    public Follower(String userNickname, String followerNickname) {
        this.userNickname = userNickname;
        this.followerNickname = followerNickname;
    }

    public Follower() {
    }
}
