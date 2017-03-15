package com.louisly.java;

import java.nio.file.NotDirectoryException;
import java.util.LinkedList;

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
					// �Ƴ���һ��Ԫ��
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
	
	// 第三周作业
	public void reverse() {
		if (header == null || header == lastNode) {
			return;
		}
		lastNode = header;
		LYNode pre = null;
		LYNode node = header;
		while (node != null) {
			LYNode next = node.next;
			node.next = pre;
			pre = node;
			node = next;
		}
		header = pre;
	}
	
	public void removeFirstHalf() {
		int size = this.size()/2;
		if (size == 0) {
			return;
		}
		for (int i = 0; i < size; i++) {
			header.data = null;
			header = header.next;
			currentCount--;
		}
	}
	
	public void remove(int index, int length) {
		if (index > currentCount-1 || (length + index) > currentCount) {
			throw new IndexOutOfBoundsException();
		}
		
		LYNode preNode = null;
		LYNode node = header;
		for (int i = 0; i < index; i++) {
			preNode = node;
			node = node.next;
		}
	
		for (int i = 0; i < length; i++) {
			node.data = null;
			node = node.next;
			currentCount--;
		}
		
		if (preNode == null) {
			header = node;
		} else {
			preNode.next = node;
		}
		
		if (length + index == currentCount) {
			lastNode = node;
		}
	}
	
	public int[] getElements(LYArrayLink list) {
		int length = list.size();
		int[] result = new int[length];
		for (int i = 0; i < result.length; i++) {
			int index = ((Integer)list.get(i)).intValue();
			Integer value = (Integer)this.get(index); 
			result[i] = value.intValue();
		}
		return result;
	}
	
	public void subtract(LYArrayLink list) {
		int listIndex = 0;
		LYNode node = header;
		LYArrayLink indexLink = new LYArrayLink();
		int thisIndex = 0;
		while (node != null) {
			
			int thisValue = ((Integer)node.data).intValue();
			
			if (listIndex > list.size() - 1) {
				break;
			} else {
				int listValue = ((Integer)list.get(listIndex)).intValue();
				listIndex++;
				
				if (thisValue == listValue) {
					indexLink.addObject(new Integer(thisIndex));
				}
			}
			
			node = node.next;
			thisIndex++;
		}
		
		for (int i = indexLink.size() - 1; i >= 0 ; i--) {
			this.removeAtIndex(i);
		}
	}
	
	// 大于小于这个之间的值删除
	public void removeRange(int min, int max) {
		
	}
	
	// 已知值递增。删除相同的元素
	public void removeDuplicateValues() {
		
	}
	
	// 都递增  返回交集的链表
	public LYArrayLink interSection(LYArrayLink list) {
		return null;
	}
	
}
