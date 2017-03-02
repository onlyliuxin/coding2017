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


	//�޳����캯��
	public MyArrayList(){
		this(DEFAULT_CAPACITY);
	}
	
	public MyArrayList(int size){
		if (size < 0){
			throw new IllegalArgumentException("Ĭ�ϵĴ�С" + size);
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
		//�ж��Ƿ񳬹���ʼ�������Ƿ���Ҫ����
		if (size > DEFAULT_CAPACITY){
			explicitCapacity(size);
		}
		if (size < 0){
			throw new OutOfMemoryError();
		}
	}
	
	private void explicitCapacity(int capacity){
		int oldCapacity = elementData.length;
		//������=������ + ��������/2�� ����1.5�������Ʋ������൱�ڳ���2��
		int newLength = oldCapacity + (oldCapacity >> 1);
		if (newLength - capacity < 0){
			newLength = capacity;
		}
		//�ж�newLength�ĳ���
		//����������涨���������󳤶����ж�Ҫ��Ҫ�����ݿռ��Ƿ����������󳤶�
		//���������newLengthΪ MAX_VALUE ������Ϊ MAX_ARRAY_LENGTH��
		if (newLength > (MAX_ARRAY_LENGTH)){
			newLength = (capacity > MAX_ARRAY_LENGTH ? Integer.MAX_VALUE : MAX_ARRAY_LENGTH);
		}
		//����copyof��������
		elementData = Arrays.copyOf(elementData, newLength);
	}
	
	public void add(int index, Object o){
		
		checkRangeForAdd(index);
		isCapacityEnough(size +1);
		// �� elementData�д�Indexλ�ÿ�ʼ������Ϊsize-index��Ԫ�أ�  
	    // ���������±�Ϊindex+1λ�ÿ�ʼ���µ�elementData�����С�  
	    // ������ǰλ�ڸ�λ�õ�Ԫ���Լ����к���Ԫ������һ��λ�á�
		System.arraycopy(elementData, index, elementData, index + 1, size - index);
		elementData[index] = o;
		size++;
	}
	
	//�ж��Ƿ�Խ��
	private void checkRangeForAdd(int index){
		if (index < 0 || index > size){
			throw new IndexOutOfBoundsException("ָ����indexԽ��");
		}
	}
	
	// ���ش��б���ָ��λ���ϵ�Ԫ�ء� 
	public Object get(int index){
		checkRange(index);
		return elementData[index];
	}
	
	//�ж��Ƿ�Խ��
	private void checkRange(int index){
		if (index >= size || index < 0){
			throw new IndexOutOfBoundsException("ָ����indexԽ��");
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
	
	//������
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