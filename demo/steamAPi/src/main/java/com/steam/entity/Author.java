package com.steam.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.logging.Logger;

/**
 * @author 吴荣洋
 * @program: demo
 * @description:
 * @date 2023-12-24:21:54
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Author {
    private Long id;
    private String  name;
    private Integer age;
    //简介
    private String intro;
    //作品
    private List<Book> books;

    public Author(String key, Integer value) {
        this.name = key;
        this.age = value;
    }

    public Author(String key, Author author) {
        this.id = author.getId();
        this.name = author.getName();
        this.age = author.getAge();
        this.intro = author.getIntro();
        this.books = author.getBooks();
    }
}
