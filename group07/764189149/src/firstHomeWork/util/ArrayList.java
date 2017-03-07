package firstHomeWork.util;

import java.util.NoSuchElementException;

/**
 * @Description: 基于数组的列表
 * @author: leijing
 * @date: 2017年2月21日 下午9:03:17
 * @param <E>
 */
public class ArrayList<E> implements List<E> {
	private static int initialCapacity = 10 ;//数组默认初始容量
	Object[] elements;//元素的数组
	private int size;//元素的个数
	
	public ArrayList(){
		this(initialCapacity);
	}
	public ArrayList(int capacity){
		elements = new Object[capacity];
	}
	private void ensureCapacity(int minCapacity){
		if(minCapacity > 0){
			
		}
	}
	@Override
	public boolean add(E e) {
		ensureCapacity(size + 1);
		elements[size++] = e;
		return true;
	}

	@Override
	public E remove(int index) {
		rangeCheck(index);
		E oldElement = (E) elements[index];
		//将其后的元素前移
		int needMovedNum = size - index - 1;
		move(elements, index+1, elements,index, needMovedNum);
		size--;
		return oldElement;
	}
	
	/**
	 * @Description: 移动数组中的元素
	 * @param src 原数组
	 * @param from 复制元素起始下标
	 * @param dest 目标元素数组
	 * @param num 要复制的元素个数
	 * @return: void
	 * @author: leijing  
	 * @date: 2017年2月22日 下午7:54:08
	 */
	private void move(Object[] src , int srcPosition , Object[] dest , int destPosition, int num){
		for(int i = 0 ; i < num ; i ++){
			dest[destPosition++] = src[srcPosition++];
		}
	}
	
	/**
	 * @Description: 检查下标是否正确，如果越界抛出异常
	 * @param index
	 * @return: void
	 * @author: leijing  
	 * @date: 2017年2月22日 下午7:52:59
	 */
	private void rangeCheck(int index){
		if(index < 0 || index > size){
			throw new IndexOutOfBoundsException();
		}
	}

	@Override
	public boolean remove(Object o) {
		if(o == null){
			for (int index = 0; index < size; index++) {
				if(elements[index] == null){
					remove(index);
					return true;
				}
			}
		}else{
			for (int index = 0; index < size; index++) {
				if(o.equals(elements[index])){
					remove(index);
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public E get(int index) {
		rangeCheck(index);
		return (E) elements[index];
	}

	@Override
	public E set(int index, E e) {
		rangeCheck(index);
		E oldElement = (E) elements[index];
		elements[index] = e;
		return oldElement;
	}

	@Override
	public boolean contains(Object o) {
		return indexOf(o) >= 0;
	}
	
	private int indexOf(Object o){
		if(o == null){
			for (int index = 0; index < size; index++) {
				if(elements[index] == null){
					return index;
				}
			}
		}else{
			for (int index = 0; index < size; index++) {
				if(o.equals(elements[index])){
					return index;
				}
			}
		}
		return -1;
	}

	@Override
	public void clear() {
		for (int index = 0; index < size; index++) {
			elements[index] = null;
		}
		size = 0;
	}

	@Override
	public Iterator<E> iterator() {
		return new ArraylistIterator();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int index = 0; index < size; index++) {
			if(index == size -1){
				sb.append(elements[index]);
			}else{
				sb.append(elements[index]).append(",");
			}
			
		}
		return sb.toString();
	}
	private class ArraylistIterator implements Iterator<E>{
		private int position;
		
		@Override
		public boolean hasNext() {
			return position != size;
		}

		@Override
		public E next() {
			Object[] elements = ArrayList.this.elements;
			int i = position;
			if(i >= size){
				throw new NoSuchElementException();
			}
			position = i + 1;
			return (E) elements[i+1];
		}

		@Override
		public void remove() {
			if(position > size){
				throw new NoSuchElementException();
			}
			ArrayList.this.remove(position);
		}
		
	}
	
	

}
