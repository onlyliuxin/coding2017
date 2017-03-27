package week01;

import java.util.Arrays;



public class ArrayList implements List {
	/**
	 * The current array's size.
	 */
	private int size = 0;                          
	private static final int DEFAULT_CAPACITY = 10;
	private Object[] elementData; 
	/**
	 * Return an empty Array instance if no initial elements has specified.
	 */
	private static final Object[] EMPTY_ELEMENTDATA ={}; 
	/**
	 * Construct an ArrayList with default capacity.
	 */
	public ArrayList(){
		this(DEFAULT_CAPACITY);
	}
	/**
	 * Construct an ArrayList instance with specified capacity.
	 * @param initialSize   the specified capacity
	 */
	public ArrayList(int initialSize){
		if(initialSize>0){         //Does it right >0 but > DEFAULTCAPACITY?
			this.elementData = new Object[initialSize];
		}else if(initialSize==0){
			this.elementData = EMPTY_ELEMENTDATA;
		}else{
			throw new IllegalArgumentException("IllegalCapacity:" + initialSize);
		}
	}
	/**
	 * Increase the capacity
	 */
	public void grow(int capacity){
		 elementData = Arrays.copyOf(elementData, capacity);
	}
	/**
	 * Add an element to ArrayList's tail
	 */
	public boolean add(Object o){
		if(this.size == elementData.length){
			grow(size+10);
		}
		elementData[size++] = o;
		return true;
	}
	/**
	 * Add an element to an Array with specified location
	 */
	public boolean add(int index, Object o){
		if(index<0 || index>this.size){
			throw new IllegalArgumentException("IllegalIndex");
		}else if(this.size+1>elementData.length){
			grow(size+10);
		}else{
			for(int i=this.size+1;i>index;i--){
				elementData[i] = elementData[i-1];
			}
			elementData[index] = o;
			size++;
		}
		return true;
	}
	/**
	 * Return an element that specified
	 */
	public Object get(int index){
		if(index<0 || index>this.size){
			throw new IllegalArgumentException("IllegalIndex");
		}
		return elementData[index];
	}
	
	public boolean remove(int index){
		if(index<0 || index>this.size){
			throw new IllegalArgumentException("IllegalIndex");
		}else{
			for(int i=index;i<=this.size;i++){
				elementData[i] = elementData[i+1];
			}
			size--;
		}
		return true;
	}
	
	public int size(){
		return size;
	}
	
	public Iterator iterator(){
		return null;
	}
	
}

