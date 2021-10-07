package com.sata.blockingqueue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by YotWei on 2018/7/1.
 */
public class BlockingQueue<E> {

    /**
     * 有界队列内部固定长度，因此可以用数组实现
     */
    private Object[] elements;

    /**
     * 队列的头和尾下标
     */
    private int head = 0, tail = 0;

    /**
     * 队列目前的长度
     */
    private int size;

    private ReentrantLock lock = new ReentrantLock();

    private Condition notEmpty = lock.newCondition();

    private Condition notFull = lock.newCondition();

    public BlockingQueue(int capacity) {
        this.elements = new Object[capacity];
    }

    public void put(E e) {
        lock.lock();
        try {
            while (size == elements.length) {
                System.out.println("阻塞队列满了");
                notFull.await(); //队列已满等待
            }
            elements[tail] = e;
            if (++tail == elements.length) {
                tail = 0;
            }
            size++;
            notEmpty.signal(); //队列非空信号

        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public E take() {
        lock.lock();
        E e = null;
        try {
            while (size == 0) {
                System.out.println("阻塞队列空了");
                notEmpty.await(); //队列已空等待
            }
            e = (E) elements[head];
            elements[head] = null;
            if (++head == elements.length)
                head = 0;
            size--;
            notFull.signal(); //队列非满信号

        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            lock.unlock();
        }
        return e;
    }

    public int size() {
        lock.lock();
        try {
            return size;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args){
        BlockingQueue<Integer> queue = new BlockingQueue<>(5);
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                queue.put(i);
                System.out.println("塞入" + i);
                try {
                    Thread.sleep(800);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread t2 = new Thread(() -> {
            for (; ; ) {
                System.out.println("消费"+queue.take());
                try {
                    Thread.sleep(800);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        });
        t1.start();
        t2.start();
    }
}