package com.sata.others.learning;

public class ThreadCreationCmp{
    public static void main(String[] args){
        Thread t;
        CountingTask countingTask = new CountingTask();
        final int numberOfProcessors = Runtime.getRuntime().availableProcessors();
        for(int i = 0; i< 2*numberOfProcessors; i++){
            t = new Thread(countingTask);
            t.start();
        }

        for(int i = 0; i< 2*numberOfProcessors; i++){
            t= new CountingThread();
            t.start();
        }
    }
    static class Counter{
        private int count = 0;

        public int value(){
            return count;
        }

        public void increment(){
            count++;
        }
    }

    static class CountingTask implements Runnable {
        private Counter counter = new Counter();

        private void doSomeThing() throws InterruptedException {
            Thread.sleep(80);
        }
        @Override
        public void run() {
            for(int i = 0; i<100; i++) {
                try{
                    doSomeThing();
                }catch(Exception e){
                    e.printStackTrace();
                }
                counter.increment();
            }
            System.out.println("CountingTask:" + counter.value());
        }

    }

    static class CountingThread extends Thread {
        private Counter counter = new Counter();

        private void doSomeThing() throws InterruptedException {
            Thread.sleep(80);
        }
        @Override
        public void run() {
            for(int i = 0; i<100; i++) {
                try{
                    doSomeThing();
                }catch(Exception e){
                    e.printStackTrace();
                }
                counter.increment();
            }
            System.out.println("CountingThread:" + counter.value());
        }
    }
}

