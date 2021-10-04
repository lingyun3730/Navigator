package com.sata.others.learning;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class WorkerThread {
    public static void main(String[] args) {
        Helper helper = new Helper();
        helper.init();
        //Helper 线程的客户端线程是 main线程
        helper.submit("Something...");
    }

    static class Helper{
        private final BlockingQueue<String> workQueue = new ArrayBlockingQueue<String>(100);
        //worker threads to process tasks in Queue
        private final Thread workerThread = new Thread() {
            @Override
            public void run(){
                String task = null;
                while(true){
                    try{
                        task = workQueue.take();
                    } catch (InterruptedException e){
                        break;
                    }
                    System.out.println(doProcess(task));
                }
            }
        };

        public void init(){
            workerThread.start();
        }

        protected  String doProcess(String task) {
            return task + "->processed";
        }

        public void submit(String task) {
            try{
                workQueue.put(task);
            }catch(InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
