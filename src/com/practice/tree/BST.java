package com.practice.tree;


import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BST<E extends Comparable<E>> {


    private class Node{
        public E e;
        public Node left,right;

        public Node(E e){
            this.e = e;
            left = null;
            right = null;
        }
    }
    private Node root;
    //记录二分搜索树中存储的元素个数
    private int size;

    public BST(){
        root = null;
        size = 0;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size==0;
    }

    public void add(E e) {
        root = add(root,e);
    }
    /**
     * 向以node为根的二分搜索树中插入元素e，递归算法
     * 返回插入新节点后二分搜索树的根
     */

    private Node add(Node node,E e){
        //如果结点为空，创建新结点并返回
        if (node == null){
            size++;
            return new Node(e);
        }
        //如果结点元素小于当前结点元素
        // 去左子树执行递归操作并返回给当前结点的左子树
        if (e.compareTo(node.e) < 0){
            node.left = add(node.left, e);
            //如果结点元素大于当前结点元素
            // 去右子树执行递归操作并返回给当前结点的右子树
        }else if (e.compareTo(node.e)>0){
            node.right = add(node.right, e);
        }
        //最终返回当前结点
        //一般的以root为开始递归
        return node;
    }

   /* private void add(Node node, E e){
        if (e.equals(node.e))
            return;
        else if (e.compareTo(node.e)<0&&node.left==null){
            node.left = new Node(e);
            size++;
            return;
        }
        else if (e.compareTo(node.e)>0&&node.right == null){
            node.right = new Node(e);
            size++;
            return;
        }
        if (e.compareTo(node.e)<0)
            add(node.left,e);
        else
            add(node.right, e);
    }*/

    /**
     * 实现contains方法，判断二分搜索树中是否包含元素e
     */
    public boolean contains(E e){
        return contains(root,e);
    }
    /**
     * 判断以node为根的二分搜索树中是否包含元素e
     */
    private boolean contains(Node node,E e){
        //如果传入结点为空，返回false
        if (node==null){
            return false;
        }
        //如果传入结点元素与当前结点元素相等
        //返回true
        if (e.compareTo(node.e)==0){
            return true;
            //如果传入结点元素小于当前结点元素
            //去左子树执行递归操作
        }else if (e.compareTo(node.e) < 0){
            return contains(node.left,e);
            //如果传入结点元素大于当前结点元素
            //去右子树执行递归操作
        }else {
            return contains(node.right,e);
        }
    }

    /**
     * 二分搜索树的遍历操作
     * 前序遍历的业务逻辑如下
     */
    public void preOrder(){
        preOrder(root);
    }
    private void preOrder(Node node){
        //若传入结点为空，结束
        if (node==null){
            return ;
        }
        //打印当前结点数据
        System.out.print(node.e);
        //递归执行左子树
        preOrder(node.left);
        //递归执行右子树
        preOrder(node.right);
    }
    /**
    用栈实现二分搜索树的非递归前序遍历
     */
    public void preOrderNR(){
        //定义栈
        Stack<Node> stack = new Stack<>();
        //在栈中加入根节点
        stack.push(root);
        //当栈不为空时循环执行
        while(!stack.isEmpty()){
            //返回栈顶元素
            Node cur = stack.pop();
            //打印栈顶元素
            System.out.print(cur.e);
            //如果栈顶元素右子树不为空
            //将栈顶元素右子树先放入栈中
            if (cur.right!=null){
                stack.push(cur.right);
            }
            //如果栈顶元素左子树不为空
            //将栈顶元素左子树先放入栈中
            if (cur.left!=null){
                stack.push(cur.left);
            }
            //先放入并处理当前结点然后放右子树后放左子树
            //这样栈输出的顺序是当前结点左子树然后是右子树
        }
    }

    /**
     * 中序遍历的业务逻辑如下
     */
    public void inOrder(){
        inOrder(root);
    }
    private void inOrder(Node node){
        if (node==null){
            return;
        }
        //中序遍历先遍历左子树然后遍历当前结点最后遍历右子树
        inOrder(node.left);
        System.out.print(node.e);
        inOrder(node.right);
    }

    /**
     * 后序遍历
     */
    public void postOrder(){
        postOrder(root);
    }
    private void postOrder(Node node){
        if (node==null){
            return;
        }
        //后序遍历先遍历左子树然后遍历右子树最后遍历当前结点
        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.e);
    }

    /**
     * 二分搜索树的层序遍历
     * 相当于广度优先遍历
     */
    public void levelOrder(){
        //定义一个队列数据结构
        Queue<Node> q = new LinkedList<>();
        //将根放入队列
        q.add(root);
        //如果队列不为空，执行循环
        while(!q.isEmpty()){
            //出队列并返回
            Node cur = q.remove();
            //打印出队列元素
            System.out.print(cur.e);
            //如果出队列元素的左子树不为空则将左子树加入队列
            if (cur.left!=null){
                q.add(cur.left);
            }
            //如果出队列元素的右子树不为空则将右子树加入队列
            if (cur.right!=null){
                q.add(cur.right);
            }
        }
    }

    /**
     * 获取二分搜索树中的最小元素和最大元素
     *
     */
    //寻找二分搜索树中的最小元素
    public E minimum(){
        if (size==0){
            throw new IllegalArgumentException("BST is empty!");
        }
        return minimum(root).e;
    }
    //返回以node 为根的二分搜索树的最小元素所在节点
    private Node minimum(Node node){
        //如果当前结点的左子树为空，返回当前结点
        if (node.left == null){
            return node;
        }
        //否则递归执行当前结点的左子树
        return minimum(node.left);
    }
    //寻找二分搜索树中的最大元素
    public E maximum(){
        if (size==0){
            throw new IllegalArgumentException("BST is empty!");
        }
        return maximum(root).e;
    }
    //返回以node为根的二分搜索树的最大元素所在节点
    private Node maximum(Node node){
        //如果当前结点的右子树为空，返回当前结点
        if (node.right==null){
            return node;
        }
        //否则递归执行当前结点的右子树
        return maximum(node.right);
    }

    /**
     * 删除二分搜索树中最小元素和最大元素所在节点
     *
     */
    //从二分搜索树中删除最小元素所在节点，返回最小元素
    public E removeMin(){
        E ret = minimum();
        root = removeMin(root);
        return ret;
    }

    //删除掉以Node为根的二分搜索树中的最小元素所在的节点
    //返回删除节点后新的二分搜索树的根
    private Node removeMin(Node node){
        //如果该节点的左子树为空
        if (node.left == null){
            //保存该结点的右子树
            Node rightNode = node.right;
            //删掉当前结点的右子树
            node.right = null;
            size--;
            //返回保存的右子树
            return rightNode;
        }
        //递归执行该节点的左子树
        node.left = removeMin(node.left);
        //返回当前结点
        return node;
    }

    //从二分搜索树中删除最大元素所在节点，返回最大元素
    public E removeMax(){
        E ret = maximum();
        root = removeMax(root);
        return ret;
    }
    //删除掉以node为根的二分搜索树中的最小元素所在节点
    //返回删除节点后新的二分搜索树的根
    private Node removeMax(Node node){
        if (node.right == null){
            Node leftNode = node.left;
            node.left = null;
            size--;
            return leftNode;
        }
        node.right = removeMax(node.right);
        return node;
    }

    /**
     * 删除二分搜索树中指定元素所对应的节点
     *
     */
    //从二分搜索树中删除元素为e的节点
    public void remove(E e){
        remove(root,e);
    }
    //删除以node为根节点的二分搜索树中元素为e的节点，递归算法
    //返回删除节点后新的二分搜索树的根
    private Node remove(Node node,E e){
        if (node == null){
            return null;
        }

        if (e.compareTo(node.e)<0){
            node.left = remove(node.left,e);
            return node;
        }else if (e.compareTo(node.e)>0){
            node.right = remove(node.right,e);
            return node;
        }else {
            //待删除节点左子树为空的情况
            if (node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
                //待删除节点右子树为空的情况
            } else if (node.right == null){
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
                //待删除节点左右子树均不为空
                //找到比待删除节点大的最小节点，即待删除节点右子树的最小节点
                //用这个节点顶替待删除节点
            }else {
                Node successor = minimum(node.right);
                successor.right = removeMin(node.right);//这里进行了size--操作
                successor.left = node.left;
                node.left = null;
                node.right = null;
                return  successor;
            }
        }
    }

}
