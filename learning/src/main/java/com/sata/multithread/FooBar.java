package com.sata.multithread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class FooBar {
    private int n;
    volatile boolean invokeFoo; //轮到调用foo了
    ReentrantLock lock = new ReentrantLock();
    Condition foo = lock.newCondition();
    Condition bar = lock.newCondition();

    public FooBar(int n) {
        this.n = n;
        invokeFoo = true;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {

            // printFoo.run() outputs "foo". Do not change or remove this line.
            lock.lock();
            try {
                if(! invokeFoo) {
                    foo.await();
                }
                printFoo.run();
                invokeFoo = false; //下一个应该invoke bar
                bar.signal();
            } finally {
                lock.unlock();
            }
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            lock.lock();
            try {
                if(invokeFoo) { //正在调用foo，bar应该等会
                    bar.await();
                }
                // printBar.run() outputs "bar". Do not change or remove this line.
                printBar.run();
                invokeFoo = true;
                foo.signal();

            } finally {
                lock.unlock();
            }
        }
    }
}