package com.sata.multithread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ABCPrint {

    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private String[] ops = new String[] {"a", "b", "c"};
    private volatile String cur = ops[0];

    public void printA(int n) throws InterruptedException {
        for(int i = 0; i < n; i++) {
            printHelper(ops[0]);
        }
    }

    public void printB(int n) throws InterruptedException {
        for(int i = 0; i < n; i++) {
            printHelper(ops[1]);
        }
    }

    public void printC(int n) throws InterruptedException {
        for(int i = 0; i < n; i++) {
            printHelper(ops[2]);
        }
    }

    private void printHelper(String c) throws InterruptedException {
        try{
            lock.lock();
            while(! c.equalsIgnoreCase(cur)) {
                condition.await();
            }
            if(ops[0].equalsIgnoreCase(c)) {
                System.out.println(ops[0]);
                cur = ops[1];
            } else if(ops[1].equalsIgnoreCase(c)) {
                System.out.println(ops[1]);
                cur = ops[2];
            } else if(ops[2].equalsIgnoreCase(c)){
                System.out.println(ops[2]);
                cur = ops[0];
            }
            condition.signalAll();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ABCPrint abcPrint = new ABCPrint();
        new Thread(() -> {
            try {
                abcPrint.printA(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                abcPrint.printB(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                abcPrint.printC(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
