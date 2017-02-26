package com.coding.basic;

public class LinkedList implements List {
	
	private Node head;
	
	public void add(Object o){
		if(head==null){					//如果head为空
			head=new Node();			//新建一个节点
			head.data=o;				//赋予数据
			head.next=null;				//下一个节点为空
		}
		else{
			Node node=head.next;		
			Node temp = null;
				while(node!=null){
					temp=node.next;
					if(temp==null){
						temp=node;
						node=temp.next;
					}
					else{
						node=temp;
					}
				}
				if(node==null&&temp==null){
					node=new Node();
					node.data=o;
					node.next=null;
					head.next=node;
				}
				else{
					node=new Node();
					node.data=o;
					node.next=null;
					temp.next=node;
				}
		}
	}
	public void add(int index , Object o){
		int size=size();
		if(index<size&&index>0&&size>0){
			Node node=(Node) getNode(index-1);
			Node newNode=new Node();
			newNode.data=o;
			newNode.next=(Node) getNode(index);
			node.next=newNode;
		}
		else if(index==size&&size>0){
			Node node=(Node) getNode(size-1);
			Node newNode=new Node();
			newNode.data=o;
			newNode.next=null;
			node.next=newNode;
		}
		else if(index==0&&size!=0){
			Node temp=new Node();
			temp.next=head.next;
			temp.data=head.data;
			head.data=o;
			//head.next=temp;
			head.next=temp;
		}
		else if(index==0&&size==0){
			head=new Node();
			head.data=o;
			head.next=null;
		}
		else{
			System.out.println("下标超出范围");
		}
	}
	public Object get(int index){
		if(index==0){
			return head.data;
		}
		else{
			int i=1;
			Node node=head.next;//1 2 3 4 5
			while(node!=null){
				if(i==index){
					return node.data;
				}
				node=node.next;
				i++;
			}
		}
		return null;
	}
	public Object getNode(int index){
		if(index==0){
			return head;
		}
		else{
			int i=1;
			Node node=head.next;//1 2 3 4 5
			while(node!=null){
				if(i==index){
					return node;
				}
				node=node.next;
				i++;
			}
		}
		return null;
	}
	public Object remove(int index){
		if(index==0){
			Node node=head.next;
			Node temp=head;
			head=node;
			return temp.data;
		}
		else{
			Node temp=(Node) getNode(index);
			Node node=(Node) getNode(index-1);
			node.next=(Node) getNode(index+1);
			return temp.data;
		}
		
	}
	
	public int size(){
		if(head==null){
			return 0;
		}
		else{
			Node node=head.next;
			if(node==null){
				return 1;
			}
			else{
				int i=1;
				while(node!=null){
					node=node.next;
					i++;
				}
				return i;
			}
		}
	}
	
	public void addFirst(Object o){
		add(0,o);
	}
	public void addLast(Object o){
		add(size(),o);
	}
	public Object removeFirst(){
		Object temp = null;
		if(size()>0){
			temp=remove(0);
		}
		
		return temp;
	}
	public Object removeLast(){
		Object temp = null;
		if(size()>0){
			temp=remove(size()-1);
		}
		return temp;
	}
	public Iterator iterator(){
		return new Ir();
	}
	
	
	private static  class Node{
		Object data;
		Node next;
		
	}
	private class Ir implements Iterator{
		private int cursor;
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return cursor!=size();
		}

		@Override
		public Object next() {
			// TODO Auto-generated method stub
			int i=cursor;
			if(cursor<size()){
				cursor=i+1;
				return get(i);
			}
			return null;
		}
		
	}
		
}
