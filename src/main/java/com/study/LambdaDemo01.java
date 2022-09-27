package com.study;

import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.IntConsumer;
import java.util.function.IntPredicate;

/**
 * 不关注方法，只关注方法中的参数和方法体
 */
public class LambdaDemo01 {
    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println("这是一个lambda表达式");
        });
        //练习1
        int i = calculateNum((left, right) -> left + right);
        System.out.println(i);
        System.out.println("================");
        calculateNum((left, right) -> {
            System.out.println(666);
            return left + right;
        });

        //练习2
        printNum(value -> {
            return value % 2 == 0;//如果是偶数true，则取出来，如果是奇数false，不取
        });
        System.out.println("================");
        //练习3  new Function<T, R>   R apply(T t);
        Integer result = typeConver(s -> {//参数类型是第一个参数类型，返回值类型是第二个参数类型
            return Integer.valueOf(s); //将字符串类型转为Integer
        });
        System.out.println(result);
        System.out.println("================");
        String s =typeConver(s1 -> s1 +"study");
        System.out.println(s);
        System.out.println("===================");
        //练习4
        foreachArr(value -> System.out.println(value));
    }
    //练习4
    public static void foreachArr(IntConsumer consumer){
        int[] arr = {1,2,3,4,5,6,7,8,9,10};
        for (int i : arr) {
            consumer.accept(i);
        }
    }

    //练习3
    public static <R> R typeConver(Function<String, R> function) {
        String str = "1235";
        R result = function.apply(str);//执行方法的return
        return result;
    }

    //练习2
    public static void printNum(IntPredicate predicate) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        for (int i : arr) {
            if (predicate.test(i)) {//为偶数的话为true
                System.out.println(i);
            }
        }
    }

    //练习1
    // 参数是一个函数式接口
    public static int calculateNum(IntBinaryOperator operator) {
        int a = 10;
        int b = 20;
        return operator.applyAsInt(a, b);
    }


}


