package com.ikook.basic_data_structure;

/**
 * @author ikook;  QQ号码: 935542673
 */
public class MyArrayList implements MyList{

	private Object[] elementData;
	
	private int size;
	
	/**
	 * 使Object[]的长度默认为10；
	 */
	public MyArrayList() {
		this(10);
	}
	 
	/**
	 * 在构造函数中初始化集合的长度
	 * @param initialCapacity
	 */
	public MyArrayList(int initialCapacity) {
		if(initialCapacity < 0) {
			try {
				throw new Exception();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		this.elementData = new Object[initialCapacity];
	}

	/**
	 * 在集合中添加元素
	 * @param obj
	 */
	public void add(Object obj) {
		
		ensureCapacity();
		elementData[size++] = obj;
		
	}
	
	/**
	 * 添加元素到集合的指定位置
	 * @param index
	 * @param obj
	 */
	public void add(int index, Object obj) {
		rangeCheck(index);
		ensureCapacity();
	
		System.arraycopy(elementData, index, elementData, index + 1, size-index);
		elementData[index] = obj;
		size++;
	}
	
	/**
	 * 返回集合的长度
	 * @return
	 */
	public int size() {
		return size;
	}
	
	/**
	 * 判断集合是非为空
	 * @return
	 */
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
	 * 获取集合指定位置的元素
	 * @param index
	 * @return
	 */
	public Object get(int index) {
		rangeCheck(index);
		return elementData[index];
	}
	
	/**
	 * 删除指定位置的对象
	 * @param index
	 */
	public Object remove(int index) {
		
		rangeCheck(index);
		
		Object oldValue = elementData[index];
		
		int numMoved = size - index - 1;
	    if (numMoved > 0){
	   		System.arraycopy(elementData, index+1, elementData, index,
                       numMoved);
	    }
	    elementData[--size] = null; 
	    
	    return oldValue;
	}
	
	/**
	 * 删除指定的对象(Object 对象)
	 * @param obj
	 */
	public boolean remove(Object obj){
		for(int i = 0; i < size; i++) {
			if(get(i).equals(obj)) {
				remove(i);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 更改集合中指定位置的元素，并返回原来的对象
	 * @param index
	 * @param obj
	 * @return
	 */
	public Object set(int index, Object obj) {
		rangeCheck(index);
		
		Object oldValue = elementData[index];
		elementData[index] = obj;
		
		return oldValue;
	}
	
	/**
	 * 集合扩容封装类
	 */
	private void ensureCapacity() {
		if(size == elementData.length) {
			Object[] newArray = new Object[size * 2 + 1];
			System.arraycopy(elementData, 0, newArray, 0, elementData.length);
			elementData = newArray;
		}
	}
	
	/**
	 * 判断集合范围是否越界的封装类
	 * @param index
	 */
	private void rangeCheck(int index) {
		if(index < 0 || index >= size) {
			try {
				throw new Exception("索引异常");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}