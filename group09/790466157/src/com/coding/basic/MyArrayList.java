package com.coding.basic;
import java.util.Arrays;
import java.util.NoSuchElementException;

import com.coding.basic.List;
import com.coding.basic.Iterator;

public class MyArrayList implements List {
	
	private int size = 0;
	
	private Object[] elementData = new Object[100];
	
	private final static int MAX_ARRAY_LENGTH = Integer.MAX_VALUE;

	private static final int DEFAULT_CAPACITY = 10;


	//无常构造函数
	public MyArrayList(){
		this(DEFAULT_CAPACITY);
	}
	
	public MyArrayList(int size){
		if (size < 0){
			throw new IllegalArgumentException("默认的大小" + size);
		}
		else{
			elementData = new Object[size];
		}
	}

	public void add(Object o){
		isCapacityEnough(size+1);
		elementData[size++] = o;
	}
	
	private void isCapacityEnough(int size){
		//判断是否超过初始容量，是否需要扩容
		if (size > DEFAULT_CAPACITY){
			explicitCapacity(size);
		}
		if (size < 0){
			throw new OutOfMemoryError();
		}
	}
	
	private void explicitCapacity(int capacity){
		int oldCapacity = elementData.length;
		//新容量=旧容量 + （旧容量/2） 扩容1.5倍【右移操作符相当于除以2】
		int newLength = oldCapacity + (oldCapacity >> 1);
		if (newLength - capacity < 0){
			newLength = capacity;
		}
		//判断newLength的长度
		//如果超过上面定义的数组最大长度则判断要需要的扩容空间是否大于数组最大长度
		//如果大于则newLength为 MAX_VALUE ，否则为 MAX_ARRAY_LENGTH。
		if (newLength > (MAX_ARRAY_LENGTH)){
			newLength = (capacity > MAX_ARRAY_LENGTH ? Integer.MAX_VALUE : MAX_ARRAY_LENGTH);
		}
		//调用copyof进行扩容
		elementData = Arrays.copyOf(elementData, newLength);
	}
	
	public void add(int index, Object o){
		
		checkRangeForAdd(index);
		isCapacityEnough(size +1);
		// 将 elementData中从Index位置开始、长度为size-index的元素，  
	    // 拷贝到从下标为index+1位置开始的新的elementData数组中。  
	    // 即将当前位于该位置的元素以及所有后续元素右移一个位置。
		System.arraycopy(elementData, index, elementData, index + 1, size - index);
		elementData[index] = o;
		size++;
	}
	
	//判断是否越界
	private void checkRangeForAdd(int index){
		if (index < 0 || index > size){
			throw new IndexOutOfBoundsException("指定的index越界");
		}
	}
	
	// 返回此列表中指定位置上的元素。 
	public Object get(int index){
		checkRange(index);
		return elementData[index];
	}
	
	//判断是否越界
	private void checkRange(int index){
		if (index >= size || index < 0){
			throw new IndexOutOfBoundsException("指定的index越界");
		}
	}
	
	public Object remove(int index){
		Object value = get(index);
		int moveSize = size - index -1;
		if (moveSize >0){
			System.arraycopy(elementData, index +1, elementData, index, size - index -1);
			
		}
		elementData[--size] = null;
		return value;
	}
	
	public int size(){
		return size;
	}
	
	//迭代器
	public Iterator iterator(Object o){
		 return new ArrayListIterator();
	}
	
	
    private class ArrayListIterator implements Iterator{
        private int currentIndex=0;
        
        public boolean hasNext() {
			return currentIndex < size();
        }

        public Object next() {
            if (!hasNext()){
            	throw new NoSuchElementException();
            }
			return new Object[currentIndex + 1];
        }
    }
	
}