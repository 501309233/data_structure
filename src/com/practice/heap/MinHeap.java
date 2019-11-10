package com.practice.heap;

import com.practice.array.Array;

public class MinHeap <E extends Comparable<E>> {
    private Array<E> array;
    public MinHeap(int capacity){
        array = new Array<>(capacity);
    }

    public MinHeap(){
        array = new Array<>();
    }
    //返回堆中的个数
    public int size(){
        return array.getSize();
    }
    //返回堆是否为空
    public boolean isEmpty(){
        return array.isEmpty();
    }
    //返回完全二叉树的数组表示中，一个索引所表示的元素的父亲节点的索引
    private int parent(int index){
        if (index == 0 ){
            throw new IllegalArgumentException("index 0 doesn't have parent.");
        }
        return (index-1)/2;
    }
    //返回完全二叉树的数组表示中，一个索引所表示的元素的左孩子节点的索引
    private int leftChild(int index){
        return index*2+1;
    }
    //返回完全二叉树的数组表示中，一个索引所表示的元素的右孩子节点的索引
    private int rightChild(int index){
        return index*2+2;
    }

    //向最小堆中添加一个元素
    public void add(E e){
        array.addLast(e);
        siftUp(array.getSize()-1);
    }
    //找到堆中的最小元素
    public E findMin(){
        if (array.getSize()==0){
            throw new IllegalArgumentException("Can not findMin when heap is empty.");
        }
        return array.get(0);
    }
    //取出堆中的最小元素
    public E extractMin(){
        E ret = findMin();
        array.swap(0,array.getSize()-1);
        array.removeLast();
        siftDown(0);
        return ret;
    }
    //上浮
    private void siftUp(int k){
        while (k>0&&array.get(parent(k)).compareTo(array.get(k))>0){
            array.swap(k,parent(k));
            k=parent(k);
        }
    }
    //下沉
    private void siftDown(int k){
        while(leftChild(k)<array.getSize()){
            int j = leftChild(k);
            if (j+1<array.getSize()&&array.get(j+1).compareTo(array.get(j))<0){
                j = rightChild(k);
            }
            if (array.get(k).compareTo(array.get(j))<=0){
                break;
            }
            array.swap(k,j);
            k=j;
        }
    }

}
