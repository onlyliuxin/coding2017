package com.coding.basic;

import java.util.Iterator;

public class LinkedList implements List {
	private int size = 0;
	private Node head = null;
	
	public void add(Object o){
		if(head == null){
			head = new Node();
			head.data = o;
			head.next = null;
		}else{
			Node tmpLast = head;
			//last node
			while(tmpLast.next != null){
				tmpLast = tmpLast.next;
			}
			Node tmp = new Node();
			tmp.data = o;
			tmp.next = null;
			tmpLast.next = tmp;
		}
		size++;
	}
	
	public void add(int index , Object o){
		if(head == null){
			if(index > 0){
				System.out.println("index wrong: now head is null index > 0");
			}else if(index == 0){
				head = new Node();
				head.data = o;
				head.next = null;
				size++;
			}
		}else{
			if(index > 0){
				System.out.println("here1");
				Node tmp = head;
				int cnt = 0;
				//to(Index-1)Node
				while(cnt < index-1){
					tmp = tmp.next;
					cnt++;
				}
				Node indexTmp = new Node();
				indexTmp.data = o;
				indexTmp.next = tmp.next;
				tmp.next = indexTmp;
				size++;
			}else if(index == 0){
				Node indexTmp = new Node();
				indexTmp.data = o;
				indexTmp.next = head;
				head = indexTmp;
			}

		}
		
	}
	public Object get(int index){
		if(index<0 || index >=size){
			System.out.println("index out boundry");
		}
		Node tmp = head;
		int cnt = 0;
		//toIndexNode
		while(cnt < index){
			tmp = tmp.next;
			cnt++;
		}
		return tmp.data;
	}
	
	public Object remove(int index){
		if(index+1 <= size){				
			if(index == 0){
				Node tmp;
				tmp = head;
				head = head.next;
				size--;
				return tmp.data;
			}else if(index > 0){
				int cnt = 0;
				Node tmp = head;
				while(cnt < index-1)
				{
					tmp = tmp.next;
					cnt++;
				}
				Node indexMinusOnePos = tmp;
				Node tmpIndexNode = indexMinusOnePos.next;
				indexMinusOnePos.next = indexMinusOnePos.next.next;
				size--;
				return tmpIndexNode.data;
			}
		}else{
			System.out.println("index wrong");
			return null;
		}
		return null;
	}
	
	public int size(){
		return size;
	}
	
	public void addFirst(Object o){
		add(0, o);	
	}
	public void addLast(Object o){
		add(size-1, o);
		
	}
	public Object removeFirst(){
		return remove(0);
	}
	public Object removeLast(){
		return remove(size-1);
	}
	public Iterator iterator(){
		return new linkedListIterator();
	}
	
	private class linkedListIterator implements Iterator{
		
	}
	private static class Node{
		Object data;
		Node next;
		
	}
	
	public static void main(String[] args){
		/*
		System.out.println("=======1========");
		LinkedList linkedList = new LinkedList();
		linkedList.add("123");
		System.out.println(linkedList.get(0));
		System.out.println(linkedList.size());
		
		System.out.println("=======2========");
		for (int i = 0; i < 10; i++) {
			String tmp = i+"";
			linkedList.add(tmp);
		}
		for (int i = 0; i < linkedList.size(); i++) {
			System.out.println("" + i + ":" + linkedList.get(i));
		}
		
		System.out.println("=======3========");
		linkedList.add(0, "999999999999999999");
		linkedList.add(1, "grewtrewtew");
		for (int i = 0; i < linkedList.size(); i++) {
			System.out.println("index-" + i + ":" + linkedList.get(i));
		}
		*/
		
		System.out.println("=======001========");
		LinkedList linkedList = new LinkedList();
		linkedList.add("123");
		System.out.println("after remove=============");
		System.out.println("remove what:" + linkedList.remove(0));
		System.out.println("size:" +linkedList.size());
		
		for (int i = 0; i < 5; i++) {
			String tmp = i+"--";
			linkedList.add(tmp);		
		}
		for (int i = 0; i < linkedList.size(); i++) {
			System.out.println(linkedList.get(i));	
		}
		
		linkedList.remove(1);
		System.out.println("after remove index 1");
		for (int i = 0; i < linkedList.size(); i++) {
			System.out.println(linkedList.get(i));	
		}
		
		linkedList.remove(3);
		System.out.println("after remove index 3");
		for (int i = 0; i < linkedList.size(); i++) {
			System.out.println(linkedList.get(i));	
		}
	}
	
	
	
}
