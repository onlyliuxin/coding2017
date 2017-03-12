package lesson01;

import java.util.Arrays;

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
		elementData = new Object[DEFAULT_CAPACITY];
		size = 0;
	}

	@Override
	public boolean contains(Object o) {
		for(int i = 0; i < size; i++){
			
		}
		return false;
	}

	@Override
	public int indexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public E get(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E remove(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public E set(int index, E element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

}
