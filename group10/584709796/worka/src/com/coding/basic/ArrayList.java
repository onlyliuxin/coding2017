//刚开始学JAVA，现在学到JAVA基础类库耐力，迭代器还没学

package com.coding.basic;

public class ArrayList implements List {
	
	private int size =0;
	
	private Object[] elementData = new Object[100];
		
	public int getSize() {//得到数组大小
		return size;
	}
	public void setSize(int size) {//设置数组的长度
		this.size = size;
	}
	
	public void ExtendArray(int size){ //插入元素时，数组长度加1，确保数组不越界
		this.size=size+1;
	}
		
	public void add(Object o){//在末尾添加元素
		int length=getSize();
		elementData[length] = o;	
		ExtendArray(length);
	}
	
	public void add(int index, Object o){
		int length=getSize();
		
		for(int k=length-1;k>=index-1;k--){//元素后移
			elementData[k+1]=elementData[k];
		}
		elementData[index-1]=o;
		ExtendArray(length);//插一个元素，扩充一次
	}
	
	public Object get(int index){//获取元素
		int length=getSize();
		if(index+1>length||index<0){
			System.out.println("方法 get(int index)的index不在数组索引的范围内");
		}
				return (Object)elementData[index];
	}
	
	public Object remove(int index){//移除元素
		int length=getSize();
		if(index+1>length||index<0){
			System.out.println("方法 remove(int index)的index不在数组索引的范围内");
		}
		Object ss=(Object)elementData[index];
		
		for(int k=index;k<length;k++){
			elementData[k]=elementData[k+1];//元素前移
		}
		
		length--;		
		return ss;
	}
	
	public int size(){
		return size;
	}
	
	public void printArray(){
		for(int i = 0; i < getSize()-1; i++){ 
				System.out.print(elementData[i]+"	");  
				}  
	}
	
	public Iterator iterator(){
		return null;
	}
	
}
