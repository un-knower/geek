package com.tzp.T.java8.LambdaScopes;

import com.tzp.T.java8.FunctionalInterfaces.Converter;

/**
 * author i
 * date 2016/7/25 17:07
 * Lambda作用域
 */
public class Main {
    public static void main(String[] args) {
        /**
         * 访问局部变量
         */
        //通过lambda表达式的作用域读到final类型的局部变量
        final int num = 1;
        Converter<Integer, String> stringConverter =
                (from) -> String.valueOf(from + num);

        stringConverter.convert(2);     // 3

        //不同于匿名对象，变量num 并不需要必须被声明为 final，如下代码同样有效
        int num1 = 1;
        Converter<Integer, String> stringConverter1 =
                (from) -> String.valueOf(from + num1);

        stringConverter1.convert(2);     // 3

        //在lambda表达式里对 num 赋值也同样是被禁止的,编译不过
//        int num2 = 1;
//        Converter<Integer, String> stringConverter2 =
//                (from) -> String.valueOf(from + num2);
//        num2 = 3;

    }

}

/**
 * 访问实例域和静态变量
 */

class Lambda4 {
    static int outerStaticNum;
    int outerNum;

    void testScopes() {
        Converter<Integer, String> stringConverter1 = (from) -> {
            outerNum = 23;
            return String.valueOf(from);
        };

        Converter<Integer, String> stringConverter2 = (from) -> {
            outerStaticNum = 72;
            return String.valueOf(from);
        };
    }
}