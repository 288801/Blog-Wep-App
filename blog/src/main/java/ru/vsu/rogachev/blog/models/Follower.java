package ru.vsu.rogachev.blog.models;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Data
@Getter
@Setter
@ToString
@Entity
@Table(name = "follower")
public class Follower {

    @JoinColumn(name = "user_nickname")
    @Column(name = "user_nickname")
    private User user;

    @JoinColumn(name = "user_nickname")
    @Column(name = "follower_nickname")
    private User follower;

}
