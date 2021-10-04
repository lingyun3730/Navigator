package com.sata.others.masterworker;

import lombok.Setter;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

@Setter
public class Worker implements Runnable{

    private ConcurrentLinkedQueue<Task> taskQueue;
    private ConcurrentHashMap<String, Object> resultMap;

    @Override
    public void run() {
        while(true){
            Task executeTask = this.taskQueue.poll();
            if(executeTask == null) break;
            //真正的任务处理
            Object result = handle(executeTask);
            this.resultMap.put(executeTask.getName(),result);
        }

    }

    private Object handle(Task executeTask) {
        Object result = null;
        try {
            Thread.sleep(500);
            result = executeTask.getPrice();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }

}
