package com.bruce.homework0226;

import com.bruce.utils.MyException;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

/**
 * 用数组实现ArrayList基本功能：add,remove,size,contains,toArray方法
 * @Version: 0.1
 * Created by Bruce.Jiao on 17-2-23.
 */
public class ArrayListV00<T> implements Serializable {

    /**
     * 存放集合元素的数组
     */
    private Object[] elementData;
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
    public ArrayListV00(int initCapacity) {
        if(initCapacity < 0){
            //throw new MyException("集合大小不能小于0");
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
    public boolean add(T value) {
        ensureCapacity(size + 1);
        elementData[size++] = value;
        return true;
    }

    public void add(int index, T value) {
        if(index < 0 || index > size) {
            //抛出异常
        }
        ensureCapacity(size+1);
        System.arraycopy(elementData, index, elementData, index+1 , size-index);
        elementData[index] = value;
        size++;
    }

    public T  set(int index, T value) {
        if(index < 0 || index > size) {
            return null;//抛出异常
        }
        elementData[index] = value;
        return value;
    }

    /**
     * 返回指定位置的元素 数组和集合，下标从1开始
     * 
     * @param index
     *            用户指定的位置
     * @return
     */
    @SuppressWarnings("unchecked")
    public T get(int index) {
        // 判断是否越界，注意：此处判断依据是size，而不能是elementData.length，
        // 集合元素个数size小于等于elementData.length
        if (index >= size || index < 0) {
            return null;//throw new MyException("给定数值超出集合范围");
        }
        return (T) elementData[index];
    }

    /**
     * 删除指定位置的元素
     * 
     * @param index
     *            用户指定位置，从0开始
     * @return 返回删除掉的指定位置的元素
     */
    public T remove(int index) {
        if (index >= size || index < 0) {
            return null;//throw new MyException("给定数值超出集合范围");
        }
        T value = (T) elementData[index];
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
     * 删除元素
     * @Version:0.1
     * @return true：删除成功；false：删除失败
     */
    public boolean remove(T value) {
        int index = indexOf(value);
        if (index < 0) {
            return false;
        }
        System.arraycopy(elementData, index+1, elementData, index, elementData.length-1-index);
        elementData[size--] = null;
        return true;
    }

    public int indexOf(T value) {
        for(int i = 0 ; i < elementData.length ; i++){
            if(Objects.equals(elementData[i], value)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 判断集合中是否包含指定的元素
     * @param value
     *            用户制定的元素
     * @return true:包含指定元素；false:不包含指定元素
     */
    public boolean contains(Object value) {
        //v0.0版本
//        for (int i = 0; i < elementData.length; i++) {
//            if (value == null) {
//                if (elementData[i] == null) {
//                    return true;
//                }
//            } else {
//                if (value.equals(elementData[i])) {
//                    return true;
//                }
//            }
//        }
        //v0.1版本，根据老师作业讲解进行修改
        for(Object o : elementData) {
            if (Objects.equals(o,value)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 得到集合对应的静态数组
     * @return 底层数组
     */
    @SuppressWarnings("unchecked")
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

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        for (int i = 0 ; i < size ; i++) {
            elementData[i] = null;
        }
        size = 0;
    }

    public IteratorV00<T> iterator() {
        return new Iterator();
    }

    //非静态内部类，和外部类实例绑定的,可以访问实例方法和属性
    private class Iterator implements IteratorV00<T> {
        private int position;

        Iterator(){}

        @Override
        public boolean hasNext() {
            return position < size;
        }

        @Override
        public T next() {
            if(hasNext()){
                return get(position++);
            }
            return null;
        }
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
//v0.0初級版本
//            int newCapacity = oldCapacity + oldCapacity >> 1;
//            if (minCapacity > newCapacity) {
//                newCapacity = minCapacity;
//            }
//            elementData = Arrays.copyOf(elementData, newCapacity);
            //v0.1升級版本
            int newCapacity = Math.max(minCapacity, oldCapacity + oldCapacity >> 1);
            Object[] newElementData = new Object[newCapacity];
            System.arraycopy(elementData,0,newElementData,0,oldCapacity);
            elementData = newElementData;
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
