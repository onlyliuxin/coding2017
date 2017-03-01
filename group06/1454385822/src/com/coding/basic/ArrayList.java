package com.coding.basic;

import java.util.Arrays;

public class ArrayList implements List {
	
	private int size = 0;
	
	private int pos = 0; // 当前数组的末尾元素的下一位置
	
	private Object[] elementData = new Object[3];
	
	public void add(Object o){
	
		if(pos >= elementData.length - 1){
			//数组扩容1/3
			elementData = Arrays.copyOf(elementData, elementData.length + elementData.length/3);
		}
		elementData[pos++] = o;
		
	}
	public void add(int index, Object o){

		if(pos >= elementData.length - 1){
			//数组扩容1/3
			elementData = Arrays.copyOf(elementData, elementData.length + elementData.length/3);
		}
		/*
		 * 情况1.index < pos && pos < elementData.length - 1 
		 * index 只能在pos前面
		 */
		 if(index <= pos && pos <= elementData.length - 1){
			 Object[] tem = new Object[pos - index];
			 System.arraycopy(elementData, index, tem, 0, tem.length);
			 elementData[index] = o;
			 System.arraycopy(tem, 0, elementData, index + 1, tem.length);
			 pos++;
		 }else{
			 throw new IndexOutOfBoundsException();
		 }
		
		

	}
	
	public Object get(int index){
		if(index < pos){
			return elementData[index];
		}
		throw new IndexOutOfBoundsException();
	}
	
	public Object remove(int index){
		Object result;
		//将数字删除并将该数字后面的元素向前移动一位
		if(index < pos ){ //只有index在pos之前才进行删除操作
			result = elementData[index];
			if(pos - index > 1){ //删除的不是最后一个元素
				Object[] tem = new Object[pos - index - 1];
				System.arraycopy(elementData, index + 1, tem, 0, tem.length);
				for(int i=0; i<tem.length; i++){
					elementData[index + i] = tem[i];
				}
			}
			elementData[--pos] = null;
			return result;
		}
		throw new IndexOutOfBoundsException();
	}
	
	public int size(){
		return pos;
	}
	
	public Iterator iterator(){
		return new Iterator(){
			private int cur = 0;
			@Override
			public boolean hasNext() {
				if(cur < pos){
					return true;
				}
				return false;
			}

			@Override
			public Object next() {
				return elementData[cur++];
			}
			
		};
	}
	public static void main(String[]args){
		ArrayList list = new ArrayList();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(0, 0);
//		list.add(5, 0);
//		list.add(5, 0);
//		list.add(5, 0);
//		list.add(5, 0);
		Iterator it = list.iterator();
//		while(it.hasNext()){
//			System.out.print(it.next()+" ");
//		}
		System.out.println();
//		System.out.println(list.get(0));
		System.out.println(list.remove(0));
		while(it.hasNext()){
			System.out.print(it.next()+" ");
		}
		System.out.println();
		System.out.println(list.size());
		
	}
	
}





