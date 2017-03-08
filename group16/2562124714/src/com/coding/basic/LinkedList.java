package com.coding.basic;

import com.sun.org.apache.bcel.internal.generic.NEW;

import java.awt.*;

public class LinkedList implements List {
	public LinkedList()
	{
		head = null;
		this.Size = 0;
	}
	
	private Node head;
	private int Size;
	
	public void add(Object o){
		Node NewNode = new Node(o);

		if (this.head == null)
		{
			head = NewNode;
		}
		else
		{
			Node node;
			for (node = head; node.next != null; node = node.next)
			{
			}
			node.next = NewNode;
		}
		this.Size++;
		
	}
	//index为位置1，2，3，4，5，6，7
	public void add(int index , Object o){
		Node NewNode = new Node(o);

		if (1 == index)
		{
			NewNode.next = head;
			head = NewNode;
		}
		else {
			Node node;
			int i = 0;
			for (i = 1, node = head; i < index - 1; i++, node = node.next) {
			}
			NewNode.next = node.next;
			node.next = NewNode;
		}
		this.Size++;
		
	}
	public Object get(int index){
		Node node;
		int i = 0;

		for (i = 1, node = head; i < index ; i++, node = node.next) {
		}

		return node.data;
//		return null;
	}
	public Object remove(int index){
		Node node;
		int i = 0;

		if (1 == index)
		{
			if (head.next == null)
			{
				Object DelData = head.data;
				head = null;
				this.Size--;
				return DelData;
			}
			else
			{
				Node DelNode = head;
				head = DelNode.next;
				DelNode.next = null;
				this.Size--;
				return	 DelNode.data;

			}
		}
		else {

			for (i = 1, node = head; i < index - 1; i++, node = node.next) {
			}
			Node DelNode = node.next;
			node.next = DelNode.next;
			DelNode.next = null;
			this.Size--;
			return DelNode.data;
		}
	}
	
	public int size(){

		return this.Size;
	}
	
	public void addFirst(Object o){
		Node NewNode = new Node(o);

		if (null == this.head)
		{
			NewNode.next = null;
			head = NewNode;
		}
		else
		{
			NewNode.next = head;
			head = NewNode;
		}

		this.Size++;
		
	}
	public void addLast(Object o){
		Node NewNode = new Node(o);

		if (this.Size == 0)
		{
			NewNode.next = null;
			head = NewNode;
		}
		else
		{
//			int i = 0;
			Node node;
			for (node = head; node.next != null; node = node.next) {

			}
			node.next = NewNode;
		}

		this.Size++;
		
	}
	public Object removeFirst(){
		Node DelFirst;

		if (1 == this.Size)
		{
			DelFirst = this.head;
			DelFirst.next = null;
			head = null;
		}
		else
		{
			DelFirst = this.head;
			head = head.next;
			DelFirst.next = null;
		}
		this.Size--;

		return DelFirst.data;
	}
	public Object removeLast(){
		Node DelLast;

		if (1 == this.Size)
		{
			DelLast = head;
			DelLast.next = null;
			head = null;
		}
		else
		{
			Node node;
			for (node = head; node.next.next != null; node = node.next) {

			}
			DelLast = node.next;
			node.next = null;
		}
		this.Size--;

		return DelLast.data;
	}
	public Iterator iterator(){
		return null;
	}
	
	
	private static  class Node{
		Object data;
		Node next;

		public  Node(Object o)
		{
			this.data = o;
			this.next = null;
		}
		
	}
}
