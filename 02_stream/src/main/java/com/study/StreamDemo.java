package com.study;


import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamDemo {
    public static void main(String[] args) {
        List<Author> authors = getAuthors();
        for (Author author : authors) {
            System.out.println(author);
        }
        System.out.println("=======================");
        //调用getAuthors方法获取到作家的集合。现在需要打印所有年龄小于18的作家的名字，并且要注意去重。
        authors.stream().distinct()//去重
                .filter(author -> {
                    System.out.println("test");
                    return author.getAge() < 18;
                })//过略年龄<18的
                .forEach(author -> System.out.println(author.getName()));

        test02();
        System.out.println("=========================");
        //去重收集
        ArrayList<Author> collect = authors.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(()
                -> new TreeSet<>(Comparator.comparing(Author::getName))), ArrayList::new));
        System.out.println(collect);
        System.out.println("===========================");
        //比较器去重
        TreeSet<Author> set = new TreeSet<>(Comparator.comparing(a -> (a.getName() + a.getAge())));
        set.addAll(authors);
        ArrayList<Author> list = new ArrayList<>(set);
        System.out.println(list);
        System.out.println("=================");
        test03();
        System.out.println("=================");
        test05();//打印集合中所有作家的姓名
        test05_1();//对集合中所有作家的年龄+10并打印
        System.out.println("=======================");
        test07();//对流中的元素按照年龄降序排序，且不能要求有重复元素
    }
    //compareTo<Author author>  当前作家年龄和你传入的作家年龄进行比较，相减判断大小
    private static void test07() {
        List<Author> authors = getAuthors();
        authors.stream().distinct()
                .sorted((o1, o2) -> o2.getAge()-o1.getAge())
                .forEach(author -> System.out.println(author.getAge()));
    }

    //map计算操作
    private static void test05_1() {
        List<Author> authors = getAuthors();
        List<Integer> list = authors.stream()
                .map(Author::getAge).map(age -> age + 10).collect(Collectors.toList());
        System.out.println(list);
    }

    //map操作
    private static void test05() {
        List<Author> authors = getAuthors();
        List<String> collect = authors.stream().distinct()
                .map(new Function<Author, String>() {
                    @Override
                    public String apply(Author author) {
                        return author.getName();
                    }
                }).collect(Collectors.toList());
        System.out.println(collect);
    }

    private static void test03() {
        Map<String,Integer> map = new HashMap<>();
        map.put("蜡笔小新",19);
        map.put("黑子",17);
        map.put("日向翔阳",16);
        //map.entrySet(),就是把键和值封装为一个Entry对象
        Stream<Map.Entry<String, Integer>> stream = map.entrySet().stream();
        stream.filter(entry -> entry.getValue()>=17)
                .forEach(entry -> System.out.println(entry));
    }

    private static void test02() {
        //数组stream流
        Integer[] arr = {1,2,3,4,5};
        //Stream<Integer> stream = Arrays.stream(arr);
        Stream<Integer> stream = Stream.of(arr);
        stream.distinct().filter(integer -> integer>=3).forEach(integer -> System.out.println(integer));
    }

    private static List<Author> getAuthors() {
        //数据初始化
        Author author = new Author(1L,"蒙多",33,"一个从菜刀中明悟哲理的祖安人",null);
        Author author2 = new Author(2L,"亚拉索",15,"狂风也追逐不上他的思考速度",null);
        Author author3 = new Author(3L,"易",14,"是这个世界在限制他的思维",null);
        Author author4 = new Author(3L,"易",14,"是这个世界在限制他的思维",null);

        //书籍列表
        List<Book> books1 = new ArrayList<>();
        List<Book> books2 = new ArrayList<>();
        List<Book> books3 = new ArrayList<>();

        books1.add(new Book(1L,"刀的两侧是光明与黑暗","哲学,爱情",88,"用一把刀划分了爱恨"));
        books1.add(new Book(2L,"一个人不能死在同一把刀下","个人成长,爱情",99,"讲述如何从失败中明悟真理"));

        books2.add(new Book(3L,"那风吹不到的地方","哲学",85,"带你用思维去领略世界的尽头"));
        books2.add(new Book(3L,"那风吹不到的地方","哲学",85,"带你用思维去领略世界的尽头"));
        books2.add(new Book(4L,"吹或不吹","爱情,个人传记",56,"一个哲学家的恋爱观注定很难把他所在的时代理解"));

        books3.add(new Book(5L,"你的剑就是我的剑","爱情",56,"无法想象一个武者能对他的伴侣这么的宽容"));
        books3.add(new Book(6L,"风与剑","个人传记",100,"两个哲学家灵魂和肉体的碰撞会激起怎么样的火花呢？"));
        books3.add(new Book(6L,"风与剑","个人传记",100,"两个哲学家灵魂和肉体的碰撞会激起怎么样的火花呢？"));

        author.setBooks(books1);
        author2.setBooks(books2);
        author3.setBooks(books3);
        author4.setBooks(books3);

        List<Author> authorList = new ArrayList<>(Arrays.asList(author,author2,author3,author4));
        return authorList;
    }
}
