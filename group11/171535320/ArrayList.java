import java.util.Arrays;
import java.util.Objects;

public class ArrayList implements List {
	
	private int size = 0;
	
	private Object[] elementData = new Object[100];

	private void ensureCapacity(int minCapacity) {
		if(minCapacity > elementData.length) {
			Object[] temp = elementData;
			int newCapacity = elementData.length * 3 / 2 + 1;
			Object[] newArray = new Object[newCapacity];
			System.arraycopy(temp, 0, newArray, 0, elementData.length);
			elementData = newArray;
		}
	}

	public void add(Object o){


		ensureCapacity(size + 1);
		elementData[size++] = o;
	}

	public void add(int index, Object o){
		ensureCapacity(size + 1);
		System.arraycopy(elementData, index, elementData, index + 1, size - index);
		elementData[index] = o;
		size++;
	}
	
	public Object get(int index){
		if(index >= size || index < 0) {
			return null;
		}
		return elementData[index];
	}
	
	public Object remove(int index){
		if(index < 0 || index >= size) {
			return null;
		}
		Object obj = elementData[index];

		System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);

		elementData[--size] = null;

		return obj;
	}
	
	public int size(){
		return size;
	}
	
	public Iterator iterator(){
		return null;
	}

}
