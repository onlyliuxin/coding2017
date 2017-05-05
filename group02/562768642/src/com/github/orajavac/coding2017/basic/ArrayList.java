package com.github.orajavac.coding2017.basic;

public class ArrayList implements List,Iterator{
	
	private int size = 0;
	
	private int i=0;
	
	private Object[] elementData = new Object[2];
	
	//SS
	public void add(Object o){
		size=size();
		if (size == elementData.length)
			grow(elementData,size);
		elementData[size]=o;
	}
	public void add(int index, Object o){
		Object old=elementData[index];	//[3] old value=c
		size=size();
		size+=1;
		if (size>elementData.length)
			grow(elementData,size);
		Object temp = null;
		int len=elementData.length;
		for (int i=0;i<len;i++){
			if (i==index){
				elementData[index]=o;
			}else if(i>index){
				temp=elementData[i];
				elementData[i]=old;	//[4]=c
				old=temp;
			}
		}
	}
	
	public Object get(int index){
		return elementData[index];
	}
	
	public Object remove(int index){
		if (index==elementData.length-1){	//删除最后一个索引元素里的值
			elementData[index]=null;
		}else{
			elementData[index]=null;
			int len=elementData.length;
			for (int i=0;i<len;i++){
				if (i==index){
					elementData[i]=elementData[i+1];
				}else if(i>index){
					if(i+1!=len){
						elementData[i]=elementData[i+1];
					}else{	//我们假设数组索引 0-3，那么数组长度是4，3+1==4，elementData[i+1]会报错
						elementData[i]=null;
					}
				}
			}
		}
		return null;
	}
	
	public int size(){
		size=0;
		for (int i=0;i<elementData.length;i++){
			if (elementData[i]!=null){
				size++;
			}
		}
		return size;
	}
	
	public void grow(Object[] elementData,int size){
		Object[] target = new Object[size+elementData.length];
		System.arraycopy(elementData, 0, target, 0, elementData.length);
		this.elementData=target;
	}
	
	public Iterator iterator(){
		ArrayList l = new ArrayList();
		l.elementData=this.elementData;
		l.size=this.size();
		return l;
	}
	
	
	
	public boolean hasNext(){
		if (i<=size-1){
			return true;
		}else{
			i=0;
		}
		return false;
	}
	
	public Object next(){
		Object obj=elementData[i];
		i++;
		return obj;
	}
}
