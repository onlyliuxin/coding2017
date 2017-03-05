/**
 * 
 */
package org.le;

/**
 * @author yue
 * @time 2017年2月19日
 */
public class ArrayList<E> implements List<E> {
	
	private Object[] elementData;
	
	private int size;
	
	public ArrayList(int initCapcity){
		if(initCapcity < 0){
			throw new IllegalArgumentException("initCapcity 必须大于0");
		}
		elementData = new Object[initCapcity];
	}
	
	public ArrayList(){
		elementData = new Object[10];
	}

	@Override
	public void add(Object obj) {
		grow(size + 1);
		elementData[size++] = obj;
	}
	
	@Override
	public void add(int index, Object obj) {
		rangeCheckForAdd(index);
		grow(size + 1);
		System.arraycopy(elementData, index, elementData, index+1, size - index);
		elementData[index] = obj;
		size ++;
	}
	
	@Override
	public void remove(Object obj) {
		if(obj == null){
			for (int i = 0; i < size; i++) {
				if(elementData[i] == null){
					fastRemove(i);
				}
			}
		}else{
			for (int i = 0; i < size; i++) {
				if(obj.equals(elementData[i])){
					fastRemove(i);
				}
			}
		}
	}

	@Override
	public E remove(int index) {
		rangeCheck(index);
		int movedNum = size - index - 1;
		E oldElement = elementData(index);
		System.arraycopy(elementData, index+1, elementData, index, movedNum);
		elementData[--size] = null;
		return oldElement;
	}
	
	@Override
	public E get(int index) {
		rangeCheck(index);
		return elementData(index);
	}

	@Override
	public E set(int index, E obj) {
		rangeCheck(index);
		E oldElement = elementData(index);
		elementData[index] = obj;
		return oldElement;
	}

	@Override
	public int indexOf(E obj) {
		if(obj == null){
			for (int i = 0; i < size; i++) {
				if(elementData[i] == null){
					return i;
				}
			}
		}else{
			for (int i = 0; i < size; i++) {
				if(obj.equals(elementData[i])){
					return i;
				}
			}
		}
		return -1;
	}
	
	/**
	 * 数组扩容
	 * @param minCapacity
	 */
	private void grow(int minCapacity) {
		if(minCapacity <= elementData.length){
			return;
		}
		int oldCapacity = elementData.length;
		int newCapacity = minCapacity + (oldCapacity >> 1);
		if(newCapacity < minCapacity){
			newCapacity = minCapacity;
		}
		if(minCapacity > Integer.MAX_VALUE){
			newCapacity = Integer.MAX_VALUE;
		}
		Object[] newArray = new Object[newCapacity];
		System.arraycopy(elementData, 0, newArray, 0, newCapacity);
		elementData = newArray;
	}
	
	@SuppressWarnings("unchecked")
	 private E elementData(int index){
		return (E) elementData[index];
	}
	
	private void fastRemove(int i) {
		int numMoved = size - i -1;
		if(numMoved > 0){
			System.arraycopy(elementData, i+1, elementData, i, numMoved);
		}
		elementData[-- size] = null;
	}
	
	private void rangeCheck(int index){
		if(index >= size || index <0)
			throw new IndexOutOfBoundsException("index:"+index+",size:"+size);
	}
	
	private void rangeCheckForAdd(int index){
		if(index > size || index <0)
			throw new IndexOutOfBoundsException("index:"+index+",size:"+size);
	}
}
