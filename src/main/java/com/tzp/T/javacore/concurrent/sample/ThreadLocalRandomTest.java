package com.tzp.T.javacore.concurrent.sample;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 说明：从Math.random()改变到ThreadLocalRandom有如下好处：
 我们不再有从多个线程访问同一个随机数生成器实例的争夺。
 取代以前每个随机变量实例化一个随机数生成器实例，我们可以每个线程实例化一个。
 */
public class ThreadLocalRandomTest {
    public static void main(String[] args){
        int u = ThreadLocalRandom.current().nextInt(10);
        System.out.println(u);
    }
}
