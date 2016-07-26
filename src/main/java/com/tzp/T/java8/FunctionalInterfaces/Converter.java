package com.tzp.T.java8.FunctionalInterfaces;

/**
 * author i
 * date 2016/7/25 16:58
 */
@FunctionalInterface //@FunctionalInterface 这个注解被遗漏，此代码依然有效。
public interface Converter<F, T> {
    T convert(F from);
}

class Main{
    public static void main(String[] args){
        Converter<String, Integer> converter = (from) -> Integer.valueOf(from);
        Integer converted = converter.convert("888");
        System.out.println(converted);
    }
}
