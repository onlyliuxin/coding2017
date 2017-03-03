package rui.study.coding2017;


import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList implements List {

    private int size;

    private Object[] elementData;

    private static Object[] emptyObjects={};

    private static int defaultCapacity=10;

    public void add(Object o){
        ensureCapacity(this.size+1);
        elementData[size++]=o;
    }

    public void add(int index, Object o){
        rangeCheckForAdd(index);
        if(elementData[index]!=null){
            ensureCapacity(this.size+1);
            //执行数组拷贝
            System.arraycopy(elementData,index,elementData,index+1,size-index);
            size++;
        }
        elementData[index]=o;
    }

    public Object get(int index){
        rangeCheck(index);
        return elementData[index];
    }

    public Object remove(int index){
        rangeCheck(index);
        Object object=elementData[index];

        int numMoved=size-index-1;
        //如果是最后一位remove ，无需进行数组拷贝
        if(numMoved>0){
            System.arraycopy(elementData, index+1, elementData, index,numMoved);
        }
        elementData[--size]=null;
        return object;
    }

    public int size(){
        return this.size;
    }

    public Iterator iterator(){
        return new ArrayListIterator();
    }

    public ArrayList(){
        this.size=0;
        this.elementData=emptyObjects;
    }

    public ArrayList(int size){
        this.size=size;
        if(size>0){
            this.elementData=new Object[size];
        }else if(size==0){
            this.elementData=emptyObjects;
        }else{
            throw new IllegalArgumentException("非法容器大小 "+size);
        }

    }

    /**
     * 判断索引是否合法
     * @param index 索引
     */
    private void rangeCheckForAdd(int index) {
        if(index>size||index<0)throw new IndexOutOfBoundsException("索引为"+index+",但是当前数组长度为："+size);
    }

    /**
     * 判断索引是否合法 ,
     * @param index 索引
     */
    private void rangeCheck(int index) {
        if(index>=size||index<0)throw new IndexOutOfBoundsException("索引为"+index+",但是当前数组长度为："+size);
    }


    /**
     * 确保当前数组能够长度能够容纳新的对象，如果不够，就自行增长
     * @param needLength 需要的数组长度
     */
    private void ensureCapacity(int needLength) {
        if(elementData==emptyObjects){
            needLength = Math.max(defaultCapacity, needLength);
        }

        if(needLength-elementData.length>0){
            this.grow(needLength);
        }
    }

    /**
     * 数组扩容
     * @param needLength 需要的长度
     */
    private void grow(int needLength) {
        int elementLength=elementData.length;
        //扩容1.5倍
        int newLength=elementLength+(elementLength>>1);

        if(needLength-newLength>0){
            newLength=needLength;
        }
        this.elementData= Arrays.copyOf(this.elementData,newLength);
    }

    private class ArrayListIterator implements Iterator{
        //游标，当前迭代器执行到何处了
        private int cursor=0;

        @Override
        public boolean hasNext() {
            return cursor!=size;
        }

        @Override
        public Object next() {
            if (cursor >= size)throw new NoSuchElementException();
            Object[] elementData = ArrayList.this.elementData;
            return elementData[cursor++];
        }
    }




}
