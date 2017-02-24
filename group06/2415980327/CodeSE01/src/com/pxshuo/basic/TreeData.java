package com.pxshuo.basic;

public class TreeData implements Comparable<Object> {
	private int data;
	
	public TreeData(int data) {
		this.data = data;
	}
	
	@Override
	public String toString() {
		return data + "";
	}
	
	@Override
	public int compareTo(Object o) {
		return data - ((TreeData)o).data;
	}

}
