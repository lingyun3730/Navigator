package com.sata.queue;

/**
 * 实现一个队列 cyclic
 */
class MyQueue {

    //数组实现环形队列
    private int[] que;
    private int rear;
    private int front;
    private int k;
    int count = 0;
    public MyQueue(int k) {
        this.k = k;
        que = new int[k];
        rear = -1; //rear初始化
        front = 0;
    }

    public boolean enQueue(int value) {
        if(isFull()) {
            return false;
        }
        rear = (rear + 1) % k;
        que[rear] = value;
        count ++;
        return true;
    }

    public boolean deQueue() {
        if(isEmpty()) return false;
        front = (front + 1) % k;
        count --;
        return true;
    }

    public int Front() {
        if(isEmpty()) return -1;
        return que[front];
    }

    public int Rear() {
        if(isEmpty()) return -1;
        return que[rear];
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public boolean isFull() {
        return count == k;
    }

    public static void main(String[] args) {
        MyQueue queue = new MyQueue(10);
        queue.enQueue(1);
        queue.enQueue(3);
        try {
            System.out.println(queue.deQueue());
            System.out.println(queue.deQueue());
            System.out.println(queue.deQueue());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
