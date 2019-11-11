package com.practice.tree;

/**
 * 线段树
 */
public class SegmentTree<E> {
    private E[] tree;
    private E[] data;
    private Merger<E> merger;

    /**
     * 通过一个数组构造线段树（静态，没有添加删除）
     */

    public SegmentTree(E[] arr, Merger<E> merger) {
        //自定义接口，构造线段树的时候可以用匿名内部类实现
        this.merger = merger;
        //用data接受传入的数据
        data = (E[]) new Object[arr.length];
        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
        }
        //创建线段树，容量为数据量的四倍
        tree = (E[]) new Object[arr.length * 4];
        //通过此方法构建线段树
        buildSegmentTree(0, 0, data.length - 1);
    }

    /**
     * 在treeIndex的位置创建表示区间[l...r]的线段树
     *
     * @param treeIndex
     * @param l
     * @param r
     */
    private void buildSegmentTree(int treeIndex, int l, int r) {
        if (l == r) {
            tree[treeIndex] = data[l];
            return;
        }
        //左孩子节点
        int leftTreeIndex = leftChild(treeIndex);
        //有孩子节点
        int rightTreeIndex = rightChild(treeIndex);
        //中间位置
        int mid = l + (r - l) / 2;
        //递归构建左子树的线段二叉树
        buildSegmentTree(leftTreeIndex, l, mid);
        //递归构建右子树的线段二叉树
        buildSegmentTree(rightTreeIndex, mid + 1, r);
        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }


    public E get(int index) {
        if (index < 0 || index > data.length - 1) {
            throw new IllegalArgumentException("Index is Illegal.");
        }
        return data[index];
    }

    public int getSize() {
        return data.length;
    }

    /**
     * 返回左孩子的索引值
     *
     * @param index
     * @return
     */
    private int leftChild(int index) {
        return index * 2 + 1;
    }

    /**
     * 返回右孩子的索引值
     *
     * @param index
     * @return
     */
    private int rightChild(int index) {
        return index * 2 + 2;
    }


    /**
     * 返回区间[queryL,queryR]的值
     *
     * @param queryL
     * @param queryR
     * @return
     */
    public E query(int queryL, int queryR) {
        if (queryL < 0 || queryL >= data.length || queryR < 0 || queryR >= data.length || queryL > queryR) {
            throw new IllegalArgumentException("Index is illegal.");
        }
        //从根节点开始，以根节点左边界和右边界构建线段树，查找[queryL...queryR]的区间
        return query(0, 0, data.length - 1, queryL, queryR);
    }

    /**
     * 在以treeID为根的线段树中{l..r}的范围里，搜索区间[queryL...queryR]的值
     *
     * @param treeIndex 线段树的索引（写死就无法复用方法递归了）
     * @param l         线段树区间左边界索引
     * @param r         线段树区间右边界索引
     * @param queryL    搜索区间左边界索引
     * @param queryR    搜索区间右边界索引
     * @return
     */
    private E query(int treeIndex, int l, int r, int queryL, int queryR) {
        //如果线段树左右边界与搜索区间相同，直接返回这个线段树索引
        if (l == queryL && r == queryR) {
            return tree[treeIndex];
        }
        //线段树分割左右子树的中间值
        int mid = l + (r - l) / 2;
        //左孩子节点
        int leftTreeIndex = leftChild(treeIndex);
        //右孩子节点
        int rightTreeIndex = rightChild(treeIndex);
        //如果搜索区间左边界值大于中间值，返回右子树查询的值
        if (queryL >= mid + 1) {
            return query(rightTreeIndex, mid + 1, r, queryL, queryR);
        }
        //如果搜索区间有边界值大于中间值，返回左子树查询的值（经过merger.merge(a,b)）
        else if (queryR <= mid) {
            return query(leftTreeIndex, l, mid, queryL, queryR);
        }
        //否则，返回左、右子树查询到的值的结果
        E leftResult = query(leftTreeIndex, l, mid, queryL, mid);
        E rightResult = query(rightTreeIndex, mid + 1, r, mid + 1, queryR);
        //将结果经过merger处理，自己在构建树的时候定义规则
        return merger.merge(leftResult, rightResult);
    }
}
