package ru.vsu.rogachev.blog.models;

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
@Table(name = "image")
public class Image {

    @NotEmpty(message = "Имя не должно быть пустым")
    @Column(name = "image_url")
    private String url;

}
