package ru.vsu.rogachev.blog.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "user")
public class User {

    @Id
    @Column(name = "nickname")
    private String nickname;

    @NotEmpty
    @Column(name = "photo_url")
    private String photoUrl;

    @NotEmpty
    @Column(name = "phone_number")
    private String phoneNumber;

    @NotEmpty
    @Column(name = "email")
    private String email;

    @NotEmpty
    @Column(name = "name")
    private String name;

    @NotEmpty
    @Column(name = "surname")
    private String surname;

    @Column(name = "password")
    private String password;

    public User(String nickname, String photoUrl, String phoneNumber, String email, String name, String surname, String password) {
        this.nickname = nickname;
        this.photoUrl = photoUrl;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "nickname='" + nickname + '\'' +
                ", photoUrl='" + photoUrl + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
