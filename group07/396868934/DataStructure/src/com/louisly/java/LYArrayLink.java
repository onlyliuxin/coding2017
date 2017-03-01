package com.louisly.java;

public class LYArrayLink {

	private int currentCount = 0;
	private LYNode header = null;
	private LYNode lastNode = null;
	public void addObject(Object obj) {
		if (obj == null) return;
		
		currentCount++;
		LYNode node = new LYNode();
		node.data = obj;
		
		if (lastNode != null) {
			lastNode.next = node;
			lastNode = node;
		} else {
			lastNode = node;
		}
		
		if (header == null) {
			header = node;
		}
	}
	
	public void removeObject(Object obj) {
		LYNode lastNode = null;
		LYNode node = header; 
		Object data = null;
		while (node != null) {
			data = node.data;
			if (data == obj) {
				if (lastNode != null) {
					lastNode.next = node.next;
				} else {
					// 移除第一个元素
					header = node.next;
				}
				
				currentCount--;
			} else {
				lastNode = node;
			}
			node = node.next;
		}
	}
	
	public void removeAtIndex(int index) {
		if (header == null) return; // error: out of bounces
		
		LYNode lastNode = null;
		LYNode node = header; 
		
		for (int i = 0; i < index; i++) {
			if (node != null) {
				lastNode = node;
				node = node.next;
			} else {
				return; // error: out of bounces
			}
		}
		
		if (index == 0) {
			header = node.next;
		} else {
			lastNode.next = node.next;
		}
		currentCount--;
	}
	
	public Object get(int index) {
		if (header == null) return null; // error: out of bounces
		LYNode node = header;
		for (int i = 0; i < index; i++) {
			node = node.next;
			if (node == null) {
				return null;
			}
		}
		return node.data;
	}
	
	public int size() {
		return currentCount;
	}
	
	private static class LYNode {
		Object data;
		LYNode next;
		
	}
}
