package com.coding.basic;
import java.util.Arrays;

public class ArrayList implements List {
	
	//数组初始容量//
	private final int DEFAULT_CAPICITY=7;
	
	//数组元素个数//
	private int size = 0;
	
	private Object[] elementData = new Object[DEFAULT_CAPICITY];
	
	public void add(Object o){
		ensureCapcity(size+1);
		elementData[size++]=o;
	}
	public void add(int index, Object o){
		//index要连续的增加//
		checkIndex(index);
		ensureCapcity(size+1);
		/* index及后面的元素左移一位,即从index开始移动，注意index从0开始，
		 * 即还要+1，则长度为size-((index)+1)+1
		 */
		System.arraycopy(elementData, index, elementData, index+1, size-index);
		elementData[index]=o;
		size++;
	}
	
	public Object get(int index){
		checkIndex(index);
		return elementData[index];
	}
	
	public Object remove(int index){
		checkIndex(index);
		Object temp=elementData[index];
		/* index后面的元素左移一位,即从index+1开始移动，注意index从0开始，
		 * 即还要+1，则长度为size-((index+1)+1)+1
		 */
		System.arraycopy(elementData, index+1, elementData, index, size-index-1);
		size--;
		return temp;
	}
	
	public int size(){
		return size;
	}
	
	public Iterator iterator(){
		return new Iterator();
	}
	
	private class Iterator{
		private int index=0;
		public boolean hasNext(){
			return index<size;
		}
		public Object next(){
			checkIndex(index);
			return elementData[index++];
		}
	}
	
	//判断请求的下标是否越界并提示//
	private void checkIndex(int index){
		if (index<0 || index >=size){
			throw new IndexOutOfBoundsException("get " + index+" in "+size);
		}		
	}
	
	//判断是否需要扩容并完成扩容//
	private void ensureCapcity(int size){
		int oldLength=elementData.length;
		if (size>=oldLength){
			//util.ArrayList中的公式，源代码使用的右移1，即除2//
			int newLength=oldLength/2+oldLength;
			if (newLength<size){
				newLength=size;
			}
			//Arrays.copyOf返回新的指定长度的数组//
			elementData=Arrays.copyOf(elementData, newLength);
		}
	}

	@Override
	public String toString(){
		//方便检查重写了toString//
		StringBuilder sb = new StringBuilder("[");
		for (int i=0 ;i<size-1;i++){
			sb.append(elementData[i]+",");
		}
		sb.append(elementData[size-1]);
		sb.append("]");
		return sb.toString();
	}
	
}