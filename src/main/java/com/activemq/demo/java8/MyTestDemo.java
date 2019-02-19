package com.activemq.demo.java8;

import com.activemq.demo.repository.MyDemo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.*;

/**
 * @author biaoyang
 * @Description:java8功能,练习使用类
 * @date 2018/8/31 00319:26
 */
public class MyTestDemo {

    public static void main(String[] args) {
        List<String> list = Lists.newArrayList("a", "b", "c", "d");
        List<String> li = Lists.newArrayList("a", "c", "e", "f");
        li.forEach((String s) -> {
            list.forEach(a -> {
                if (s.equals(a)) {
                    System.err.println(s);
                    System.out.println(a);
                }
            });
        });

        //排序返回值的写法(不知道这个返回的去处,要么返回-1,要么返回0)
        li.sort((s, a) -> {
            int i = s.compareTo(a);
            return i;
        });

        //map的相关操作(包含可以key重复的情况)
        Map<Object, Object> map = Maps.newIdentityHashMap();
        map.put("b", "BBBBBBBBBBB");
        map.putIfAbsent("b", "bb");//如果缺少key值b,那么就添加,否则不操作absent(缺少的意思)
        map.computeIfPresent("m", (k, v) -> "PP");//如果key存在,则将其value换成新值,否则不做任何操作
        System.err.println(map.toString());
        map.compute("a", (k, v) -> "aa");
        map.compute("a", (k, v) -> "DD");
        map.compute("c", (k, v) -> "FF");
        map.put("e", "ee");
        System.err.println(map.toString());
        map.put(new String("f"), "GG");
        map.put(new String("f"), "MM");
        System.err.println(map.toString());

        //这里是类似多态的形式实例化接口,只是通过lambda表达式直接实现接口的方法的方式实现,不再是通过实现类的new等实例化
        //因为lambda这里只允许有一个抽象方法,所以这里覆盖的必然只有接口里定义的那个抽象方法，当然了因为接口还允许写默认的方法和静态方法
        //当然了,不太建议使用默认方法,因为导致一些问题
        MyDemo demo = () -> {
            System.err.println("我是重写的方法");
        };
        demo.method();
        demo.get("aaa");

        //常用的普通实现接口的方式
        Runnable runnable = new Runnable() {
            @Override
            public void run() {

            }
        };
        //拉姆达(lambda)的方式实现方式
        Runnable runn = () -> {
            //重写run方法
            System.err.println("我实现了Runnable的run方法了");
        };
        runn.run();

        //开启线程
        //new Thread(runn).run();//这里就是把上面的那个直接runn直接换成了lambda表达式而已(看起来复杂了些)
        Thread thread = new Thread(() -> {
            //重写run方法
            for (int i = 0; i < 10; i++) {
                System.err.println("我实现了Runnable的run方法了" + i + "次");
            }
        });
        thread.run();

        //
//        Optional<String> s = Optional.of(null);
//        Optional<String> s = Optional.ofNullable(null);
//        String aa = s.orElse("aa");
//        s.orElseGet(()->"sss");
//        System.out.println(s.toString());
//        System.out.println(s.get()+""+s.isPresent());
//        System.out.println(s);

        //
        Optional<String> fullName = Optional.ofNullable(null);
        System.out.println("Full Name is set? " + fullName.isPresent());
        System.out.println("Full Name: " + fullName.orElseGet(() -> "[none]"));
        System.out.println(fullName.map(ss -> "Hey " + ss + "!").orElse("Hey Stranger!"));
    }
}
