package com.practice;

import com.practice.tree.SegmentTree;

/**
 * @author MoFeng
 */
public class Main {

    public static void main(String[] args) {
        //定义一个数组
        Integer [] nums = {-2,0,3,-5,2,-1};
        //根据数组实例化一个线段树，并且定义线段树规则
        SegmentTree<Integer> segTree = new SegmentTree<>(nums,((a, b) -> a+b));
        Integer result = segTree.query(0,3);
        System.out.println(result);
    }
}
