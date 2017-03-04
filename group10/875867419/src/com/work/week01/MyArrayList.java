package com.work.week01;

import java.io.Serializable;
import java.util.Arrays;

/**
 * ʵ��List<E>�ӿڣ�������ķ�ʽʵ���Լ���ArrayList
 * @author denghuaijun
 *
 * @param <E>
 */
public class MyArrayList<E> implements MyList<E>,Serializable {

	private static final long serialVersionUID = 4145346362382387995L;
	
	/**
	 * ����<MyArrayList>Ĭ�ϴ�С
	 */
	private static final int DEFAULT_CAPACITY = 10; 
	
	/**
	 * ����<MyArrayList>Ĭ�Ͽ�����
	 */
	private static final Object[] EMPTY_ELEMENTDATA = {};
	
	transient Object[] elementData;
	
	/**
	 * <MyArrayList>��С
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
			throw new IllegalArgumentException("�Ƿ�����");
		}
	}
	
	private void ensureCapacity(int minCapacity){
		if(this.elementData == EMPTY_ELEMENTDATA){
			minCapacity = Math.max(minCapacity, DEFAULT_CAPACITY);
		}
		if(minCapacity > elementData.length){//����λ�ô����������鳤��
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
		//ȷ��index�Ƿ�Խ��
		checkAddRange(index);
		//ȷ�����鳤���Ƿ��㹻
		ensureCapacity(size + 1);
		System.arraycopy(elementData, index, elementData, index + 1, size - index);
		elementData[index] = element;
		size++;
	}
	
	private void checkAddRange(int index){
		if(index < 0 || index > size){//index == size ������������Ԫ��
			throw new IndexOutOfBoundsException("����Խ��");
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
			throw new IndexOutOfBoundsException("����Խ��");
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
			flag = size;	//���鳤��	
		}

		@Override
		public boolean hasNext() {
			return flag > 0;
		}

		@SuppressWarnings("unchecked")
		@Override
		public E next() {
			if(!hasNext()){
				throw new IndexOutOfBoundsException("����ֵ�������鷶Χ");
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
