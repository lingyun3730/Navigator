package com.sata.others.learning;

import java.util.EmptyStackException;

public class LinkedStack<E> implements IStack<E>{

    private static class Node<E>{
        E data;
        Node<E> next;
        Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }
    }

    private Node<E> top;
    private int size;

    public LinkedStack(){
        this.top = null;
        this.size = 0;
    }

    @Override
    public int size(){
        return size;
    }

    @Override
    public boolean isEmpty(){
        return (size==0);
    }

    @Override
    public void push(E item) {
        top = new Node<>(item, top);
        size++;
    }

    @Override
    public E pop() {
        if(size == 0){
            throw new EmptyStackException();
        }
        E val = top.data;
        top = top.next;
        size--;
        return val;
    }

    @Override
    public E peek() {
        if(size==0){
            throw new EmptyStackException();
        }
        return top.data;
    }

    public static void main(String[] args) {
        IStack<Integer> st = new LinkedStack<>();
        for(int i=1; i<=10; i++) {
            st.push(i);
        }
        while(!st.isEmpty()) {
            System.out.println("pop val : " + st.pop());
        }

    }
}
