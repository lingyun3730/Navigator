package com.sata.others.masterworker;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Master {
    //task collection
    private ConcurrentLinkedQueue<Task> taskQueue = new ConcurrentLinkedQueue<>();

    //result collection
    private ConcurrentHashMap<String,Object> resultMap = new ConcurrentHashMap<>();

    //worker collection
    private HashMap<String,Thread> workerMap = new HashMap<>();

    public Master(Worker worker, int workerCount) {
        worker.setTaskQueue(this.taskQueue); // refer to Master task collection
        worker.setResultMap(this.resultMap); // refer to Master result collection
        for(int i = 0 ;i < workerCount; i++){
            //key表示worker的名字,value表示线程执行对象
            workerMap.put("worker"+i,new Thread(worker));
        }
    }

    //用于提交任务
    public void submit(Task task){
        this.taskQueue.add(task);
    }

    //执行方法，启动应用程序让所有的Worker工作
    public void execute(){
        for(Map.Entry<String,Thread> me : workerMap.entrySet()){
            me.getValue().start();
        }
    }

    //判断所有的线程是否都完成任务
    public boolean isComplete() {
        for(Map.Entry<String,Thread> me : workerMap.entrySet()){
            if(me.getValue().getState() != Thread.State.TERMINATED){
                return false;
            }
        }
        return true;
    }

    //总结归纳
    public int getResult(){
        int ret = 0;
        for (Map.Entry<String, Object> entry : resultMap.entrySet()) {
            ret+=(Integer) entry.getValue();
        }
        return ret;
    }


}
