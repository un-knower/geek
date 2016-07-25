package com.tzp.T.javacore.concurrent.md.Chapter01.code.BlockingQueue;

import java.util.concurrent.BlockingQueue;

/**
 * Created by Administrator on 2016/7/12.
 */
public class Producer implements Runnable{
    private BlockingQueue queue;

    public Producer(BlockingQueue queue) {
        this.queue = queue;
    }

    public void run() {
        for (int product = 1; product <= 10; product++) {
            try {
                Thread.sleep((int) Math.random() * 3000);
                queue.put(product);
                System.out.println("Producer 生产： " + product);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
