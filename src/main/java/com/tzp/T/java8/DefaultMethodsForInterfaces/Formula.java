package com.tzp.T.java8.DefaultMethodsForInterfaces;

/**
 * author i
 * date 2016/7/25 16:40
 */
public interface Formula {
    double calculate(int a);

    default double sqrt(int a) {
        return Math.sqrt(a);
    }
}

class Main{
    public static void main(String[] args){
        Formula formula = new Formula() {
            @Override
            public double calculate(int a) {
                return sqrt(a * 100);
            }
        };

        formula.calculate(100);
        formula.sqrt(16);
    }
}
