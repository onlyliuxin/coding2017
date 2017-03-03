package hw1;

//import java.util.ArrayList;
import java.util.Iterator;
//import java.util.List;

import com.sun.xml.internal.ws.api.pipe.ThrowableContainerPropertySet;

public class ArrayListImpl implements List {

private int size = 0;
	
	private Object[] elementData = new Object[100];
	
	public void add(Object o){
		//size++;
		try{
			if (size < elementData.length) {
				elementData[size] = o;
			} else {
				elementData = grow(elementData, elementData.length);
				elementData[size] = o;
			}
			size++;
		} catch (IndexOutOfBoundsException e) {
			System.out.println("The added index is out of bound");
		}
	}
	public void add(int index, Object o){
		if (index < 0) {
			throw new IndexOutOfBoundsException("Index cannot be less than 0");
		}
		while (index >= elementData.length) {
			elementData = grow(elementData, elementData.length);
		}
		if (index >= size) {
			elementData[index] = o;
			size++;
		} else {
			if (size + 1 >= elementData.length) {
				elementData = grow(elementData, elementData.length);
			}
				for (int i = size-1; i >= index;i--) {
					elementData[i+1] = elementData[i];
				}
				elementData[index] = o;
				size++;
			
		}
	}
	
	public Object get(int index){
		if (index >= size ||index < 0) {
			throw new IndexOutOfBoundsException("Index entered is out of bounds");
		} else {
			return elementData[index];
		}
		
	}
	
	public Object remove(int index){
		if (index >= size ||index < 0) {
			throw new IndexOutOfBoundsException("Index entered is out of bounds");
		} else {
			Object result = elementData[index];
			for (int i = index; i < size - 1; i++) {
				elementData[i] = elementData[i+1];
			}
			elementData[size - 1] = 0;
			size--;
			return result;
		}
	}
	
	public int size(){
		return size;
	}
	
	public Iterator iterator(){
		return null;
	}
	
	private Object[] grow (Object[] src, int increase) {
		Object[] target = new Object[src.length + increase];
		System.arraycopy(src, 0, target, 0, src.length);
		return target;
	}
}
