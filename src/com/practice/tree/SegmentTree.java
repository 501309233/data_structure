package com.practice.tree;


/**
 * 线段树
 */
public class SegmentTree<E> {
    private E [] tree;
    private E [] data;

    /**
     * 通过一个数组构造线段树（静态，没有添加删除）
     */

    public SegmentTree(E [] arr){
        data =(E[]) new Object[arr.length];
        for (int i = 0 ; i < arr.length ; i++){
            data[i] = arr[i];
        }
        tree = (E[]) new Object[arr.length*4];
    }

    public E get(int index){
        if (index<0||index>data.length-1){
            throw new IllegalArgumentException("Index is Illegal.");
        }
        return data[index];
    }

    public int getSize(){
        return data.length;
    }

    private int leftChild(int index){
        return index*2+1;
    }
    private int rightChild(int index){
        return index*2+2;
    }


}
