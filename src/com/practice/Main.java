package com.practice;


import com.practice.tree.BST;

public class Main {

    public static void main(String[] args) {
        BST<Integer> bst = new BST<>();
        int[] nums = {5, 3, 6, 8, 4, 2};
        for (int num: nums){
            //测试add方法
            bst.add(num);
        }
        //测试前序遍历
        bst.preOrder();
        System.out.println();
        //测试中序遍历
        bst.inOrder();
        System.out.println();
        //测试后序遍历
        bst.postOrder();
    }
}
