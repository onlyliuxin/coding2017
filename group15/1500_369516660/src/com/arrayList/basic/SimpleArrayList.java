package com.arrayList.basic;

import java.util.Arrays;

/**
 * 
 * @author Jodie
 *
 */
public class SimpleArrayList implements List {

	private final static int default_num = 10;//创建数组时，没有定义大小，默认为10
	private Object[] elementData;
	private int size = 0;//定义数组的大小,真实数组的大小
	
	public SimpleArrayList(){
		this(default_num);
	}

	public SimpleArrayList(int size) {
		if(size<0){//判断定义数组的大小是否小于0
			throw new IllegalArgumentException();
		}else{
			 elementData = new Object[size];
		}
	}

/**
 * 重写增加函数的方法1
 */
	@Override
	public void add(Object o) {
		//判断是否需要扩容
		ifSpaceEnougn(size+1);
		elementData[size++]=o;
	}
	
/**
 * 重写增加函数的方法2
 */
	@Override
	public void add(int index, Object o) {
		checkIfOut(index);//是否越界
		ifSpaceEnougn(size+1);//是否要扩容
		System.arraycopy(elementData, index, elementData, index + 1, size-index);//将index的元素及以后的元素向后移一位
	}
/**
 * 得到指定下标的数据
 */
	@Override
	public Object get(int index) {
		checkIfOut(index);
		return elementData[index];
	}
/**
 * 删除指定下标的数据
 */
	@Override
	public Object remove(int index) {
		Object value = get(index);
		int numRemove = size - index - 1;
		if(numRemove > 0){
			System.arraycopy(elementData, index+1, elementData, index, size - index);//集体向前进一位
		}
		elementData[--size] = null;
		return value;
	}
/**
 * 删除指定的数据	
 */
	@Override
	public String remove(Object o) {
		if(contains(o)){
			remove(indexOf(o));
			return "删除成功";
		}else{
			return "要删除的数据不在数组中，删除失败";
		}
	}
/**
 * 判断是否需要扩容
 * @param size
 */
	private void ifSpaceEnougn(int size) {
		if(size>default_num){
			exlicitSpace(size);
		}
		if(size<0){//当size超过Integer.MAX_VALUE时，会变为负数
			throw new OutOfMemoryError();
		}
	}
/**
 * 数组扩容方法
 * @param 
 */
	private void exlicitSpace(int size) {
		final int max_arrayLength = Integer.MAX_VALUE-8;
		int newLength = elementData.length*2;//一次性扩容为原来的两倍，避免频繁的扩容
		if(newLength - size<0){
			newLength = size;
		}
		if(newLength > max_arrayLength){//避免扩容后的大小超过最大值
			newLength = (size > max_arrayLength ? Integer.MAX_VALUE : max_arrayLength);
		}
		elementData = Arrays.copyOf(elementData, newLength);
	}

/**
 * 判断是否越界
 * @param index
 */
	private void checkIfOut(int index) {
		if(index<0 || index>size){
			throw new IndexOutOfBoundsException();
		}
	}

/**
 * 找到指定的数的下标
 * @param o
 * @return
 */
private int indexOf(Object o) {
	if(o!=null){
		for(int i=0;i<size;i++){
			if(elementData[i].equals(o)){
				return i;
			}
		}
	}else{
		for(int i=0;i<size;i++){
			if(elementData[i] == null){
				return i;
			}
		}
	}
	return -1;
}

/**
 * 判断数组中是否存在这个数据
 * @param o
 * @return
 */
	private boolean contains(Object o) {
		return indexOf(o) >= 0;
	}

	@Override
	public int size() {
		return size;
	}


}
