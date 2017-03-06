package com.louisly.java;
import com.louisly.java.LYArrayList;

public class LYStack {
	private LYArrayList list = new LYArrayList();
//	public LYStack() {
//		list = new LYArrayList();
//	}
	
	public void push(Object obj) {
		list.addObject(obj);
	}
	
	public Object pop() {
		if (list.size() == 0) return null;
		Object obj = list.get(list.size()-1);
		list.removeObject(obj);
		return obj;
	}
	
	public Object peak() {
		if (list.size() == 0) return null;
		Object obj = list.get(list.size()-1);
		return obj;
	}
	
	public boolean isEmpty() {
		return list.size() == 0;
	}
	
	public int size() {
		return list.size();
	}
}
