package com.rd.p2p.common.util.liuxin;

public class Queue<T> {
	
	ArrayList<T> arrayList = new ArrayList<T>();
	
	public void enQueue(T o){	
		arrayList.add(o);
	}
	
	public T deQueue(){
		if(arrayList.size() > 0){
			@SuppressWarnings("unchecked")
			T t = (T) arrayList.get(0);
			arrayList.remove(0);
			return t;
		}else{
			return null;
		}
	}
	
	public boolean isEmpty(){
		return arrayList.size() == 0 ? true : false;
	}
	
	public int size(){
		return arrayList.size();
	}
}

