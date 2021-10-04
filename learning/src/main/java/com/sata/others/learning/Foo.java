package com.sata.others.learning;

class Foo {

    static boolean[] t = new boolean[2];
    public Foo() {

    }

    public static void first(Runnable printFirst) throws InterruptedException {
        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        t[0] = true;
    }

    public static void second(Runnable printSecond) throws InterruptedException {
        while(!t[0]);

        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        t[1] = true;
    }

    public static void third(Runnable printThird) throws InterruptedException {
        while(!t[0] && !t[1]);
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            try {
                first(() -> System.out.println("first"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                second(() -> System.out.println("second"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t3 = new Thread(() -> {
            try {
                second(() -> System.out.println("third"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();
        t3.start();
    }
}