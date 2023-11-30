package ru.vsu.rogachev.blog.entities;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Data
@Getter
@Setter
@ToString
@Entity
@Table(name = "follower")
public class Follower {

    @NotEmpty
    @Column(name = "user_nickname")
    private String userNickname;

    @NotEmpty
    @Column(name = "follower_nickname")
    private String followerNickname;

}
