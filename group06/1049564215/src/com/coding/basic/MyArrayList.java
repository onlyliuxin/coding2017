import java.util.*;

/**
 * @author CCD
 *
 */
public class MyArrayList {
	
	private Object[] elementData = new Object[100];
	private int size  = 100 ;
	
	public void add(Object o){
		elementData[size++] = o;
	}
	public void add(int index, Object o){
		if(index > size || index < 0)
			throw new IndexOutOfBoundsException("index "+ index +"is biger than size" + size+
		    "index is less than 0");
		System.arraycopy(elementData, index, elementData, index+1, size-index);
		elementData[index] = o;
		size++;
	}
	
	public Object get(int index){
		if(index > size || index < 0)
			throw new IndexOutOfBoundsException("index "+ index +"is biger than size" + size+
		    "index is less than 0");
		return elementData[index];
	}
	
	public Object remove(int index){
		if(index > size || index < 0)
			throw new IndexOutOfBoundsException("index "+ index +"is biger than size" + size+
		    "index is less than 0");
		Object E  = elementData[index];
		System.arraycopy(elementData, index+1, elementData, index,
				size - index - 1);
		elementData[--size] = null;
        return E;
	}
	
	public int size(){
		return size;
	}
	
	public Iterator iterator(){
		return null;
	}
}
