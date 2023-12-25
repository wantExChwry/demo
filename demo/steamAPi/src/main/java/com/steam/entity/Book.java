package com.steam.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.logging.Logger;

/**
 * @author 吴荣洋
 * @program: demo
 * @description:
 * @date 2023-12-24:23:56
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Book {
    private Long id;
    private String name;
    //分类，多个分类类型
    private String category;
    //评分
    private Integer score;
    //简介
    private String intro;
}
