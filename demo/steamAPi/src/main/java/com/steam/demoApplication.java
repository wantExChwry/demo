package com.steam;

import com.steam.entity.Author;
import com.steam.entity.Book;
import com.steam.utils.AuthorUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * @author 吴荣洋
 * @program: demo
 * @description:
 * @date 2023-12-24:29:44
 */

public class demoApplication {

    public static void main(String[] args) {
        List<Author> authors = AuthorUtils.getAuthors();
        authors.stream()
                .filter(author -> author.getBooks().stream().iterator().next().getName().length()>2)
                .map(new Function<Author, String>() {
                    @Override
                    public String apply(Author author) {
                        return author.getName();
                    }
                })
                .forEach(s -> System.out.println(s));
    }


    public static void createStream(String[] args) {

        //数组创建流
        Integer[] arr = {1,2,3,4,5};
        Stream<Integer> stream = Arrays.stream(arr);
        stream.distinct()
                .filter(integer -> integer>2)
                .forEach(integer -> System.out.println(integer));
        Stream<Integer> arr1 = Stream.of(arr);

        //mao创建stream
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("1","str");
        map.put("2","str2");
        map.put("3","str3");
        //对键操作
        Stream<String> stream1 = map.keySet().stream();
        //对值操作
        Stream<String> stream2 = map.values().stream();
        //对键值对操作
        Stream<Map.Entry<String, String>> stream3 = map.entrySet().stream();

    }


}
