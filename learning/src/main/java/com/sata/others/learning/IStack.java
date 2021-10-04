package com.sata.others.learning;

public interface IStack<E> {
    /**
     * size of the stack
     * @return
     */
    int size();

    /**
     * if is empty
     * @return
     */
    boolean isEmpty();

    void push(E item);

    E pop();

    E peek();
}
