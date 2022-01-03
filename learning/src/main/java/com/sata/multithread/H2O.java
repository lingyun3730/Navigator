package com.sata.multithread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class H2O {

    private ReentrantLock lock = new ReentrantLock();

    private Condition condition = lock.newCondition();

    char h = 'h';

    char o = 'o';

    int hn;

    int on;

    public H2O() {
        hn = 0;
        on = 0;
    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {

        // releaseHydrogen.run() outputs "H". Do not change or remove this line.
        helper(releaseHydrogen, h);
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {

        // releaseOxygen.run() outputs "O". Do not change or remove this line.
        helper(releaseOxygen, o);
    }

    private void helper(Runnable ops, char c) throws InterruptedException {
        try {
            lock.lock();
            if(c == h) {
                while(hn > 1) {
                    condition.await();
                }
                ops.run();
                hn ++;
            } else if(c == o) {
                while(on == 1) {
                    condition.await();
                }
                ops.run();
                on ++;

            }
            if(hn == 2 && on == 1) {
                hn = 0;
                on = 0;
            }
            condition.signal();
        }finally {
            lock.unlock();
        }
    }

}
