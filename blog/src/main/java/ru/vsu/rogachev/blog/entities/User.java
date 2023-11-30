package ru.vsu.rogachev.blog.entities;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@Getter
@Setter
@ToString
@Entity
@Table(name = "user")
public class User {

    @Id
    @NotEmpty(message = "Имя не должно быть пустым")
    @Column(name = "nickname")
    private String username;

    @Column(name = "photo_url")
    private String imageUrl;

    @UniqueElements
    @NotEmpty(message = "Имя не должно быть пустым")
    @Column(name = "phone_number")
    private String phone_number;

    @UniqueElements
    @NotEmpty(message = "Имя не должно быть пустым")
    @Column(name = "email")
    private String email;

    @NotEmpty(message = "Имя не должно быть пустым")
    @Column(name = "name")
    private String name;

    @NotEmpty(message = "Имя не должно быть пустым")
    @Column(name = "surname")
    private String surname;

    @NotEmpty(message = "Имя не должно быть пустым")
    @Column(name = "password")
    private String password;

    public User(String username, String imageUrl, String phone_number, String email, String name, String surname, String password) {
        this.username = username;
        this.imageUrl = imageUrl;
        this.phone_number = phone_number;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.password = password;
    }
}