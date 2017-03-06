package list;

import java.util.Arrays;

public class ArrayList<E> {
	private transient static int INITIAL_SIZE = 10;
	private transient int arrayLength;
	private transient int size;
	private transient E[] array;
	public ArrayList(){
		array = (E[]) new Object[INITIAL_SIZE];
		arrayLength = INITIAL_SIZE;
	}
	public ArrayList(int size){
		if(size<=0){
			throw new IllegalArgumentException("参数不可以小于0");
		}
		array = (E[])new Object[size];
		arrayLength = array.length;
		ensureCapacity(size);
		this.size = size;
	}
	public int size(){
		return size;
	}
	public void add(E e){
		ensureCapacity(size+1);
		array[size] = e;
		size++;
	}
	public E get(int index){
		if(index<0 || index > size){
			throw new IllegalArgumentException("索引越界");
		}
		return array[index];
		
	}
	public E set(int index, E e){
		if(index<0 || index>size){
			throw new IllegalArgumentException("索引越界");
		}
		E result = array[index];
		array[index] = e;
		return result;
	}
	private void ensureCapacity(int size){
		E[] oldArray = array;
		int oldSize = arrayLength;
		while(size>arrayLength){
			arrayLength = arrayLength + (arrayLength >> 1);
			array = Arrays.copyOf(oldArray, arrayLength);
		}
	}
}
