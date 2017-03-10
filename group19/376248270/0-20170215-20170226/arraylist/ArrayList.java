package arraylist;
import java.util.Arrays;

import base.List;

public class ArrayList implements List {
    public static void main(String[] args) {
        ArrayList arr = new ArrayList();
        System.out.println(arr.size());
        arr.add("ele1");
        arr.add("ele2");
        
        for (int i = 0; i < arr.size(); i++) {
        	System.out.println((String)arr.get(i));
        }
        
        System.out.println("============");
        arr.remove(0);
        
        for (int i = 0; i < arr.size(); i++) {
        	System.out.println((String)arr.get(i));
        }
    }

    // 初始容量
    static final int DEFAULT_INITIAL_CAPACITY = 10;

    // 数组扩展速度
    static final int INCRE_SPEED = 2;

    // 元素数组
    private Object[] elementData = new Object[DEFAULT_INITIAL_CAPACITY];

    // 元素数量
    private int size = 0;
    
    /**
     * 添加元素到指定位置
     */
    public void add(int index, Object obj) {
    	add(elementData[size-1]);
    	for (int i = size - 1; i > index; i--) {
    		elementData[i] = elementData[i - 1];
    	}
    	elementData[index] = obj;
    }
    
    /**
     * 删除指定位置的元素
     */
    public Object remove(int index) {
    	Object result = elementData[index];
    	
    	while (index < size) {
    		elementData[index] = elementData[index + 1];
    		index++;
    	}
    	size--;
    	return result;
    }
    
    /**
     * 获取指定位置的元素
     */
    public Object get(int index) {
    	if (index < 0 || index >= size) {
    		throw new IndexOutOfBoundsException("Index: "+index);
    	}
    	return elementData[index];
    }

    /**
     * 获取当前元素数量
     */
    public int size() {
        return size;
    }

    /**
     * 添加元素到尾部
     */
    public void add(Object obj) {
        elementData[size++] = obj;
        if (size == elementData.length) {
            resize();
        }

    }

    /**
     * 元素数组自动扩展
     */
    private void resize() {
        elementData = Arrays.copyOf(elementData, elementData.length * INCRE_SPEED);
    }
    
}
