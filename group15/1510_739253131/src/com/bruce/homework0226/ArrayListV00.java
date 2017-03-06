package com.bruce.homework0226;

import com.bruce.utils.MyException;

import java.io.Serializable;
import java.util.Arrays;

/**
 * 用数组实现ArrayList基本功能：add,remove,size,contains,toArray方法
 * @Version: 0.0
 * Created by Bruce.Jiao on 17-2-23.
 */
public class ArrayListV00 implements Serializable {

    /**
     * 存放集合元素的数组
     */
    private transient Object[] elementData;
    /**
     * 集合中元素的个数
     */
    private int size;

    /**
     * 空构造，默认数组长度为10
     */
    public ArrayListV00() throws MyException {
        this(10);
    }

    /**
     * 有参构造
     * 
     * @param initCapacity
     *            用户传入的集合大小，底层数组的初始化大小
     */
    public ArrayListV00(int initCapacity) throws MyException{
        if(initCapacity < 0){
            throw new MyException("集合大小不能小于0");
        }
        elementData = new Object[initCapacity];
    }

    /**
     * 向集合中添加元素
     * 
     * @param value
     *            添加的元素，允许添加null
     * @return true:添加成功 ; false:添加失败
     */
    public boolean add(Object value) {
        // 添加元素之前，对数组长度进行判断，此处需要传入当前元素个数+1,
        ensureCapacity(size + 1);
        elementData[size++] = value;
        return true;
    }

    /**
     * 返回指定位置的元素 数组和集合，下标从1开始
     * 
     * @param index
     *            用户指定的位置
     * @return
     */
    public Object get(int index) throws MyException {
        // 判断是否越界，注意：此处判断依据是size，而不能是elementData.length，
        // 集合元素个数size小于等于elementData.length
        if (index >= size || index < 0) {
            throw new MyException("给定数值超出集合范围");
        }
        return elementData[index];
    }

    /**
     * 删除指定位置的元素
     * 
     * @param index
     *            用户指定位置，从0开始
     * @return 返回删除掉的指定位置的元素
     */
    public Object remove(int index) throws MyException {
        if (index >= size || index < 0) {
            throw new MyException("给定数值超出集合范围");
        }
        Object value = elementData[index];
        // 数组中被删除元素后边的所有元素的个数，此处不能使用elementData.length
        int length = size - 1 - index;
        // 被删除位置后还有元素，将数组中被删除位置往后(不包含被删除位置)的所有元素往前移动一位
        if (length > 0) {
            System.arraycopy(elementData, index + 1, elementData, index, length);
        }
        elementData[--size] = null;
        return value;
    }

    /**
     * 判断集合中是否包含指定的元素
     * 
     * @param value
     *            用户制定的元素
     * @return true:包含指定元素；false:不包含指定元素
     */
    public boolean contains(Object value) {
        for (int i = 0; i < elementData.length; i++) {
            if (value == null) {
                if (elementData[i] == null) {
                    return true;
                }
            } else {
                if (value.equals(elementData[i])) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 得到集合对应的静态数组
     * 
     * @return 底层数组
     */
    public Object[] toArray() {
        //elementData可能会包含null元素，不能直接返回，需返回一个包含集合所有元素的新数组
//        return elementData;
        return Arrays.copyOf(elementData,size);
    }

    /**
     * 返回集合大小
     * 
     * @return
     */
    public int size() {
        return size;
    }

    /**
     * 传入的数值与数组长度进行比较，长度小于传入数值，对数组进行扩容
     *
     * @param minCapacity
     *            传入的数值
     */
    public void ensureCapacity(int minCapacity) {
        int oldCapacity = elementData.length;
        // 如果传入数值大于现有数组长度，对现有数组进行扩容
        if (minCapacity > oldCapacity) {
            // 此处用新的局部变量引用指向原有数组的内存地址,仅为了避免复制数组元素到新数组时候,发生原有数组内存地址被覆盖的情况
            Object[] oldArray = elementData;
            // 先得到现有数组长度1.5倍的值
            int newCapacity = oldCapacity + oldCapacity >> 1;
            // 如果增加1.5倍后的数值仍然小于传入的数值，将传入的数值赋给新数组长度
            if (minCapacity > newCapacity) {
                newCapacity = minCapacity;
            }
            // 将elementData引用指向一个新的扩容后的数组，并且将原有数组的元素复制到新数组中
            elementData = Arrays.copyOf(elementData, newCapacity);
        }
    }

    /**
     * 重写toString方法，查看集合具体内容
     * @return
     */
    @Override
    public String toString() {
        return Arrays.toString(elementData);
    }

    /**
     * 仅仅作为自己查看底层数组长度的方法，
     * @return
     */
    int arrayLength() {
        return elementData.length;
    }
}
