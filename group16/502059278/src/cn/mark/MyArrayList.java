package cn.mark;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 自定义实现ArrayList的数据结构
 * @author hilih
 *
 */
public class MyArrayList implements MyList{
    
    private int size = 0;
    
    private Object[] elementData;
    
    public MyArrayList(){
        //默认容量初始化为10
        this(10);
    }
    
    /**
     * 初始即指定大小的构造方法
     * @param size 集合容量
     */
    public MyArrayList(int size){
        if ( size < 0 ){
            System.out.println("不合法的容量输入");
            return;
        }
        elementData = new Object[size];
    }
    
    /**
     * 集合增容
     * @param minSize
     */
    private void ensureSize(int minSize){
        int oldSize = elementData.length;
        if(minSize > oldSize){
            int newSize = 3 * oldSize / 2 + 1;
            if(minSize > newSize){
                newSize = minSize;
            }
            elementData = Arrays.copyOf(elementData, newSize);
        }
    }
    
    /**
     * 下标范围判断
     * @param index
     */
    private boolean rangeCheck(int index){
        if ( index >= size || index < 0 ){
            System.out.println("索引不合法！");
            return false;
        }
        return true;
    }
    
    @Override 
    public boolean add(Object o) {
       ensureSize(size+1);
       elementData[size++] = o;
       return true;
    }

    @Override
    public boolean add(int index, Object o) {
        if (!rangeCheck(index)){
           return false; 
        }
        ensureSize(size + 1);
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = o;
        size++;
        return true;
    }

    @Override
    public Object get(int index) {
        if (!rangeCheck(index)){
            return null; 
         }
        Object o = elementData[index];
        return o;
    }

    @Override
    public Object remove(int index) {
        if (!rangeCheck(index)){
            return null; 
         }
        Object oldValue = elementData[index];
        int numMoved = size - index - 1;
        if( numMoved > 0 ){
            System.arraycopy(elementData, index + 1, elementData, index, numMoved);
        }
        return oldValue;
    }

    @Override
    public int size() {
        return size;
    }
    
    
    
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("[");
        for (int i = 0; i < size; i++){
            Object o = elementData[i];
            s.append(o.toString());
            if( i < size-1 ){
                s.append(",");
            }
        }
        s.append("]");
        return s.toString();
    }

    /**
     * 判断当前集合是否为空
     * @return
     */
    public boolean isEmpty(){
        return size == 0;
    }
    
    public static void main(String[] args) {
      MyList list =  new MyArrayList();
      list.add("a");
      list.add("b");
      list.add("c");
      list.add(2,"d");
      Object o = list.get(5);
      System.out.println(o);
      System.out.println(list.size());
      System.out.println(list);
    }
}
