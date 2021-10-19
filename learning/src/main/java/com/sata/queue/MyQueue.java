package com.sata.queue;

/**
 * 实现一个队列 cyclic
 */
public class MyQueue {
    private int[] queue;
    private int size;
    private int count;
    private int front; //front has value
    private int rear; //rear no value

    MyQueue(int n) {
        size = n;
        queue = new int[size];
        front = 0;
        rear = 0;
        count = 0;
    }

    /**
     * to judge if the queue is empty
     * @return
     */
    public boolean isEmpty() {
        return front == rear;
    }

    /**
     * to judge if the queue is full
     * @return
     */
    public boolean isFull() {
        return front == (rear + 1) % size;
    }

    /**
     * insert x into the queue.
     * @param x
     */
    public boolean offer(int x) {
        if(! isFull()) {
            queue[rear] = x;
            rear = (rear + 1) % size;
            count ++;
            return true;
        }else {
            System.out.println("The queue is full");
            return false;
        }
    }

    /**
     * return the first element
     * @return
     */
    public int peek() throws Exception {
        if(! isEmpty()) {
            return queue[front];
        }
        throw new Exception("The queue is empty");
    }

    /**
     * return the first element and delete it.
     * @return
     */
    public int poll() throws Exception {
        if(! isEmpty()) {
            int res = queue[front];
            front = (front + 1) % size;
            count --;
            return res;
        }
        throw new Exception("The queue is empty");
    }

    public static void main(String[] args) {
        MyQueue queue = new MyQueue(10);
        queue.offer(1);
        queue.offer(3);
        try {
            System.out.println(queue.peek());
            System.out.println(queue.poll());
            System.out.println(queue.poll());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
