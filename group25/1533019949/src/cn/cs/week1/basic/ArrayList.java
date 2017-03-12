package cn.cs.week1.basic;

import java.util.Arrays;

public class ArrayList implements List{
	private final int DEFAULT_CAPACITY = 100;//默认容量
	private int capacity = DEFAULT_CAPACITY;//当前容量
	private int size;//当前队列长度
	
	private Object[] elementData = new Object[DEFAULT_CAPACITY];

	private void ensureCapacity(int miniCap){
		if(capacity < miniCap){
			while(capacity < miniCap){
				capacity <<= 1;
			}
			elementData = Arrays.copyOf(elementData,capacity);
		}
	}

	public void add(Object o){
		ensureCapacity(size + 1);
		elementData[size] = o;
		size ++;
	}

	public void add(int index, Object o){
		if(index < 0 || index > size){
			throw new IndexOutOfBoundsException("插入索引越界");
		}
		ensureCapacity(size + 1);
		for(int p=size-1;p>=index;p--){//向后移动index后面的元素
			elementData[p+1] = elementData[p];
		}
		elementData[index] = o;
		size ++;
	}
	
	public Object get(int index){
		return elementData[index];
	}
	
	public Object remove(int index){
		if(index < 0 || index > size - 1){
			throw new IndexOutOfBoundsException("删除索引越界");
		}
		Object o = elementData[index];
		for(int p=index;p<size-1;p++){
			elementData[p] = elementData[p+1];
		}
		size --;
		return o;
	}
	
	public int size(){
		return size;
	}
	
	public Iterator iterator(){
		return new Iterator() {
			private int index;

			@Override
			public boolean hasNext() {
				return index < size - 1;
			}

			@Override
			public Object next() {
				return elementData[index++];
			}
		};
	}
	
}
