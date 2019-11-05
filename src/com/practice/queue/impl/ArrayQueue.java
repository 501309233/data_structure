package com.practice.queue.impl;

import com.practice.array.Array;
import com.practice.queue.Queue;

public class ArrayQueue<E> implements Queue<E> {

    private Array<E> array;

    public ArrayQueue(int capacity){
        array = new Array<>(capacity);
    }
    public ArrayQueue(){
        array = new Array<>();
    }

    /**
     * 入队
     * @param e
     */
    @Override
    public void enqueue(E e) {
        array.addLast(e);
    }

    /**
     * 出队
     * @return
     */
    @Override
    public E dequeue() {
        return array.removeFirst();
    }

    /**
     * 得到队首
     * @return
     */
    @Override
    public E getFront() {
        return array.getFirst();
    }

    /**
     * 得到队伍的长度
     * @return
     */
    @Override
    public int getSize() {
        return array.getSize();
    }

    /**
     * 判断队伍是否为空
     * @return
     */
    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Queue: front[ ");
        for (int i=0;i<array.getSize();i++){
            stringBuilder.append(array.get(i));
            if (i!=array.getSize()-1){
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append("] tail");
        return stringBuilder.toString();
    }


}
