package ListServiceImpl;

import java.util.Arrays;

import ListService.KIStackList;

public class KStackList<T> implements KIStackList<T>{
	Object[] data;
	private int capacity;
	private int size;

	public KStackList()  
    {  
        capacity=16;  
        size=0;  
        data=new Object[capacity];  
    }

	public void ensureCapacity() {
		capacity = capacity * 2;
		data = Arrays.copyOf(data, capacity);
	}

	//压入栈底
	public void push(T ele) {
		if (size < capacity) {
			data[size++] = ele;
		} else {
			ensureCapacity();
			data[size++] = ele;
		}
	}

	//弹出
	public void pop() {
		if (size > 0) {
			System.out.println(data[size - 1]);
			data[--size] = null;
		} else {
			System.out.println("Empty stack!");
		}
	}

	boolean isEmpty() {
		return size == 0;
	}

}
