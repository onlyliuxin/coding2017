package week1_0226;
import java.util.Arrays;
public class ArrayList implements List {
	
	private int size = 0;
	
	private Object[] elementData = new Object[100];
	
	public void add(Object o){
		ensureCapacity(size + 1);
		elementData[size++] = o;
	}
	private void ensureCapacity(int size){
		if (size > elementData.length){
			grow();
		}
	}
	private void grow(){
		elementData = Arrays.copyOf(elementData, size * 2);
	}
	public void add(int index, Object o){
		rangeCheckForAdd(index);
		ensureCapacity(size + 1);
		System.arraycopy(elementData,index, elementData, index + 1, size - index);
		elementData[index] = o;
		size ++;
	}
	private void rangeCheckForAdd(int index){
		//index = size时不需要报错？
		if (index > size || index < 0){
			throw new IndexOutOfBoundsException();
		}
	}
	public Object get(int index){
		rangeCheck(index);
		return elementData[index];
	}
	private void rangeCheck(int index){
		if (index >= size || index < 0){
			throw new IndexOutOfBoundsException();
		}
	}
	
	public Object remove(int index){
		rangeCheck(index);
		Object dest = elementData[index];
		System.arraycopy(elementData, index +1, elementData, index, size-index-1);
		size --;
		return dest;
	}
	
	public int size(){
		return size;
	}
	
	public Iterator iterator(){
		return new Iterator(){
			private int index = 0;
			public Object next(){
				return elementData[index++];
			}
			public boolean hasNext(){
				return index >= size;
			}
		};
	}
	
}
