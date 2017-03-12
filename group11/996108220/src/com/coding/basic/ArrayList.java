package com.coding.basic;

public class ArrayList implements List {
	
	private int size = 0;
	private Object[] elementData = new Object[100];
	/**
	 * 在队尾添加元素
	 */
	public void add(Object o){
		if(size+1>elementData.length)this.grow(elementData);
		else elementData[size++]=o;
	}
	/**
	 * 在index处添加元素，index+1到size-1元素向后移动
	 */
	public void add(int index, Object o){
		if(index<0||index>size){
			System.out.println("数组越界"+index);
			return;
		}
		if(size+1>elementData.length)this.grow(elementData);
		else {
			for(int i=size;i>=index+1;)
			{
				elementData[i]=elementData[--i];
			}
			size++;
			elementData[index]=o;
		}
		
	}
	/**
	 * 获得index处的元素elementData[index]
	 */
	public Object get(int index){
		//TODO越界抛出异常
		if(index<0||index>size){
			System.out.println("数组越界"+index);
			return null;
		}
		else{
			return elementData[index];
		}
	}
	/**
	 * 移除index处的元素，将index+1到size-1的元素向前移动
	 */
	public Object remove(int index){
		//TODO越界抛出异常
		if(index<0||index>=size){
			System.out.println("数组越界"+index);
			return null;
			}
		else{
			Object value=elementData[index];
			for(int i=index;i<size;)
			{
				elementData[i]=elementData[++i];
			}
			size--;
			return value;
		}
	}
	
	public int size(){
		return size;
	}
	
	public Iterator iterator(){
		return new Itr();
	}

	 private class Itr implements Iterator {
	        int cursor;       // index of next element to return
	        Itr() {
	            cursor=0;
	        }
	        public boolean hasNext() {
	            return cursor != size;
	        }

	        
	        public Object next() {
	            
	            int i = cursor;
	            if (i >= size)
	                System.out.println("超过size");;
	            Object[] elementData = ArrayList.this.elementData;
	            if (i >= elementData.length)
	                System.out.println("超过length");
	            cursor = i + 1;
	            return  elementData[i];
	        }

	 }
	public void grow(Object[] elementData2){
		int[] elementData=new int[elementData2.length+elementData2.length/2];
		System.arraycopy(elementData2,0,elementData,0,elementData2.length); 
	}
	/**
	 * 测试方法
	 * @param args
	 */
	public static void main(String args[]){
		ArrayList list=new ArrayList();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(6);
		//list.add(2,0);
		//list.remove(list.size-1);
		System.out.println(list.size());
		Iterator itr=list.iterator();
		while (itr.hasNext()) {
			System.out.println(itr.next());
			
		}
	}
	
}

