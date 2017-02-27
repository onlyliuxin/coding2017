package com.bruce.homework0226;

import com.bruce.utils.MyException;

import java.io.Serializable;
import java.util.Arrays;

/**
 * 用数组实现一个栈的基本功能：push,pop,isEmpty,size,clear方法
 * @Version: 0.0
 * Created by Bruce.Jiao on 17-2-24.
 */
public class StackV00 implements Serializable{

    /**
     * 底层存放栈元素的数组
     */
    private Object[] elementData;

    /**
     * 栈中元素的个数
     */
    private int size;

    /**
     * 每次扩容增加的大小
     */
    private int capacityIncrement;

    /**
     * 空构造，数组初始长度为10
     */
    public StackV00() throws MyException{
        this(10);
    }

    /**
     * 有参构造
     * @param initCapacity 用户指定的栈空间初始大小（底层数组的初始大小）
     * @throws MyException 对传入参数进行判断，不符合要求抛出异常
     */
    public StackV00(int initCapacity) throws MyException{
        this(initCapacity,0);
    }

    /**
     * 有参构造
     * @param initCapacity 用户指定的栈空间初始大小（底层数组的初始大小）
     * @param capacityIncrement 用户指定的每次扩容大小（当空间不足时，每一次扩容增加的大小）
     * @throws MyException 对传入参数进行判断，不符合要求抛出异常
     */
    public StackV00(int initCapacity, int capacityIncrement) throws MyException{
        if(initCapacity < 0 || capacityIncrement <0){
            throw new MyException(initCapacity < 0?"栈空间大小不能为负数":"扩容参数不能为负数");
        }
        elementData = new Object[initCapacity];
    }

    /**
     * 向栈中添加元素
     * @param value 添加的元素，可以为null
     * @return 添加成功后的元素
     */
    public Object push(Object value){
        ensureCapacity(size+1);
        //将新增的元素放在size索引处，并且将size加1
        elementData[size++] = value;
        return value;
    }

    /**
     * 从栈中获取元素，拿到当前所有元素中最后添加进来的元素
     * @return 最后的元素
     */
    public Object pop(){
        //拿到最后的元素，在栈中将该元素删除，将size减1
        return elementData[--size];
    }

    /**
     * 判断栈是否为空
     * @return true:空栈，无元素；false:有元素
     */
    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * 获取栈大小(元素数量，包括null元素)
     * @return 栈中元素大小
     */
    public int size(){
        return size;
    }

    /**
     * 清空栈中元素
     */
    public void clear(){
        int oldCapacity = elementData.length;
        size = 0;
        elementData = new Object[oldCapacity];
    }

    /**
     * 判断数组尺寸
     * @param minCapacity
     */
    public void ensureCapacity(int minCapacity){
        int oldCapacity = elementData.length;
        //如果传入值大于当前数组尺寸，对数组进行扩容
        if(minCapacity > oldCapacity){
            //如果capacityIncrement大于0，每次扩容用户指定的大小，否则每次将当前数组尺寸扩大一倍
            int newCapacity = oldCapacity + capacityIncrement > 0 ? capacityIncrement : oldCapacity;
            //元素数组扩容，并且将原有的元素复制到新数组中
            elementData = Arrays.copyOf(elementData,newCapacity);
        }
    }

    /**
     * 拿到底层的静态数组
     * @return 底层元素数组
     */
    public Object[] toArray(){
        return Arrays.copyOf(elementData,size);
    }

    /**
     * toString方法，可以直接打印出栈底层的数组
     * @return
     */
    @Override
    public String toString() {
        return Arrays.toString(toArray());
    }

    /**
     * 仅仅作为自己查看底层数组长度的方法，
     * @return 数组长度，大于等于元素个数
     */
    int arrayLength() {
        return elementData.length;
    }
}
