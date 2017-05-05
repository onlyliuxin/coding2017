package list;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

public class ArrayList implements List{
	private int size ;
	private Object [] elementData;
	private static final Object [] EMPTY_ELEMENTDATA ={};
	private static final int DEFAULT_CAPACITY = 10;
	private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
	public ArrayList(){
		this.elementData = EMPTY_ELEMENTDATA;
	}
	public ArrayList(int limit){
		if(limit<0)
			throw new IllegalArgumentException("Illegal Capacity: "+limit);
		this.elementData = new Object[limit];
	}
	/**
	 * 添加元素
	 * @param o 元素
	 * @return 是否成功添加
	 */
	public boolean add(Object o){
		ensureCapacityInternal(size+1);
		elementData[size++]=o;
		return true;
	}
	/**
	 * 添加元素
	 * @param index 添加位置
	 * @param o 元素
	 * @return 是否成功添加
	 */
	public void  add(int index, Object o) {
		if (index > size || index < 0) {
			throw new IndexOutOfBoundsException("Index: "+index+", Size: "+size);
		}
		ensureCapacityInternal(size + 1);
		//将A数组的的a位置开始 复制至B数组的a+1位置进行覆盖--->等于将B数组a位置后面的所有元素向后移了一位。
		System.arraycopy(elementData, index, elementData, index+1, size-index);
		elementData[index] = o;
		size++;
	}
	
	/**
	 * 删除元素
	 * @param o 元素
	 * @return 是否成功删除
	 */
	public boolean remove(Object o) {
		if (o == null) {
			for (int index = 0; index < size; index++) {
				if (elementData[index] == null) {
					fastRemove(index);
					return true;
				}
			}
		} else {
			for (int i = 0; i < size; i++) {
				if(elementData[i].equals(o)){
					fastRemove(i);
					return true;
				}	
			}
		}

		return false;
	}
	/**
	 * 删除元素
	 * @param index 需要删除的元素的位置
	 * @return 被删除的元素
	 */
	public Object remove(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
		}
		Object o =elementData[index];
		fastRemove(index);
		return o;
	}
	
	
	
	/**
	 * 查询元素
	 * @param index 位置 
	 * @return 被查询的元素
	 */
	public Object get(int index){
		if(index>=size)
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);		
		return elementData[index];
	}
	
	
	

	private void fastRemove(int index) {
		//需要移动的个数
		int move = size-index-1;
		System.arraycopy(elementData, index+1, elementData, index, move);
		elementData[size]=null;
		size--;
	}
	private void ensureCapacityInternal(int newLength) {
		 if (elementData == EMPTY_ELEMENTDATA) {
			 newLength = Math.max(DEFAULT_CAPACITY, newLength);
	        }
		 //如果长度超过了elementData分配的内存上限 则需要继续分配内存
		 if(newLength-elementData.length>0){
			 grow(newLength);
		 }
	}

	private void grow(int newLength) {
		int oldCapacity = elementData.length;
		int newCapacity = (oldCapacity >> 1) + oldCapacity;
		if (newCapacity - newLength < 0) {
			newCapacity = newLength;
		}
		// int 上限：2147483648
		if (newCapacity > MAX_ARRAY_SIZE) {
			newCapacity = newLength > MAX_ARRAY_SIZE ? Integer.MAX_VALUE : MAX_ARRAY_SIZE;
		}
		// 扩容
		elementData = Arrays.copyOf(elementData, newLength);
	}
	
	@Override
	public void clear() {
		for (int i = 0; i < size; i++) {
			elementData[i]=null;
		}
		size=0;
	}
	
	
	@Override
	public boolean contains(Object o) {
		if (indexOf(o) >= 0)
			return true;
		return false;
	}
	
	@Override
	public int indexOf(Object o) {
		if(o==null){
			for (int i = 0; i < size; i++) {
				if(elementData[i]==null)
					return i;				
			}
		}else{
			for (int i = 0; i < size; i++) {
				if(elementData[i].equals(o))
					return i;
			}
		}
		return -1;
	}
	
	@Override
	public boolean isEmpty() {
		return size==0;
	}
	
	
	@Override
	public Object set(int index, Object o) {
		if(index<0||index>=size)
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
		Object oldO=elementData[index];
		elementData[index]=o;
		return oldO;
	}
	@Override
	public int size() {
		
		return size;
	}
	
	@Override
	public Iterator iterator() {
		return new ArrayIterator();
	}
	private class ArrayIterator implements Iterator{
		
		int limit=ArrayList.this.size;
		int cursor;   
        int lastRet = -1; 

		@Override
		public boolean hasNext() {
			return cursor < limit;
		}

		@Override
		public Object next() {
			int i = cursor;
			if (cursor >= size) {
				throw new NoSuchElementException();
			}
			Object[] o = ArrayList.this.elementData;
			if (cursor > o.length)
				throw new ConcurrentModificationException();
			cursor = i + 1;
			return o[lastRet = i];
		}

		@Override
		public void remove() {
			  if (lastRet < 0)
	                throw new IllegalStateException();
			  try {
	                ArrayList.this.remove(lastRet);
	                cursor = lastRet;
	                lastRet = -1;
	                limit--;
	            } catch (IndexOutOfBoundsException ex) {
	                throw new ConcurrentModificationException();
	            }
		}
		
	}
	
	
	


	
	

}
