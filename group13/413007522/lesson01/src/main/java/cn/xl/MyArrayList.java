package cn.xl;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;


/** 
 * 1. ArrayList ��һ��������У��൱�ڶ�̬���顣��Java����Ա������ܶ�̬���ӣ�
 * 2. ӵ����ӡ�ɾ�����޸ġ������ȹ��ܡ�
 * @author XIAOLONG
 *
 * @param <E>
 */

public class MyArrayList implements MyList {


	private int size = 0;  

	private final int initialCapacity = 3; 
	
	private Object[] elementData = new Object[100]; 

	private static final Object[] EMPTY_ELEMENTDATA = {};

	private int modCount = 0;

	/**
	 * ����һ��Ĭ�ϳ�ʼ����Ϊ3�Ŀ��б�
	 * 
	 */
	public MyArrayList() {  
		elementData  =  new Object[initialCapacity];  
	}  

	/**
	 * ����һ��ָ����ʼ�����Ŀ��б�
	 * @param initialCapacity
	 */
	public MyArrayList(int initialCapacity) {  

		if (initialCapacity < 0){
			throw new IllegalArgumentException("Illegal initialCapacity: "+ initialCapacity); 
		}else if(initialCapacity == 0){
			elementData  =  EMPTY_ELEMENTDATA;
		}else{
			elementData  =  new Object[this.initialCapacity];  
		}
	}  

	/**
	 * 
	 * ����һ������ָ��collection��Ԫ�ص��б���ЩԪ�ذ��ո�collection�ĵ������������ǵ�˳������
	 * ��MySubClass��MyClass�����ࡣ
	 * Collection<MyClass> myCollection;  
	 * Collection<MySubClass> mySubCollection;  
	 * ArrayList<MyClass> myList = new ArrayList<MyClass>(myCollection);
	 * Ҳ���ԣ�ArrayList<MyClass> myList = new ArrayList<MyClass>(mySubCollection);
	 * ����MyClass����MyClass�������Collection�����Թ����ArrayList<MyClass>
	 * @param c 
	 */
	public MyArrayList(Collection<? extends Object> c) {  
		elementData =  c.toArray();  
		if((size = elementData.length) != 0){
			//c.toArray might (incorrectly) not return Object[] (see 6260652)  
			//�ٷ�bug������ת�Ͳ���ȫ
			//����������Object[]������Arrays.copyOf��������תΪObject[]  
			if (elementData.getClass() != Object[].class)  
				elementData = Arrays.copyOf(elementData, size, Object[].class);  
		}else{
			elementData = EMPTY_ELEMENTDATA;
		}
	}  


	/**
	 * ��ָ����Ԫ��������б���ָ��λ���ϵ�Ԫ��
	 * @param index
	 * @param element
	 * @return  Object(��ǰλ�ڸ�λ���ϵľ�Ԫ��)
	 */
	public Object set(int index, Object element) {  
		if (index >= size())
			throw new RuntimeException("The Index: "+index+" is out of band.");

		Object oldValue =  elementData[index];  
		elementData[index] = element;  
		return oldValue;  
	}  

	/**
	 * ���Ԫ�����б�β��
	 * @param e
	 */                                             
	public void add(Object e) {  
		if (e == null) {  
			throw new RuntimeException("The value should not be null.");  
		}  
		if (size() >= initialCapacity) {  
			ensureCapacity(size() + 1);  
		}  
		elementData [size] = e;  
		size++;  
	}  

	/**
	 * ��Ԫ����ӵ��б�ָ��λ��
	 * @param index
	 * @param element
	 */
	public void add(int index, Object o) {  
		if (index >= size || index < 0)  
			throw new RuntimeException("The Index: "+index+" is out of band.");  
		// ������鳤�Ȳ��㣬���������ݡ�  
		ensureCapacity(size+1);
		// �� elementData�д�Indexλ�ÿ�ʼ������Ϊsize-index��Ԫ�أ�  
		// ���������±�Ϊindex+1λ�ÿ�ʼ���µ�elementData�����С�  
		// ������ǰλ�ڸ�λ�õ�Ԫ���Լ����к���Ԫ������һ��λ�á�  
		System.arraycopy(elementData, index, elementData, index + 1, size - index);  
		elementData[index] = o;  
		size++;  
	}


	/**
	 * ���ش��б���ָ��λ���ϵ�Ԫ��
	 * @param index
	 * @return
	 */
	public Object  get(int index) {  
		if (index >= size) {  
			throw new RuntimeException("The index:" + index + " is out of band.");  
		}  
		return elementData [index];  
	}  

	/**
	 * ɾ��ָ��λ��Ԫ��
	 * @param  ɾ��Ԫ������λ�ã���0��ʼ
	 * @return Object(��ɾ��ָ��λ���ϵľ�Ԫ��)
	 */
	public Object remove(int index) {  
		if (index >= size) {  
			throw new RuntimeException("The index:" + index + " is out of band.");  
		} 
		modCount++;
		Object oldElement = elementData[index];
		//�˴���ȻҲ����System.arraycopy ʵ��
		for (int i = index; i < size - 1; i++) {  
			elementData [i] = elementData [i + 1];  
		}  
		elementData [size - 1] = null;  
		size--;  
		return oldElement;
	}  

	/**
	 * �������ݣ�ÿ�������ռ�Ϊ  50%+1
	 * @param ��ǰ����������С����ֵ
	 */
	private void ensureCapacity(int minCapacity) {  
		modCount++;
		int oldCapacity = elementData.length;  
		if (minCapacity > oldCapacity) {  
			//λ�����㣬�൱�ڳ���2��������Щ�� int newCapacity = (oldCapacity * 3)/2 + 1;
			int newCapacity = oldCapacity + (oldCapacity >> 1);
			if (newCapacity < minCapacity)  
				newCapacity = minCapacity;  
			elementData = Arrays.copyOf(elementData, newCapacity);  
		}  
	}  
	/**
	 * ��List�е�Ԫ�ظ���.
	 * @return ��List�е�Ԫ�ظ���
	 */
	public int size() {
		return size;
	}

	/**
	 * �����ListԪ�����е�һ��������
	 * @return ��ListԪ�����еĵ�����
	 */
	public Iterator<Object> iterator() {
		return new Itr();
	}

	private class Itr implements Iterator<Object> {
		int cursor;       // ��һ��Ԫ�ص�����λ��
		int lastRet = -1; // ��һ��Ԫ�ص�����λ��
		int expectedModCount = modCount;

		public boolean hasNext() {
			return cursor != size;
		}

		public Object next() {

			if (modCount != expectedModCount){
				throw new RuntimeException("This list is being modified.");

			}

			int i = cursor;
			if (i >= size){
				throw new RuntimeException("No such element.");
			}
			Object[] elementData = MyArrayList.this.elementData;
			if (i >= elementData.length){
				throw new RuntimeException("This list is being modified.");
			}

			cursor = i + 1;
			return  elementData[lastRet = i];
		}

		public void remove() {
			if (lastRet < 0)
				throw new IllegalStateException();

			if (modCount != expectedModCount){
				throw new RuntimeException("This list is being modified.");

			}

			try {
				MyArrayList.this.remove(lastRet);
				cursor = lastRet;
				lastRet = -1;
				expectedModCount = modCount;
			} catch (IndexOutOfBoundsException ex) {
				throw new RuntimeException("This list is being modified.");
			}
		}
	}

}
