package com.louisly.java;
import com.louisly.java.LYArrayList;

public class LYQueue {
	LYArrayList list = null;
	public LYQueue() {
		list = new LYArrayList();
	}
	
	public void enQueue(Object obj) {
		list.addObject(obj);
	}
	
	public Object deQueue() {
		if (list.size() != 0) {
			return list.get(0);
		}
		return null;
	}
	
	public boolean isEmpty() {
		return list.size() == 0;
	}
	
	public int size() {
		return list.size();
	}
}
