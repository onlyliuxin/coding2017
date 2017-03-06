package cn.xl;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;


/** 
 * 1. ArrayList 是一个数组队列，相当于动态数组。与Java数组对比容量能动态增加，
 * 2. 拥有添加、删除、修改、遍历等功能。
 * @author XIAOLONG
 *
 * @param <E>
 */

public class MyArrayList implements MyList {


	private int size = 0;  

	private final int initialCapacity = 3; 
	
	private Object[] elementData = new Object[100]; 

	private static final Object[] EMPTY_ELEMENTDATA = {};

	private int modCount = 0;

	/**
	 * 构造一个默认初始容量为3的空列表
	 * 
	 */
	public MyArrayList() {  
		elementData  =  new Object[initialCapacity];  
	}  

	/**
	 * 构造一个指定初始容量的空列表
	 * @param initialCapacity
	 */
	public MyArrayList(int initialCapacity) {  

		if (initialCapacity < 0){
			throw new IllegalArgumentException("Illegal initialCapacity: "+ initialCapacity); 
		}else if(initialCapacity == 0){
			elementData  =  EMPTY_ELEMENTDATA;
		}else{
			elementData  =  new Object[this.initialCapacity];  
		}
	}  

	/**
	 * 
	 * 构造一个包含指定collection的元素的列表，这些元素按照该collection的迭代器返回它们的顺序排列
	 * 有MySubClass是MyClass的子类。
	 * Collection<MyClass> myCollection;  
	 * Collection<MySubClass> mySubCollection;  
	 * ArrayList<MyClass> myList = new ArrayList<MyClass>(myCollection);
	 * 也可以：ArrayList<MyClass> myList = new ArrayList<MyClass>(mySubCollection);
	 * 凡是MyClass或者MyClass的子类的Collection均可以构造成ArrayList<MyClass>
	 * @param c 
	 */
	public MyArrayList(Collection<? extends Object> c) {  
		elementData =  c.toArray();  
		if((size = elementData.length) != 0){
			//c.toArray might (incorrectly) not return Object[] (see 6260652)  
			//官方bug，向下转型不安全
			//返回若不是Object[]将调用Arrays.copyOf方法将其转为Object[]  
			if (elementData.getClass() != Object[].class)  
				elementData = Arrays.copyOf(elementData, size, Object[].class);  
		}else{
			elementData = EMPTY_ELEMENTDATA;
		}
	}  


	/**
	 * 用指定的元素替代此列表中指定位置上的元素
	 * @param index
	 * @param element
	 * @return  Object(以前位于该位置上的旧元素)
	 */
	public Object set(int index, Object element) {  
		if (index >= size())
			throw new RuntimeException("The Index: "+index+" is out of band.");

		Object oldValue =  elementData[index];  
		elementData[index] = element;  
		return oldValue;  
	}  

	/**
	 * 添加元素至列表尾部
	 * @param e
	 */                                             
	public void add(Object e) {  
		if (e == null) {  
			throw new RuntimeException("The value should not be null.");  
		}  
		if (size() >= initialCapacity) {  
			ensureCapacity(size() + 1);  
		}  
		elementData [size] = e;  
		size++;  
	}  

	/**
	 * 将元素添加到列表指定位置
	 * @param index
	 * @param element
	 */
	public void add(int index, Object o) {  
		if (index >= size || index < 0)  
			throw new RuntimeException("The Index: "+index+" is out of band.");  
		// 如果数组长度不足，将进行扩容。  
		ensureCapacity(size+1);
		// 将 elementData中从Index位置开始、长度为size-index的元素，  
		// 拷贝到从下标为index+1位置开始的新的elementData数组中。  
		// 即将当前位于该位置的元素以及所有后续元素右移一个位置。  
		System.arraycopy(elementData, index, elementData, index + 1, size - index);  
		elementData[index] = o;  
		size++;  
	}


	/**
	 * 返回此列表中指定位置上的元素
	 * @param index
	 * @return
	 */
	public Object  get(int index) {  
		if (index >= size) {  
			throw new RuntimeException("The index:" + index + " is out of band.");  
		}  
		return elementData [index];  
	}  

	/**
	 * 删除指定位置元素
	 * @param  删除元素所在位置，以0开始
	 * @return Object(被删除指定位置上的旧元素)
	 */
	public Object remove(int index) {  
		if (index >= size) {  
			throw new RuntimeException("The index:" + index + " is out of band.");  
		} 
		modCount++;
		Object oldElement = elementData[index];
		//此处当然也可以System.arraycopy 实现
		for (int i = index; i < size - 1; i++) {  
			elementData [i] = elementData [i + 1];  
		}  
		elementData [size - 1] = null;  
		size--;  
		return oldElement;
	}  

	/**
	 * 数组扩容，每次增长空间为  50%+1
	 * @param 当前数组所需最小容量值
	 */
	private void ensureCapacity(int minCapacity) {  
		modCount++;
		int oldCapacity = elementData.length;  
		if (minCapacity > oldCapacity) {  
			//位移运算，相当于除以2（？）有些用 int newCapacity = (oldCapacity * 3)/2 + 1;
			int newCapacity = oldCapacity + (oldCapacity >> 1);
			if (newCapacity < minCapacity)  
				newCapacity = minCapacity;  
			elementData = Arrays.copyOf(elementData, newCapacity);  
		}  
	}  
	/**
	 * 该List中的元素个数.
	 * @return 该List中的元素个数
	 */
	public int size() {
		return size;
	}

	/**
	 * 返会改List元素序列的一个迭代器
	 * @return 该List元素序列的迭代器
	 */
	public Iterator<Object> iterator() {
		return new Itr();
	}

	private class Itr implements Iterator<Object> {
		int cursor;       // 下一个元素的索引位置
		int lastRet = -1; // 上一个元素的索引位置
		int expectedModCount = modCount;

		public boolean hasNext() {
			return cursor != size;
		}

		public Object next() {

			if (modCount != expectedModCount){
				throw new RuntimeException("This list is being modified.");

			}

			int i = cursor;
			if (i >= size){
				throw new RuntimeException("No such element.");
			}
			Object[] elementData = MyArrayList.this.elementData;
			if (i >= elementData.length){
				throw new RuntimeException("This list is being modified.");
			}

			cursor = i + 1;
			return  elementData[lastRet = i];
		}

		public void remove() {
			if (lastRet < 0)
				throw new IllegalStateException();

			if (modCount != expectedModCount){
				throw new RuntimeException("This list is being modified.");

			}

			try {
				MyArrayList.this.remove(lastRet);
				cursor = lastRet;
				lastRet = -1;
				expectedModCount = modCount;
			} catch (IndexOutOfBoundsException ex) {
				throw new RuntimeException("This list is being modified.");
			}
		}
	}

}
