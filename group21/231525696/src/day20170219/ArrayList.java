package day20170219;

import java.util.Arrays;

public class ArrayList implements List{
	
	//默认容量
	private static final int DEFAULT_CAPACITY = 10;
	
	//数据
	private Object[] elementData = new Object[DEFAULT_CAPACITY];
	
	//数据当前索引（arraylist的大小）
	private int currentIndex = 0;
	
	public ArrayList (){
		this.elementData = new Object[]{};
	}
	
	public ArrayList (int capacity){
		if (capacity > 0) {
			this.elementData = new Object[capacity];
		} else if (capacity == 0) {
			this.elementData = new Object[]{};
		} else {
			throw new RuntimeException("Illegal Capacity: " + capacity);
		}
		
	}
	
	//扩容
	private void grow (int minCapacity) {
		int oldCapacity = elementData.length;
		int newCapacity = oldCapacity * 2;
		if (newCapacity - minCapacity < 0) {
			newCapacity = minCapacity;
		}
		elementData = Arrays.copyOf(elementData, newCapacity);
	}
	
	//试探边界
	private void trialBound(int index){
		if (index > elementData.length || index < 0){
			throw new RuntimeException("OutOfBound:" + index);
		}
	}
	
	@Override
	public void add(Object obj) {
		if (currentIndex > elementData.length - 1) {
			grow(elementData.length);
		}
		elementData[currentIndex] = obj;
		currentIndex ++;
	}
	
	@Override
	public void add(Object obj, int index) {
		trialBound(index);
		//把原来的数据中需要加入元素以后的数据复制到原来索引+1的地方
		System.arraycopy(elementData, index, elementData, index + 1, currentIndex - index);
		//用新元素替换旧元素
		elementData[index] = obj;
		currentIndex ++;
	}
	
	@Override
	public Object get(int index) {
		trialBound(index);
		return elementData[index];
	}
	
	@Override
	public Object remove(int index) {
		trialBound(index);
		Object oldElement = elementData[index];
		int moveIndex = currentIndex - index - 1;
		if (moveIndex > 0) {
			//把原来的数据中需要删除之后的moveIndex个元素复制到之前一格
			System.arraycopy(elementData, index + 1, elementData, index, moveIndex);
		}
		//删除最后一个元素
		elementData[--currentIndex] = null;
		
		return oldElement;
	}
	
	@Override
	public int size() {
		return currentIndex;
	}
	
	
}
