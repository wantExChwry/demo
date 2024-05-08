package com.steam;

import com.steam.entity.Author;
import com.steam.entity.Book;
import com.steam.utils.AuthorUtils;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author 吴荣洋
 * @program: demo
 * @description:
 * @date 2023-12-24:29:44
 */

public class demoApplication {
    private static List<Author> authors = AuthorUtils.getAuthors();

    public static void main(String[] args) {
        reducing(args);
    }


    /**
     * groupingBy中reducing的用法
     * 应用场景：求一个集合中相同元素的平均值/最大值/最小值，返回的数据中相同元素的只有一个（返回分组后的数据）。
     *
     */
    //仅返回groupby分组的name和求和的age
    public static void reducing(String[] args){
        List<Author> list = authors;
        list.addAll(authors);
        List<Author> collect = list.stream().collect(Collectors.groupingBy(Author::getName, Collectors.reducing(0, Author::getAge, Integer::sum)))
                .entrySet().stream().map(entry -> new Author(entry.getKey(), entry.getValue())).collect(Collectors.toList());
        System.out.println(collect);
        //保留其他属性
        List<Author> collect1 = list.stream().collect(Collectors.groupingBy(Author::getName, Collectors.reducing((l1, l2) -> {
            Integer run = l1.getAge() + l2.getAge();
            return new Author(l1.getId(), l1.getName(), run, l1.getIntro(), l1.getBooks());
        }))).entrySet().stream().map(entry -> new Author(entry.getKey(), entry.getValue().get())).collect(Collectors.toList());
        System.out.println(collect1);
    }

    /**
     * stream注意事项：
     * 1.惰性求值：如果没有终结操作、没有中间操作，流不会得到执行
     * 2.流是一次性的：一旦流经过一个终结操作后，这个流就不能在使用，报废
     * 3.不会影响数据：流中处理了很多数据，正常情况下不会影响到原本集合中的数据，除非在流使用过程中改变set了原来的对象
     */

    public static void reduce(String[] args) {
        /**
         * reduce:归并
         * 对流中的元素按照指定的计算方式计算出一个结果。（缩减操作）
         * reduce的作用是把stream中的元素组合起来，我们可以传入一个初始值，它会按照指定计算方式依次拿流中的元素在
         * 初始化值的基础上计算，计算出结果后再一次和后面的元素计算
         * reduce重载形式内部计算方式如下：
         * T result = identity
         * for(T element : this stream )
         *      result = accmulator.apply(result,element)
         * return result
         * 其中identity是通过方法参数传入的初始值 apply具体进行什么计算也是通过传入方法参数来确定
         */
        //使用两个参数求所有作者年龄的和
        //reduce之前需要转化为计算需要的类型
        System.out.println("---------两个参数");
        Integer reduce = authors.stream()
                .map(Author::getAge)
                .distinct()
                .reduce(0, new BinaryOperator<Integer>() {
                    @Override
                    public Integer apply(Integer integer, Integer integer2) {
                        return integer + integer2;
                    }
                });
        System.out.println(reduce);

        //使用reduce求所有作家中年龄最大
        //我的
        Integer reduce1 = authors.stream()
                .map(Author::getAge)
                .distinct()
                .reduce(0, new BinaryOperator<Integer>() {
                    @Override
//                    public Integer apply(Integer result, Integer element) {
                    public Integer apply(Integer integer, Integer integer2) {
//                        return Math.max(integer, integer2);
                        return integer>integer2?integer:integer2;
                    }
                });
        //b站三更 拿integer最小值和元素比较
        Integer reduceBzhan = authors.stream()
                .map(Author::getAge)
                .distinct()
                .reduce(Integer.MIN_VALUE, new BinaryOperator<Integer>() {
                    @Override
                    public Integer apply(Integer integer, Integer integer2) {
//                        return Math.max(integer, integer2);
                        return integer>integer2?integer:integer2;
                    }
                });
        System.out.println(reduce1);

        System.out.println("-----------------");
        //获取年龄最小值
        Integer reduceMinBzhan = authors.stream()
                .map(Author::getAge)
                .distinct()
                .reduce(Integer.MAX_VALUE, new BinaryOperator<Integer>() {
                    @Override
                    public Integer apply(Integer integer, Integer integer2) {
//                        return Math.max(integer, integer2);
                        return integer>integer2?integer2:integer;
                    }
                });
        System.out.println(reduceMinBzhan);

        System.out.println("-----------一个参数");
        /**
         *      单个参数接口实现
         *      boolean foundAny = false;
         *      T result = null;
         *      for (T element : this stream) {
         *          if (!foundAny) {
         *              foundAny = true;
         *              result = element;
         *          }
         *          else
         *              result = accumulator.apply(result, element);
         *      }
         *      return foundAny ? Optional.of(result) : Optional.empty();
         *      将第一个元素作为result，如果是空的流返回空
         */
        Optional<Integer> reduce2 = authors.stream()
                .map(Author::getAge)
                .reduce(new BinaryOperator<Integer>() {
                    @Override
                    public Integer apply(Integer integer, Integer integer2) {
                        return Math.max(integer, integer2);
                    }
                });
        reduce2.ifPresent(integer -> System.out.println(integer));

    }

    public static void find(String[] args) {
        /**
         * findAny:获取流中任意一个元素。随机获取
         */
        //获取年龄大于18的作家，如果存在则输出名字
        Optional<Author> any = authors.stream()
                .filter(author -> author.getAge() > 18)
                .findAny();
        any.ifPresent(author -> System.out.println(author.getName()));

        /**
         * findFist：获取流中第一个元素
         */

        //获取年龄最小的作家，输出名字
        Optional<Author> first = authors.stream()
                .sorted((o1, o2) -> o1.getAge()- o2.getAge())
                .findFirst();
        first.ifPresent(author -> System.out.println(author));
    }

    public static void match(String[] args) {
        /**
         * anyMatch判断是否有任意符号匹配条件的元素，返回值为boolean
         */
        //判断是否有年龄在20岁以上的作家

        boolean b = authors.stream()
                .anyMatch(author -> author.getAge() > 29);
        System.out.println(b);

        /**
         * allMatch:所有元素都匹配，返回true
         */
        boolean anyMatch = authors.stream()
                .allMatch(author -> author.getAge() >= 18);
        System.out.println(anyMatch);

        /**
         * noneMatch:判断所有的元素是否都不符合条件，都不符合返回true
         * 注意：是都不符合
         */

        boolean noneMatch = authors.stream()
                .noneMatch(author -> author.getBooks().size() < 1);
        System.out.println(noneMatch);
    }
    public static void Collect(String[] args) {
        /**
         * collect 流转化为一个集合
         */
        //获取一个存放所有作家的集合
        List<Author> authors = AuthorUtils.getAuthors();
        List<String> collect = authors.stream()
                .map(author -> author.getName())
                .distinct()
                .collect(Collectors.toList());
        collect.stream()
                .forEach(s -> System.out.println(s));

        //获取书名的set集合
        Set<String> collect1 = authors.stream()
                .flatMap(author -> author.getBooks().stream())
                .map(book -> book.getName())
                .collect(Collectors.toSet());
        collect1.stream()
                .forEach(s -> System.out.println(s));

        //获取一个map集合，key为作者名 value为List<Book>
        Map<String, List<Book>> map = authors.stream()
                .distinct()
                //看传入的参数，ctr+P，需要什么就new什么
                //map 第一个是传入key，第二个匿名内部类是传入value
                .collect(Collectors.toMap(new Function<Author, String>() {
                    @Override
                    public String apply(Author author) {
                        return author.getName();
                    }
                }, new Function<Author, List<Book>>() {
                    @Override
                    public List<Book> apply(Author author) {
                        return author.getBooks().stream().distinct().collect(Collectors.toList());
                    }
                }));
        //逐步演变
        authors.stream()
                .distinct()
                .collect(Collectors.toMap(author -> author.getName(), author -> author));
        authors.stream()
                .distinct()
                .collect(Collectors.toMap(author -> author.getName(), a -> a));
        authors.stream()
                .distinct()
                .collect(Collectors.toMap(Author::getName, author -> author));

    }

    public static void CountMaxMin(String[] args) {
        List<Author> authors = AuthorUtils.getAuthors();
        // 终结操作：count 计数
        long count = authors.stream()
                .flatMap(author -> author.getBooks().stream())
                .distinct()
                .count();
        System.out.println(count);

        //获取最大最小值
        Integer max = authors.stream()
                .flatMap(author -> author.getBooks().stream())
                .map(book -> book.getScore())
                .max((o1, o2) -> o1 - o2)
                .get();
        System.out.println("max="+max);

        Integer min = authors.stream()
                .flatMap(author -> author.getBooks().stream())
                .map(book -> book.getScore())
                .min((o1, o2) -> o1-o2)
                .get();
        System.out.println("min="+min);


    }

    public static void flatMap(String[] args) {
        /**
         * map只能转换元素的类型
         * flatMap可以把一个对象转换成多个对象作为流中的元素
         * flatMap() 操作的效果是对数据流中的元素进行一对多的转换，然后将转换后的元素平铺到一个新的数据流中。
         *
         */
        //打印所有书籍的名字，重复元素去重;场景：集合中有Book集合，想要获取单个book的stream（Stream(Book)而非Stream<List<Book>>）
        List<Author> authors = AuthorUtils.getAuthors();
        authors.stream()
                .flatMap(new Function<Author, Stream<Book>>() {
                    @Override
                    public Stream<Book> apply(Author author) {
                        //此时可以对元素过滤等，可以再一次进行流操作
                        //里面的distinct只能对该里面的单个集合进行去重，不能整体去重，整体去重得在外部
                        return author.getBooks().stream();
//                                .distinct();
                    }
                })
                .distinct()
                .forEach(book -> System.out.println(book.toString()));
        System.out.println("---------------------");

        //打印现有数据的所有分类，分类去重，不能出现、；，即不能出现标点符号
        authors.stream()
                //获取书籍
                .flatMap(author -> author.getBooks().stream())
                .distinct()
                //再将书籍的分类拆开 一转化多 使用flatMap
                //拆开后为数组，数组转化为流使用Arrays.stream
                .flatMap(book -> Arrays.stream(book.getCategory().split("、")).distinct())
                .distinct()
                .forEach(s -> System.out.println(s.toString()));

    }


    public static void skip(String[] args) {
        List<Author> authors = AuthorUtils.getAuthors();
        /**
         * 跳过流中前面n个元素，返回余下的元素
         */
        // 打印除了年龄最大作家外的其他作家，去重，降序排序
        authors.stream()
                .distinct()
                .sorted((o1, o2) -> o2.getAge()-o1.getAge())
                .skip(1)
                .forEach(author -> System.out.println(author.getName()+author.getAge()));

    }

    public static void limit(String[] args) {
        List<Author> authors = AuthorUtils.getAuthors();
        /**
         * limit:设置流最大长度，超出最大长度，超出部分的元素将被摧毁
         */
        //降序排序，去重，打印年龄最大
        authors.stream()
                .distinct()
                .sorted((o1, o2) -> o1.getAge()-o2.getAge())
                .limit(2)
                .forEach(author -> System.out.println(author.getName() + author.getAge()));
        System.out.println();

    }
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
                 * 2.写法：获取过滤条件的类型，根据数据类型过滤 使用判断符号
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
