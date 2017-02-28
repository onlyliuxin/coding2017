import java.util.Objects;

public class ArrayList implements List {
	
	private int size = 0;
	
	private Object[] elementData = new Object[100];
	
	public void add(Object o){
		elementData[size++] = o;
	}
	public void add(int index, Object o){
		for (int i = size; i >index; i++) {
			elementData[i] = elementData[i - 1];
		}
		elementData[index] = o;
	}
	
	public Object get(int index){
		return elementData[index];
	}

	public Object removeLast() {
        return elementData[size--];
    }
	
	public Object remove(int index){
		Object temp = elementData[index];
		for (int i = index; i < size - 1; i++) {
			elementData[i] = elementData[i + 1];
		}
		size -= 1;
		return temp;
	}
	
	public int size(){
		return size;
	}
	
	public Iterator iterator(){
		return new myIterator(this);
	}
	
}
