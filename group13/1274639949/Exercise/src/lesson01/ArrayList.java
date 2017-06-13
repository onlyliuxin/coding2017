package lesson01;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<E> implements List<E> {

	private static final int DEFAULT_CAPACITY = 20;
	private static final int DEFAULT_INCREMENT = 20;
	
	private int size;
	
	private Object[] elementData;
	
	public ArrayList() {
		this(DEFAULT_CAPACITY);
	}
	
	public ArrayList(int capacity) {
		size = 0;
		elementData = new Object[capacity];
	}


	@Override
	public boolean add(E e) {
		ensureCapacity();
		elementData[size] = e;
		size++;
		return true;
	}

	private void ensureCapacity() {
		if(size == elementData.length){
			elementData = Arrays.copyOf(elementData, elementData.length + DEFAULT_INCREMENT);
		}
	}

	@Override
	public void add(int index, E element) {
		if(index < 0 || index > size){
			throw new IndexOutOfBoundsException("Error index :" + index);
		}
		ensureCapacity();
		System.arraycopy(element, index, element, index + 1, size - index);
		elementData[index] = element;
		size++;
	}

	@Override
	
	public void clear() {
		elementData = new Object[elementData.length];
		size = 0;
	}

	@Override
	public boolean contains(Object o) {
		if(o == null){
			for(int i = 0; i < size; i++){
				if(elementData[i] == null){
					return true;
				}
			}
		}else{
			for(int i = 0; i < size; i++){
				if(o.equals(elementData[i])){
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public int indexOf(Object o) {
		if(o == null){
			for(int i = 0; i < size; i++){
				if(elementData[i] == null){
					return i;
				}
			}
		}else{
			for(int i = 0; i < size; i++){
				if(o.equals(elementData[i])){
					return i;
				}
			}
		}
		return -1;
	}

	@SuppressWarnings("unchecked")
	@Override
	public E get(int index) {
		checkIndex(index);
		return (E) elementData[index];
	}

	private void checkIndex(int index) {
		if(index < 0 || index >= size){
			throw new IndexOutOfBoundsException("Illegal index:" + index);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public E remove(int index) {
		checkIndex(index);
		
		E e = (E) elementData[index];
		//此处应注意要判断index是否为elementData最后一个元素的下标，因为在下面的arrayCopy方法中要访问index+1的位置，此时有可能发生数组越界。
		if(index == size -1){
			size --;
		}else{
			System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
		}
		return e;
	}

	@Override
	public boolean remove(Object o) {
		int index = indexOf(o);
		if(index >= 0){
			remove(index);
			return true;
		}else{
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public E set(int index, E element) {
		checkIndex(index);
		E e = (E)elementData[index];
		elementData[index] = element;
		return e;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public Object[] toArray() {
		return Arrays.copyOf(elementData, size);
	}
	
	/**
	 * 将ArrayList中全部元素存入一个运行时确定类型的数组中
	 * @param t
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> T[] toArray(T[] t){
		if(t.length < size){
			t = (T[]) Arrays.copyOf(elementData, size, t.getClass());
		}else{
			System.arraycopy(elementData, 0, t, 0, size);
		}
		return t;
	}

	@Override
	public Iterator<E> iterator() {
		return new Iter();
	}
	
	private final class Iter implements Iterator<E>{
		
		int pos = 0;
		int lastRet = -1;

		@Override
		public boolean hasNext() {
			return pos < size;
		}

		@Override
		public E next() {
			if(!hasNext()){
				throw new NoSuchElementException();
			}
			lastRet = pos++;
			return get(lastRet);
		}

		@Override
		public void remove() {
			if(lastRet < 0){
				throw new IllegalStateException();
			}
			ArrayList.this.remove(lastRet);
		}
	}
}
