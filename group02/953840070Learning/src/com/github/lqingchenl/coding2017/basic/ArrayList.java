package com.github.lqingchenl.coding2017.basic;

import com.github.lqingchenl.coding2017.basic.List;
import org.junit.Test;

import java.util.Arrays;


public class ArrayList implements List {
    private int size = 0;

    private Object[] elementData = new Object[3];

    /**
     * 添加一个元素
     *
     * @param o
     */
    public void add(Object o) {
        elementData[size] = o;
        size = size + 1;
        if (size >= elementData.length) {
            elementData = Arrays.copyOf(elementData, elementData.length * 2);
        }
    }

    /**
     * 往固定位置添加一个元素
     *
     * @param index
     * @param o
     */
    public void add(int index, Object o) {
        size = size + 1;
        if (size >= elementData.length) {
            elementData = Arrays.copyOf(elementData, elementData.length * 2);
        }
        // 将指定索引出后面的元素集体向后移动一格
//        for (int i = size; i >= index; i--) {
//            elementData[i + 1] = elementData[i];
//        }

        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = o;
    }

    /**
     * 获取元素
     * @param index
     * @return
     */
    public Object get(int index) {

        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("索引越界");
        }
        return elementData[index];
    }

    /**
     * 移除元素
     * @param index
     * @return
     */
    public Object remove(int index) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("索引越界");
        }
        Object deleteData = elementData[index];
        if (index == size - 1){
            elementData[index] = null;
        }else{
            int movedCount = size - index - 1;
            System.arraycopy(elementData, index + 1, elementData, index, movedCount);
        }
        return deleteData;
    }

    public int size() {
        return size;
    }

    public Iterator iterator() {
        return null;
    }

    /**
     * 测试添加、移除、当前大小
     */
//    @Test
    public void testArrayList() {
        for (int j = 0; j < 3; j++) {
            add(j);
        }
        add(2, 66);

        for (Object z : elementData) {
            System.out.println(z);
        }

        System.out.println(size());
        System.out.println(remove(3));
    }
}
