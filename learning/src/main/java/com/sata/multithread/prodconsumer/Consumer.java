package com.sata.multithread.prodconsumer;

import java.util.concurrent.ArrayBlockingQueue;

public class Consumer implements Runnable {

    private ArrayBlockingQueue<Integer> queue;

    public Consumer(ArrayBlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        System.out.println("start consume");
        int taskNum;
        try {
            taskNum = queue.take();
            System.out.println(String.format("consume %d", taskNum));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
 }
