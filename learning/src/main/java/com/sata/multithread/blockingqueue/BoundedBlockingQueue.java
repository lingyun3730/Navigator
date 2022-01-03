package com.sata.multithread.blockingqueue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * LC 1188
 * 一般来说，ReentrantLock和Condition一同出现，当队列已满时，生产者线程被挂起等待，不再生产数据到队列中，
 * 当队列为空时，消费者线程被挂起等待，等待一个生产者线程生产数据到队列中，才会被激活消费
 */
public class BoundedBlockingQueue {

    ReentrantLock lock = new ReentrantLock();
    Condition notFull = lock.newCondition();
    Condition notEmpty = lock.newCondition();
    int capacity;
    int[] que;
    int rear;
    int front;
    int size;

    public BoundedBlockingQueue(int capacity){
        this.capacity = capacity;
        que = new int[capacity];
        front = 0;
        rear = -1;
        size = 0;
    }

    public void enqueue(int element) {
        lock.lock();
        try {
            if(size == capacity) { //队列已满
                notFull.await();
            }
            rear = (rear + 1) % capacity;
            que[rear] = element;
            size ++;
            notEmpty.signal();
        } catch(InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public int dequeue() throws InterruptedException {
        lock.lock();
        try {
            if(size == 0) {
                notEmpty.await();
            }
            int res = que[front];
            front = (front + 1) % capacity;
            size--;
            notFull.signal();
            return res;
        } finally {
            lock.unlock();
        }
    }

    public int size() {
        lock.lock();
        try {
            return size;
        }finally {
            lock.unlock();
        }
    }
}
