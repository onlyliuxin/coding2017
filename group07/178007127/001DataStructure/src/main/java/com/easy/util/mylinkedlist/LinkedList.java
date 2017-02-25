package com.easy.util.mylinkedlist;

import java.util.NoSuchElementException;

public class LinkedList  {
	
	private Node first;
	private Node last;
	int size=0;
	
	public LinkedList(){
		
	}
	
	public void add(Object o){
		linkLast(o);
	}
	public void add(int index , Object o){
		checkPositionIndex(index);
		
		if(index==size){
			linkLast(o);
		}else if(index==0){
			linkFirst(o);
		}else {
			linkBefore(index, o);
		}
	}
	
	
	private void linkBefore(int index,Object o){
		Node pred=node(index-1);
		Node newNode=new Node(o, node(index));
		pred.next=newNode;
		size++;
	}
	
	public Object get(int index){
		return node(index).item;
	}
	public boolean remove(Object o){
		Node temp=first;
		if(o==null){
			for(int i=0;i<size;i++){
				if(temp.item==null){
					unlink(i);
					return true;
				}
			}
		}else{
			for(int i=0;i<size;i++){
				if (temp.item.equals(o)) {
					unlink(i);
					return true;
				}
			}
		}
		return false;
	}
	
	private void unlink(int index){
		Node before =node(index-1);
		Node target = before.next;
		Node after = target.next;
		before.next=after;
		size--;
	}
	
	
	public int size(){
		return this.size;
	}
	
	public void addFirst(Object o){
		linkFirst(o);
	}
	public void addLast(Object o){
		linkLast(o);
	}
	public Object removeFirst(){
		Node temp = first;
		if(temp==null){
			throw new NoSuchElementException();
		}
		return unlinkFirst(temp);
	}
	public Object removeLast(){
		Node temp =last;
		if(last==null){
			throw new NoSuchElementException();
		}
		if(size==1){
			first=last=null;
			return temp;
		}
		return unlinkLast(temp);
	}
	
	
	private static  class Node{
		Object item;
		Node next;
		public Node(Object element,Node next) {
			this.item=element;
			this.next=next;
		}
	}
	
	
	private void linkLast(Object o){
		Node temp=last;
		Node newNode=new Node(o, null);
		last=newNode;
		if(temp==null){
			first=newNode;
		}else{
			temp.next=newNode;
		}
		size++;
	}
	
	private void linkFirst(Object o){
		Node temp=first;
		Node newNode=new Node(o, temp);
		first=newNode;
		if(temp==null){
			last=newNode;
		}else{
			first.next=temp;
		}
		size++;
	}
	
	private Object unlinkFirst(Node temp){
		Object element = temp.item;
		Node next = temp.next;
		first = next;
		if(next==null){
			last=null;
		}
		size--;
		return element;
	}
	
	private Object unlinkLast(Node temp){
		Node xNode = first;
		for(int i=0;i<size-2;i++){
			xNode = xNode.next;
		}
		last=xNode;
		last.next=null;
		size--;
		return temp.item;
	}
	private Node node(int index){
		if(index<size){
			Node xNode =first;
			for(int i=0;i<index;i++){
				xNode=xNode.next;
			}
			return xNode;
		}
		return null;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		Node xNode = first;
		for(int i=0;i<size;i++){
			Object object = xNode.item;
			sb.append(object+",");
			xNode=xNode.next;
		}
		String temp = sb.toString();
		temp = temp.substring(0, temp.length() - 1);
		return "[" + temp + "]";
	}
	
	private void checkPositionIndex(int index){
		if(!isPositionIndex(index)){
			throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
		}
	}
	
	private boolean isPositionIndex(int index){
		return index>0&&index<=size;
	}
	
	private String outOfBoundsMsg(int index) {
        return "Index: "+index+", Size: "+size;
    }

}
