package com.coding.basic;

public class Queue {
	private int size = 0;
	private Object[] datas;
	private int capacity = 10;
	public void enQueue(Object o){		
		if(datas==null){
			datas = new Object[capacity];
		}
		if(this.size>=datas.length){
			Object[] newDatas = new Object[capacity+10];
			System.arraycopy(datas,0,newDatas,0,datas.length);
			datas = newDatas;
		}
		datas[size] = o;
		size++;
	}
	
	public Object deQueue(){
		Object o = null;
		if(datas!=null && datas.length>0){
			o = datas[0];
		}else{
			System.out.println("╤сапн╙©у");
		}
		for(int i=0;i<datas.length-1;i++){
			datas[i] = datas[i+1];
		}
		datas[datas.length-1] = null;
		size--;
		return o;
	}
	
	public boolean isEmpty(){
		if(datas!=null && datas.length>0){
			return false;
		}else{
			return true;
		}
	}
	
	public int size(){
		return this.size;
	}
}
