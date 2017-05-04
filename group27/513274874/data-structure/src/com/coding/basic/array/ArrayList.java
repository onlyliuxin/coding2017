
package com.coding.basic.array;

import com.coding.basic.Iterator;

import java.util.Arrays;

/**
 * @autor zhougd 20170306
 * 数组实现ArrayList
 */
public class ArrayList implements List {

    private int size = 0;

    private Object[] elementData;

    //扩容默认值
    private static final int INCREAMENT_CAP = 10;

    //含参数的构造函数
    public ArrayList(int size, Object[] elementData) {
        this.size = size;
        this.elementData = elementData;
    }

    //默认100容量的构造函数
    public ArrayList() {
        this.size = 0;
        this.elementData = new Object[100];
    }

    @Override
    public void add(Object o) {
        //判断超过容量自动扩容
        if (this.size + 1 > this.elementData.length) {
            increase();
        }
        this.elementData[size++] = o;
    }

    @Override
    public void add(int index, Object o) {
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException("Index out of bound!");
        }
        //判断超过容量自动扩容
        if (this.size + 1 > this.elementData.length) {
            increase();
        }
        this.size++;
        //index后面数组后移一位
        for (int cur = this.size; cur > index; cur--) {
            this.elementData[cur] = this.elementData[cur - 1];
        }

        this.elementData[index] = o;

    }

    public Object get(int index) {
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException("Index out of bound!");
        }
        return this.elementData[index];
    }

    public Object remove(int index) {
        Object o = this.get(index);

        //index后面的数向前移动一位
        for (int cur = index + 1; cur < this.size; cur++) {
            this.elementData[cur] = this.elementData[cur + 1];
        }
        //最后一个元素删除
        this.elementData[this.size-1] = null;

        this.size--;
        return o;
    }

    public int size() {
        return this.size;
    }

    public Iterator iterator() {
        return new ArrayListIterator();
    }

    @Override
    public String toString() {
        String arrayStr = "ArrayList{ size = " + this.size() + " , ";

        arrayStr += "elementData=[";
        for(int i = 0 ;i<this.size();i++){
            arrayStr +=
                    i == this.size()-1 ?
                            elementData[i]+"]":elementData[i]+","  ;
        }
        arrayStr+= " }";
        return arrayStr;

    }

    private void increase() {
        this.elementData = Arrays.copyOf(this.elementData, this.elementData.length + INCREAMENT_CAP);
    }

    private class ArrayListIterator implements Iterator {

        private int currentIndex = 0;
        private int count = size();

        @Override
        public boolean hasNext() {
            return currentIndex < count-1;
        }

        @Override
        public Object next() {
            currentIndex++;
            return get(currentIndex);
        }
    }
}

