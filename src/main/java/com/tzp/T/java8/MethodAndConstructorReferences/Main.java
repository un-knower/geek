package com.tzp.T.java8.MethodAndConstructorReferences;

import com.tzp.T.java8.FunctionalInterfaces.Converter;

/**
 * author i
 * date 2016/7/25 17:03
 * 方法和构造器引用
 */
public class Main {
    public static void main(String[] args) {
        Converter<String, Integer> converter = Integer::valueOf;
        Integer converted = converter.convert("123");
        System.out.println(converted);   // 123


        //通过Person::new创建了一个对 Person 构造器的引用。Java编译器通过匹配PersonFactory.create标记自动选择合适的构造器
        PersonFactory<Person> personFactory = Person::new;
        Person person = personFactory.create("Peter", "Parker");
    }
}
class Something {
    String startsWith(String s) {
        return String.valueOf(s.charAt(0));
    }
}

class Person {
    String firstName;
    String lastName;

    Person() {}

    Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}

interface PersonFactory<P extends Person> {
    P create(String firstName, String lastName);
}