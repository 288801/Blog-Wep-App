package ru.vsu.rogachev.blog.models;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Getter
@Setter
@ToString
@Entity
@Table(name = "user")
public class User {

    @NotEmpty(message = "Имя не должно быть пустым")
    @Column(name = "nickname")
    private String username;

    @Column(name = "photo_url")
    @JoinColumn(name = "image_url")
    private Image image;

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
}