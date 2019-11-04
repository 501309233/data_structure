package com.practice.stack;

public interface Stack<E> {
    //推入栈
    void push(E e);

    //推出栈
    E pop();

    //查看栈顶
    E peek();

    //查看栈内一共有多少元素
    int getSize();

    //查看栈是否为空
    boolean isEmpty();

}
