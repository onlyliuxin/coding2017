package com.circle.collection;

import java.util.Arrays;

/**
 * Created by keweiyang on 2017/2/25.
 * 自定义ArrayList
 */
public class ArrayList implements List {
    java.util.ArrayList list;
    private Object[] elementData = null;
    private int currentIndex = -1;

    public ArrayList(int length) {
        if (length < 0) {
            throw new RuntimeException("数组初始化大小必须大于0");
        }
        elementData = new Object[length];
    }

    /**
     * 在数组最后一位插入数据
     *
     * @param object
     */
    public void add(Object object) {
        //1：先判断数组是否越界，由于其他函数也会用到，所以提取为一个函数
        ensureCapacity();
        //2:执行插入操作
        currentIndex++;
        elementData[currentIndex] = object;
    }


    /**
     * 给数组动态扩容
     */
    public void ensureCapacity() {
        if (currentIndex + 2 > elementData.length) {
//            Object[] newObjects = new Object[elementData.length * 2 + 2];
            elementData = Arrays.copyOf(elementData, elementData.length * 2 + 2);
            //System.arraycopy(elementData, 0, newObjects, 0, newObjects.length);
        }
    }

    /**
     * 在数组指定位置(任意位置)插入数据
     *
     * @param index
     * @param object
     */
    public void add(int index, Object object) {

        rangeCheck(index);
        ensureCapacity();
        //如果index>currentIndex,并且在数组范围内，则插入
        if (index > currentIndex && index <= elementData.length) {
            currentIndex = index;
            elementData[currentIndex] = object;
            currentIndex++;

        } else if (index >= 0 && index < currentIndex) {
            //如果0<=index< currentIndex,则将index和之后的数据往后面移动

            for (int i = currentIndex; i >= index; i--) {
                elementData[i + 1] = elementData[i];
            }
            elementData[index] = object;
            currentIndex++;
        } else {
            //如果index=currentIndex,则调用add(Object o)
            add(object);
        }


    }

    public Object[] toArray() {
        Object[] array = new Object[currentIndex];
        System.arraycopy(elementData, 0, array, 0, currentIndex);
        return array;
    }

    private void rangeCheck(int index) {
        if (index < 0 || index > elementData.length - 1) {
            throw new ArrayIndexOutOfBoundsException("输入索引位置越界");
        }
    }

    /**
     * 获取指定位置数据
     *
     * @param index
     * @return
     */
    public Object get(int index) {
        rangeCheck(index);
        return elementData[index];
    }

    /**
     * 删除指定位置数据
     *
     * @param index
     * @return 返回要删除的数据
     */
    public Object remove(int index) {

        rangeCheck(index);
        Object temp = elementData[index];
        for (int i = index + 1; i <= currentIndex; i++) {
            elementData[i - 1] = elementData[i];
        }

        elementData[currentIndex] = null;
        currentIndex--;
        return temp;
    }

    /**
     * 防止内存泄露
     */
    public void clear() {
        for(int i=0;i<=currentIndex;i++) {
            elementData[i] = null;
        }
        currentIndex = -1;
    }

    /**
     * 返回数组长度
     *
     * @return
     */
    public int size() {
        return currentIndex + 1;
    }

    /**
     * 遍历ArrayList
     *
     * @return
     */
    public Iterator iterator() {

        return new Iterator() {

            int pos = -1;

            public boolean hasNext() {
                if (pos + 1 <= currentIndex) {
                    return true;
                }
                return false;
            }

            public Object next() {
                if (hasNext()) {
                    pos++;
                    return elementData[pos];
                }
                return null;
            }
        };
    }


}
