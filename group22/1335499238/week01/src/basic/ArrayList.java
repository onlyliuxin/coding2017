package basic;

import java.util.Arrays;

public class ArrayList implements List{

	private int size;
	
	private Object[] elementData = {};
	
	public ArrayList(){
		this(16);
	}
	
	public ArrayList(int capacity){
		if(capacity > 0){
			elementData = new Object[capacity];
		}else if(capacity == 0){
			
		}else{
			throw new IllegalArgumentException("initsize:"+capacity);
		}
	}
	
	@Override
	public void add(Object o) {
		ensureCapacity(elementData.length + 1);
		 elementData[size++] = o;
	}

	@Override
	public void add(int index, Object o) {
		checkIndex(index);
		ensureCapacity(size + 1);
		System.arraycopy(elementData, index, elementData, index+1, size - index);
		elementData[index] = o;
		size++;
	}

	@Override
	public Object get(int index) {
		checkIndex(index + 1);
		return elementData[index];
	}

	@Override
	public Object remove(int index) {
		checkIndex(index + 1);
		Object removeparam = elementData[index];
		int numMoved = size - index - 1;
		if(numMoved > 0){
			System.arraycopy(elementData, index+1, elementData, index, numMoved);
		}
		elementData[--size] = null;	//置空末尾元素
		return removeparam;
	}

	@Override
	public int size() {
		return size;
	}
	
	/**
	 * 检查是否越界
	 * @param index
	 */
	public void checkIndex(int index){
		if(index > size || index < 0){
			throw new IndexOutOfBoundsException("current:"+index+"  size:"+size);
		}
	}
	
	/**
	 * 判断当前容量是否足够
	 * @param minCapacity
	 */
	private void ensureCapacity(int minCapacity){
		if(minCapacity > elementData.length){
			grow(minCapacity);
		}
	}
	
	/**
	 * 扩容
	 * @param minCapacity
	 */
	private void grow(int minCapacity){
		 Object [] target = new Object [minCapacity+10];
		 System.arraycopy(elementData, 0, target, 0, elementData.length);
		 elementData = target;
	}
	
	public Iterator iterator() {
		return new ArrayListIterator();
	}

	private class ArrayListIterator implements Iterator{

		private int current = 0;
		
		@Override
		public boolean hasNext() {
			return current < size;
		}

		@Override
		public Object next() {
			int i = current;
			if(current >= size){
				throw new IndexOutOfBoundsException("current:"+current+"  size:"+size);
			}
			current++;
			return elementData[i];
		}
		
	}

	@Override
	public String toString() {
		return Arrays.toString(Arrays.copyOf(elementData, size));
	}
	
}
