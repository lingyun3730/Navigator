package com.sata.blockingqueue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * LC 1188
 * 一般来说，ReentrantLock和Condition一同出现，当队列已满时，生产者线程被挂起等待，不再生产数据到队列中，
 * 当队列为空时，消费者线程被挂起等待，等待一个生产者线程生产数据到队列中，才会被激活消费
 */
public class BoundedBlockingQueue {

    ReentrantLock lock = new ReentrantLock();
    Condition notFull = lock.newCondition(); //非满condition
    Condition notEmpty = lock.newCondition(); //非空condition

    int size = 0;//队列当前的大小
    int[] que; //数组
    int head = 0; //队列头
    int tail = 0; //队列尾

    public BoundedBlockingQueue(int capacity) { //初始化数组的capacity
        que = new int[capacity];
    }

    public void enqueue(int element) throws InterruptedException {
        lock.lock();
        try {
            while(size == que.length) {
                notFull.await();
            }
            que[tail++] = element;
            tail %= que.length;
            size++;
            notEmpty.signal();

        } finally {
            lock.unlock();
        }
    }

    public int dequeue() throws InterruptedException {
        lock.lock();
        try{
            while(size == 0) {
                notEmpty.await();
            }
            int element = que[head ++];
            head %= que.length;
            size--;
            notFull.signal();
            return element;
        } finally {
            lock.unlock();
        }
    }

    public int size() {
        lock.lock();
        try {
            return this.size;
        } finally {
            lock.unlock();
        }
    }

}
