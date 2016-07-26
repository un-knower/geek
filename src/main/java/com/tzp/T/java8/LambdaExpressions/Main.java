package com.tzp.T.java8.LambdaExpressions;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * author i
 * date 2016/7/25 16:48
 */
public class Main {
    public static void main(String[] args){
        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");

        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return b.compareTo(a);
            }
        });

        //Lambda1
        Collections.sort(names, (String a, String b) -> {
            return b.compareTo(a);
        });

        //Lambda2
        Collections.sort(names, (String a, String b) -> b.compareTo(a));

        //Lambda3
        names.sort((a, b) -> b.compareTo(a));
    }
}
