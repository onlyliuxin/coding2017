/*
 * 该程序为自己实现ArrayList
 * ArrayList底层为数组实现，是动态数组
 */
package com.firsthomework;


public class MyArrayList {
	private int size=0;
	private int initialcapcity=10; 
	private Object[] elements= null;
	public MyArrayList(){
	  this.elements=new Object[initialcapcity];
	}
	
	public MyArrayList(int capcity){

		this.elements = new Object[capcity];
		
	}
	public void enlarge(){
		initialcapcity*=2;
		Object[] tmpelements= new Object[initialcapcity];
		System.arraycopy(elements, 0, tmpelements, 0,size);
		elements=tmpelements;
	}
	public void add(Object o){
		if(o==null){
			throw new RuntimeException("不能为空：");
		}
		if(size>initialcapcity){
			enlarge();
		}
		elements[size]=o;
		size++;
		
	}
	public Object get(int index){
		if(index<0||index>size-1){
			throw new IndexOutOfBoundsException("数组越界：");
		}
		return elements[index];
		
	}
//实现remove()方法：删除位置，后面的元素要前移，返回删除位置的元素
	public Object remove(int index){
		Object oldValue=elements[index];
		int eleMoved=size-index-1; //移动的元素的个数
		if(eleMoved>0){
			System.arraycopy(elements, //原数组
					index+1, //原数组的起始位置（删除元素的后面一个位置）
					elements, //目的数组
					index, //目的数组的起始位置
					eleMoved);//拷贝的长度
		}
		elements[size-1]=null;
		size--;
		
		return oldValue;
	}
	
	public boolean set(int index,Object o){
		if(index<0||index>size-1){
			throw new IndexOutOfBoundsException("数组越界");
		}
		elements[index]=o;
		return true;
	}
	
	//重写toString()方法
	public String toString(){
		StringBuffer sb=new StringBuffer();
		sb.append("[");
		for(int i=0;i<size;i++){
			Object o=elements[i];
			sb.append(o);
			if(i<size-1){
				
				sb.append(",");
			}
			
		}
		sb.append("]");
		return sb.toString();
		
	}
	
	public static void main(String[] args) {
		MyArrayList my=new MyArrayList();
		my.add("hello");
		my.add("world");
		my.add("!");
		my.add(1);
		my.add(2);
		my.add(3);
		System.out.println(my.get(1));
		my.set(2, "oo");
		System.out.println(my.get(2));
		System.out.println("***");
		System.out.println(my.remove(1));
		System.out.println(my.remove(2));
		System.out.println(my);
	}
	
	
	
}
