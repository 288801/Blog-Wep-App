package ru.vsu.rogachev.blog.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
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
