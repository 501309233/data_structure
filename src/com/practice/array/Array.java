package com.practice.array;

public class Array<E> {
    private E[]  data;
    private int size;

    /**
     * 有参构造，
     */
    public Array(int capacity){
        data=(E[])new Object[capacity];
        size=0;
    }

    /**
     * 无参构造，默认容量为10
     */
    public Array(){
        this(10);
    }

    /**
     * 返回元素个数
     * @return
     */
    public int getSize(){
        return size;
    }

    /**
     * 返回当前数组的容量
     * @return
     */
    public int getCapacity(){
        return data.length;
    }

    /**
     * 返回当前数组是否为空
     * @return
     */
    public boolean isEmpty(){
        return size==0;
    }

    /**
     * 在数组的index位置插入e
     * @param index
     * @param e
     */
    public void add(int index,E e){
        //验证参数和环境条件
        if (index<0||index>data.length){
            throw new IllegalArgumentException("Index is illegal,you can't insert the new item");
        }
        if (data.length==size){
            resize(data.length*2);
        }

        //进行数据插入算法
        if (size>0) {
            for (int i = size - 1; i >= index; i--) {
                data[i + 1] = data[i];
            }
        }
        data[index]=e;
        size++;
    }

    private void resize(int newCapacity) {
        Array <E> newArr = new Array<>(newCapacity);
        for (int i=0;i<size;i++){
            newArr.data[i]=data[i];
        }
        data = newArr.data;
    }

    /**
     * 在所有元素后添加一个新元素
     * @param e
     */
    public void addLast(E e){
        add(size,e);
    }

    /**
     * 在所有元素前添加一个新元素
     * @param e
     */
    public void addFirst(E e){
        add(0,e);
    }

    /**
     * 根据index返回数组数据
     * @param index
     * @return
     */
    public E get(int index){
        if (index<0||index>=size){
            throw new IllegalArgumentException("Get failed. Index is illegal.");
        }
        return data[index];
    }

    /**
     * 得到数组中第一个数据
     * @return
     */
    public E getFirst(){
        return get(0);
    }

    /**
     * 得到数组中最后一个数据
     * @return
     */
    public E getLast(){
        return get(size-1);
    }

    /**
     * 根据index,e来设置数组的值
     * @param index
     * @param e
     */
    void set(int index,E e){
        if (index<0||index>data.length-1){
            throw new IllegalArgumentException("Set failed. Index is illegal.");
        }
        data[index]=e;
    }

    /**
     * 查找数组中是否有元素e
     * @param e
     * @return
     */
    public boolean contains(E e){
        for (int i = 0;i<size;i++){
            if (data[i].equals(e)){
                return true;
            }
        }
        return false;
    }

    /**
     * 查找数组中元素e所在的索引，如果不存在索引e则返回-1
     * @param e
     * @return
     */
    public int find(E e){
        for (int i=0;i<size;i++){
            if (data[i].equals(e)){
                return i;
            }
        }
        return -1;
    }

    /**
     * 从数组中删除index位置的元素，返回删除元素
     * @param index
     * @return
     */
    public E
    remove(int index){
        if (index<0||index>=size){
            throw new IllegalArgumentException("remove failed. Index is illegal.");
        }
        E ret = data[index];
        for (int i=index;i<size-1;i++){
            data[i]=data[i+1];
        }
        size--;
        //让最后这个无用的数据没有引用，java自动回收资源
        data[size]=null;
        if (size<data.length/4&&data.length/2!=0){
            resize(data.length/2);
        }
        return ret;
    }

    /**
     * 删除第一个元素并返回该元素值
     * @return
     */
    public E removeFirst(){
        return remove(0);
    }

    /**
     * 删除最后一个元素并返回该元素的值
     * @return
     */
    public E removeLast(){
        return remove(size-1);
    }

    /**
     * 删除数组中的元素e
     * @param e
     */
    public boolean removeElement(E e){
        for (int i=0;i<size;i++){
            if (data[i].equals(e)){
                remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * 删除所有元素e
     * @param e
     */
    public void removeAllElement(E e){
        for (int i=0;i<size;i++){
            if (data[i].equals(e)){
                remove(i);
            }
        }
    }

    /**
     * 打印字符串信息
     * @return
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("Array: Size = %d, Capacity = %d\n",getSize(),getCapacity()));
        stringBuilder.append("[");
        for (int i=0;i<size;i++){
            stringBuilder.append(data[i]);
            if (i!=size-1){
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    /**
     * 交换两个索引的元素的位置
     */
    public void swap(int i , int j){
        if (i<0 || i>=size || j<0||j>=size){
            throw new IllegalArgumentException("Index is illegal.");
        }
        E t = data[i];
        data[i]=data[j];
        data[j]=t;
    }


}
