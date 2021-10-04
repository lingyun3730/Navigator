package com.sata.blockingqueue;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class CrossPrint {
    private ReentrantLock lock = new ReentrantLock();
    private Condition odd = lock.newCondition();
    private Condition even = lock.newCondition();
    private int currentNumber = 1;

    public void printOdd() {
        lock.lock();
        try {
            while(currentNumber % 2 == 0) { //当前是偶数
                try {
                    odd.await();
                } catch(InterruptedException ex) {
                    odd.signal();
                    ex.printStackTrace();
                }
            }
            System.out.println(currentNumber);
            currentNumber ++;
            even.signal();
        } finally {
            lock.unlock();
        }

    }

    public void printEven() {
        lock.lock();
        try {
            while(currentNumber % 2 == 1) { //当前是奇数
                try {
                    even.await();
                } catch(InterruptedException ex) {
                    even.signal();
                    ex.printStackTrace();
                }
            }
            System.out.println(currentNumber);
            currentNumber ++;
            odd.signal();
        } finally {
            lock.unlock();
        }
    }



    //交替打印1-10000
    public static void main(String[] args) {

        CrossPrint crossPrint = new CrossPrint();

        Thread oddp = new Thread(() -> {
            for(int i = 0; i < 5000; i++) {
                crossPrint.printOdd();
            }
        });

        Thread evenp = new Thread(() -> {
            for(int i = 0; i < 5000; i++) {
                crossPrint.printEven();
            }
        });

        oddp.start();
        evenp.start();
    }
}
