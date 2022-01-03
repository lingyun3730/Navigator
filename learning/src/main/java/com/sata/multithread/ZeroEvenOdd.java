package com.sata.multithread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;

class ZeroEvenOdd {
    private volatile int n;
    ReentrantLock lock = new ReentrantLock();
    Condition condition = lock.newCondition();
    final String[] methods = {"ZERO", "EVEN", "ODD"};
    private volatile String curentMethod = methods[0];
    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i < n+1; i++)
            job(printNumber, 0, methods[0]);
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i < n+1; i++)
            job(printNumber, i, methods[1]);
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i < n+1; i++)
            job(printNumber, i, methods[2]);
    }

    void job(IntConsumer printN, int cou, String method) throws InterruptedException{
        try {
            lock.lock();
            while (! method.equals(curentMethod))
                condition.await();
            if (method.equals(methods[0])){
                curentMethod = methods[1];
                printN.accept(0);
            } else if (method.equals(methods[1])){
                curentMethod = methods[2];
                if (cou % 2 == 0)
                    printN.accept(cou);
            } else if (method.equals(methods[2])){
                curentMethod = methods[0];
                if (cou % 2 != 0)
                    printN.accept(cou);
            }
            condition.signalAll();
        } finally {
            lock.unlock();
        }

    }

    public static void main(String[] args) {
        ZeroEvenOdd t = new ZeroEvenOdd(10);
        new Thread(() -> {
            try {
                t.zero(System.out::println);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                t.even(System.out::println);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                t.odd(System.out::println);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

}