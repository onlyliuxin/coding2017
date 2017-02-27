package data2_19;

import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;


public class ArrayList implements List{
	public static final int defLen = 10;
	private Object[] elements;
	private int size;
	private int maxLen;
	
	public ArrayList(){
		size = 0;
		maxLen = defLen;
		elements = new Object[defLen];
	}
	
	/**
	 * 在ArrayList末尾处追加元素
	 * @param o 添加的元素
	 */
	public void add(Object o){
		if(size >= maxLen){
			grow();
		}
		elements[size] = o;
		size++;
	}
	
	/**
	 * 数组扩容
	 */
	private void grow(){
		maxLen = maxLen + (maxLen >> 1);
		Object[] newArr = new Object[maxLen];
		System.arraycopy(elements, 0, newArr, 0, size);
		elements = newArr;
	}
	
	/**
	 * 在指定索引处添加元素
	 * @param i 指定索引
	 * @param o 添加元素
	 */
	public void add(int i,Object o){
		//判断插入位置大于数组实际长度
		if(i > size){
			size = i;
			if(size >= maxLen){//数组大小大于数组最大容量则需要扩容
				grow();
			}
		}
		//插入位置不大于数组实际长度时，将插入位置的元素向后移。
		for (int j = size; j > i ; j++) {
			elements[j] = elements[j-1];
		}
		elements[i] = o;
		size++;
	}
	
	/**
	 * 获取传入索引的元素
	 * @param index 索引
	 * @return 返回传入索引的元素
	 */
	public Object get(int index){
		//索引不在实际范围内
		if(index < 0||index >= size){
			throw new ArrayIndexOutOfBoundsException();
		}
		for (int i = 0; i < size; i++) {
			return elements[index];
		}
		return null;
	}
	
	/**
	 * 删除指定索引元素并返回
	 * @param index
	 * @return 该索引处元素
	 */
	public Object remove(int index){
		//索引不在实际范围内
		if(index < 0||index >= size){
			throw new ArrayIndexOutOfBoundsException();
		}else{
			for (int j = index; j < size-1; j++) {
				elements[j]=elements[j+1];
			}
			size--;
			return elements[index];
		}
	}
	
	/**
	 * 获取大小
	 * @return 
	 */
	public int size(){
		return size;
	}
	
	public Iterator iterator(){
		return new ArrayListIterator();
	}
	
	private class ArrayListIterator implements Iterator{
		int cursor;
		
		@Override
		public boolean hasNext() {
			return cursor != size;
		}

		@Override
		public Object next() {
			int i = cursor;
			if(i >= size){
                throw new NoSuchElementException();
			}
			if (i >= elements.length){
				throw new ConcurrentModificationException();
			}
			cursor = i+1;        
			return elements[i];
		}
	}
	
	public static void main(String[] args) {
		ArrayList list = new ArrayList();
		list.add(0);
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(6, 6);
		list.remove(3);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(i+":"+list.get(i));
		}
		
		Iterator it =  list.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
	}
}
