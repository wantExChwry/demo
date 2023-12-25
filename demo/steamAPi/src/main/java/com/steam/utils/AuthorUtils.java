package com.steam.utils;

import com.steam.entity.Author;
import com.steam.entity.Book;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 吴荣洋
 * @program: demo
 * @description:
 * @date 2023-12-24:22:56
 */

public class AuthorUtils {
    public static List<Author> getAuthors(){
    Author author = new Author(1L, "蒙多",33, "祖安人", null);
    Author author2 = new Author(1L, "亚索",15, "快乐风男", null);
    Author author3 = new Author(3L, "易",14, "世界在限制他的思维", null);
    Author author4 = new Author(3L, "易",14, "世界在限制他的思维", null);

    //书籍列表
    List<Book> books1 = new ArrayList<>();
    List<Book> books2 = new ArrayList<>();
    List<Book> books3 = new ArrayList<>();

    books1.add(new Book( 1L, "刀的两侧是光明与黑","哲学,爱情",88,"用一把刀划分了爱恨"));
    books1.add(new Book( 1L, "一个人不能死在同一把刀下","个人成长,爱情",99,"讲述如何从失败到成功"));

    books2.add(new Book( 3L,"那风吹不到的地方","哲学",85,"带你用思维去领略世界的尽头"));
    books2.add(new Book( 3L,"那风吹不到的地方","哲学",85,"带你用思维去领略世界的尽头"));
    books2.add(new Book( 3L,"吹或不吹","爱情,个人传记",56,"一个哲学家的恋爱观注定很难把他"));

    books3.add(new Book(5L,"你的剑就是我的剑","爱情",56,"无法想象一个武者能对他的伴侣这么残忍"));
    books3.add(new Book(5L,"风与剑","个人传记",100,"两个哲学家灵魂和肉体的碰撞会激起怎么的火花"));
    books3.add(new Book(5L,"风与剑","个人传记",100,"两个哲学家灵魂和肉体的碰撞会激起怎么的火花"));

    author.setBooks(books1);
    author2.setBooks(books2);
    author3.setBooks(books3);
    author4.setBooks(books3);

    List<Author> authorList = new ArrayList<>(Arrays.asList(author,author2,author3,author4));
    return authorList;

}
}
