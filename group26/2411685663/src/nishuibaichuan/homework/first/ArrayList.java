package JEE_2411685663;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList implements List {

	private static int size = 0; //全局静态变量

	private Object[] elementData = new Object[100];

	public void add(Object o) {
		isCapacity(size + 1);//判断是否扩容
		elementData[size++] = o;
	}
	
	public void add(int index, Object o) {
		checkRangeForAdd(index);//判断下标是否越界
		isCapacity(size + 1);
		//使用arraycopy将index下标以后元素后移动 (size - index)长度
		System.arraycopy(elementData, index, elementData, index + 1, size - index);
		elementData[index] = o;
		size++;
	}

	public Object get(int index) {
		checkRangeForAdd(index);
		return (Object)elementData[index];
	}

	public Object remove(int index) {
		checkRangeForAdd(index);
		Object oldValue = get(index);
		int numMoved = size - index - 1;  
	    if (numMoved > 0)  
	        System.arraycopy(elementData, index+1, elementData, index,  
	                 numMoved);  
	    elementData[--size] = null; // index后面数据依次往前移动，将最后一个位置赋值为0，让gc来回收空间。  
	    return oldValue;  
	}

	public int size() {
		return size;
	}

	/**
	 * 迭代器
	 * @return
	 */
	public Iterator iterator() {
		return new SubIterator(this);
	}
	
	private static class SubIterator implements Iterator{
		
		private ArrayList arrayList;
		private static int ret = 0;
		
		private SubIterator(){}
		
		private SubIterator(ArrayList arrayList){
			this.arrayList = arrayList ;
		}
		
		public boolean hasNext() {
			return ret != size;
		}

		public Object next() {
			if (ret > size) {
				throw new NoSuchElementException();//没有这样的元素异常
			}
			return arrayList.elementData[ret++];
		}

		public void remove() {
			arrayList.remove(ret--);
		}
		
	}
	
	/**
	 * 下标是否越界
	 * @param index
	 */
	public void checkRangeForAdd(int index){
		if (index < 0 || index > size) {
			throw new ArrayIndexOutOfBoundsException("index下标越界" + ", Size: " + size
				+ ",Index: " + index);
		}
	}
	
	/**
	 * 是否扩容
	 */
	public void isCapacity(int size){
		if (size > 100) {
			Capacity(size);
		}
		/**
		 * size小于0主要是因为当size超过Integer.MAX_VALUE就会变成负数。
		 */
		if (size < 0) {
			throw new ArrayIndexOutOfBoundsException("数组长度溢出");
		}
	}
	
	public void Capacity(int capacity){
		int newLength = elementData.length + 1000;//再原来基础上扩容1000
		if (newLength - capacity < 0) {
			newLength = capacity; //得到最大长度
		}
		/**
		 * 保留了数组的头部信息在数组中，因此实际存放数据的大小就是整数的最大值 - 8
		 * 很难执行下面的判断
		 */
		if (newLength > Integer.MAX_VALUE - 8) {
			newLength = capacity > Integer.MAX_VALUE - 8 ? Integer.MAX_VALUE : Integer.MAX_VALUE - 8;
		}
		elementData = Arrays.copyOf(elementData, newLength);//获取扩容后的数组
	}
	
}
