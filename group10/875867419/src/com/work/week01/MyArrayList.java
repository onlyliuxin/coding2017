package com.work.week01;

import java.io.Serializable;
import java.util.Arrays;

/**
 * 实现List<E>接口，以数组的方式实现自己的ArrayList
 * @author denghuaijun
 *
 * @param <E>
 */
public class MyArrayList<E> implements MyList<E>,Serializable {

	private static final long serialVersionUID = 4145346362382387995L;
	
	/**
	 * 设置<MyArrayList>默认大小
	 */
	private static final int DEFAULT_CAPACITY = 10; 
	
	/**
	 * 设置<MyArrayList>默认空数组
	 */
	private static final Object[] EMPTY_ELEMENTDATA = {};
	
	transient Object[] elementData;
	
	/**
	 * <MyArrayList>大小
	 */
	private int size;
	
	public MyArrayList(){
		this.elementData = EMPTY_ELEMENTDATA;
	}
	
	public MyArrayList(int capacity){
		if(capacity > 0){
			this.elementData = new Object[capacity];
		}else if(capacity == 0){
			this.elementData = EMPTY_ELEMENTDATA;
		}else{
			throw new IllegalArgumentException("非法参数");
		}
	}
	
	private void ensureCapacity(int minCapacity){
		if(this.elementData == EMPTY_ELEMENTDATA){
			minCapacity = Math.max(minCapacity, DEFAULT_CAPACITY);
		}
		if(minCapacity > elementData.length){//索引位置大于现有数组长度
			grow(minCapacity);
		}
	}
	
	private void grow(int minCapacity){
		int oldCapacity = elementData.length;	
		int newCapacity = oldCapacity + (oldCapacity >> 1);
		if(newCapacity < minCapacity){
			newCapacity = minCapacity;
		}
		elementData = Arrays.copyOf(elementData, newCapacity);
	}

	@Override
	public boolean add(E element) {
		ensureCapacity(size + 1);
		elementData[size++] = element;
		return true;
	}

	@Override
	public void add(int index, E element) {
		//确认index是否越界
		checkAddRange(index);
		//确认数组长度是否足够
		ensureCapacity(size + 1);
		System.arraycopy(elementData, index, elementData, index + 1, size - index);
		elementData[index] = element;
		size++;
	}
	
	private void checkAddRange(int index){
		if(index < 0 || index > size){//index == size 则在数组最后加元素
			throw new IndexOutOfBoundsException("数组越界");
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public E get(int index) {
		checkRange(index);
		return (E) elementData[index];
	}
	
	private void checkRange(int index){
		if(index < 0 || index >= size){
			throw new IndexOutOfBoundsException("数组越界");
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public E remove(int index) {
		checkRange(index);
		E element = (E) elementData[index];
		int numMoved = size - index - 1;
		if(numMoved > 0){
			System.arraycopy(elementData, index + 1, elementData, index, numMoved);
		}
		elementData[size--] = null;
		return element;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	public int indexOf(Object o) {
		if(o == null){
			for(int i=0;i<size;i++){
				if(elementData[i] == null){
					return i;
				}
			}
		}else{
			for(int i=0;i<size;i++){
				if(o.equals(elementData[i])){
					return i;
				}
			}
		}
		return -1;
	}

	public int lastIndexOf(Object o) {
		if(o == null){
			for(int i=size-1;i>=0;i--){
				if(elementData[i] == null){
					return i;
				}
			}
		}else{
			for(int i=size-1;i>=0;i--){
				if(o.equals(elementData[i])){
					return i;
				}
			}
		}
		return -1;
	}

	@Override
	public MyIterator<E> iterator() {
		return new MyIter();
	}
	
	private class MyIter implements MyIterator<E>{
		
		int flag = -1;
		
		public MyIter(){
			flag = size;	//数组长度	
		}

		@Override
		public boolean hasNext() {
			return flag > 0;
		}

		@SuppressWarnings("unchecked")
		@Override
		public E next() {
			if(!hasNext()){
				throw new IndexOutOfBoundsException("索引值超出数组范围");
			}
			return (E) elementData[size-(flag--)];
		}
		
	}
	public static void main(String[] args) {
		MyArrayList<String> array = new MyArrayList<String>();
		array.add("1");
		array.add("2");
		array.add("3");
		array.add("4");
		array.remove(2);
		array.add(2, "1");
		System.out.println("size="+array.size());
		System.out.println("indexOf(3)="+array.indexOf("3"));
		System.out.println("lastIndexOf(1)="+array.lastIndexOf("1"));
		MyIterator<String> itr = array.iterator();
		while(itr.hasNext()){
			System.out.println(itr.next());
		}
	}
}
