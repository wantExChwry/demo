package com.steam;

import com.steam.entity.Author;
import com.steam.entity.Book;
import com.steam.utils.AuthorUtils;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * @author 吴荣洋
 * @program: demo
 * @description:
 * @date 2023-12-24:29:44
 */

public class demoApplication {
    public static void sortedStream(String[] args) {

        /**
         * 如果调用了空参的sorted，需要流中的元素需要实现Comparable接口
         * 调用有参匿名内部类，在方法参数的01和02比较
         * 区别：sorted()实现接口sorted(Comparator<? super T> comparator);匿名内部了
         */

        List<Author> authors = AuthorUtils.getAuthors();
        authors.stream()
                .distinct()
                .sorted()
                .forEach(author -> System.out.println(author.getAge()));
        authors.stream()
                .distinct()
                .sorted(new Comparator<Author>() {
                    @Override
                    public int compare(Author o1, Author o2) {
                        return o1.getAge() - o2.getAge();
                    }
                })
                .forEach(author -> System.out.println(author.getAge()));
    }


    public static void maoStream(String[] args) {
        List<Author> authors = AuthorUtils.getAuthors();
        authors.stream()
                /**
                 * filter(Predicate<? super T> predicate);
                 * 1.定义为过滤：排除不符合某个条件的元素
                 * 2.写法：获取过滤条件的类型，根据数据类型过滤
                 */
                .filter(author -> author.getBooks().stream().iterator().next().getName().length() > 2)
                /**
                 * 类型转换 map(Function<? super T, ? extends R> mapper)
                 * 如下的例子：接收Author类型，转化为String类型
                 */
                .map(new Function<Author, String>() {
                    @Override
                    public String apply(Author author) {
                        return author.getName();
                    }
                })
                //map也可大小写转化
                .map((String)->String.toLowerCase())
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
