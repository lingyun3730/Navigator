package com.sata.multithread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class CharPrint {

    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private volatile int curNumber = 1;

    public void print1() throws InterruptedException {
        for(char c = 'A'; c <= 'Z'; c += 3) {
            try {
                lock.lock();
                while(curNumber != 1) {
                    condition.await();
                }
                System.out.println(String.format("1%s", c));
                if(c == 'Z') System.out.println("OK");
                curNumber = 2;
                condition.signalAll();
            } finally {
                lock.unlock();
            }
        }
    }

    public void print2() throws InterruptedException {
        for(char c = 'B'; c <= 'Z'; c += 3) {
            try {
                lock.lock();
                while(curNumber != 2) {
                    condition.await();
                }
                System.out.println(String.format("2%s", c));
                if(c == 'Z') System.out.println("OK");
                curNumber = 3;
                condition.signalAll();
            } finally {
                lock.unlock();
            }
        }
    }

    public void print3() throws InterruptedException {
        for(char c = 'C'; c <= 'Z'; c += 3) {
            try {
                lock.lock();
                while(curNumber != 3) {
                    condition.await();
                }
                System.out.println(String.format("3%s", c));
                if(c == 'Z') System.out.println("OK");
                curNumber = 1;
                condition.signalAll();
            } finally {
                lock.unlock();
            }
        }
    }


    public static void main(String[] args) {
        CharPrint charPrint = new CharPrint();
        new Thread(() -> {
            try {
                charPrint.print1();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                charPrint.print2();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                charPrint.print3();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

    }
}
