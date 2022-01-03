package com.sata.multithread.prodconsumer;

import java.util.concurrent.ArrayBlockingQueue;

public class Producer implements Runnable {
    private int taskNum;
    private ArrayBlockingQueue<Integer> queue;

    public Producer(int taskNum, ArrayBlockingQueue<Integer> queue) {
        this.taskNum = taskNum;
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("start produce");
        queue.add(taskNum);
    }
}
