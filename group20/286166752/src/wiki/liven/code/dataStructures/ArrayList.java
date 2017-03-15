package wiki.liven.code.dataStructures;

import java.util.Arrays;

/**
 * Created by leven on 2017/2/21.
 */
public class ArrayList implements List{

    /**
     * 列表中元素的个数
     */
    private int size = 0;
    private int maxSize = 10;
    /**
     * 初始数组
     */
    private Object[] elementData = new Object[maxSize];

    /**
     * 在指定的位置i插入元素O
     * 插入元素,判断当前列表中元素的个数,
     * 当size==100,则需要扩张数组
     * 当size<100,则使用初始数组完成插入操作
     * 插入操作:
     *  从最后一个元素开始,往后移动一位,直到到index为止.
     * @param index
     * @param o
     */
    @Override
    public void add(int index, Object o) {
        if(index<0||index>size-1)
            throw new IndexOutOfBoundsException("数组下标越界异常。");
        if (size==maxSize){
            Object[] targt = new Object[++maxSize];
            System.arraycopy(elementData,0,targt,0,elementData.length);
            for (int j = targt.length-2;j>=index;j--){
                targt[j+1] = targt[j];
            }
            targt[index] = o;
            size++;
            elementData = targt;
        }else if(size<maxSize){
            for (int j = size-1;j>=index;j--){
                elementData[j+1] = elementData[j];
            }
            elementData[index] = o;
            size++;
        }
    }

    /**
     * 追加元素
     * @param o
     */
    @Override
    public void add(Object o) {
        if (size==maxSize){
            Object[] targt = new Object[++maxSize];
            System.arraycopy(elementData,0,targt,0,elementData.length);
            targt[maxSize-1] = o;
            size++;
            elementData = targt;
        }else if(size<maxSize){
            elementData[size] = o;
            size++;
        }
    }

    @Override
    public Object get(int index) {
        if(index<0||index>size-1)
            throw new IndexOutOfBoundsException("数组下标越界异常");
        Object o= elementData[index];
        return o;
    }

    @Override
    public Object remove(int index) {
        if (index<0||index>size-1)
            throw new IndexOutOfBoundsException("数组下表越界异常");
        Object temp = elementData[index];
        for (int i = index;i<=size-1;i++){
            elementData[i] = elementData[i+1];
        }
        size--;
        return temp;
    }

    @Override
    public int size() {
        return size;
    }


    @Override
    public String toString() {
        return "ArrayList{" +
                "elementData=" + Arrays.toString(elementData) +
                '}';
    }
}
