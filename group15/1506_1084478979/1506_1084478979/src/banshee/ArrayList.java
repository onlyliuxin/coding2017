package banshee;

import java.util.Arrays;
public class ArrayList  {
	
	private int size = 0;
	
	private Object[] elementData = new Object[100];
	
	public void add(Object o){
		ensureCapacity(size + 1);
		elementData[size++] = o;
	}
	public void add(int index, Object o){
		rangeCheck(index);
        ensureCapacity(size+1);  
        System.arraycopy(elementData, index, elementData, index + 1,
                 size - index);
        elementData[index] = o;
        size++;
	}
	
	public Object get(int index){
		rangeCheck(index);
		return elementData[index];
	}
	
	public Object remove(int index){
		rangeCheck(index);
		Object  oldValue = elementData[index];
        int numMoved = size - index - 1;
        if (numMoved > 0)
            System.arraycopy(elementData, index + 1, elementData, index,
                    numMoved);
        elementData[--size] = null; 
        return oldValue;
	}
	
	public int size(){
		return -1;
	}
	
//	public Iterator iterator(){
//		//TODO
//		//���ᡣ����
//		return null;
//	}
	

	private void rangeCheck( int index) {  
        if (index >= size || index < 0){
            throw new IndexOutOfBoundsException("ָ����index��������");
        }  
    }  
	

	public void ensureCapacity(int minCapacity) {
		int oldCapacity = elementData.length;
		if (minCapacity > oldCapacity) {
            //�����µ�������С��Ϊ��ǰ������1.5��
            int newCapacity = (oldCapacity * 3) / 2 + 1;
            if (newCapacity < minCapacity)
                newCapacity = minCapacity;
            elementData = Arrays.copyOf(elementData, newCapacity);
        }
    }
	
}


