package com.easy.util.mylinkedlist;

import java.util.NoSuchElementException;

public class LinkedList  {
	
	private Node first;
	private Node last;
	int size=0;
	
	//region 构造函数
	public LinkedList(){
		
	}
	//endregion
	
	//region add方法
	public void addFirst(Object o){
		linkFirst(o);
	}
	
	public void addLast(Object o){
		linkLast(o);
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
	//endregion

	//region get 方法
	public Object get(int index){
		return node(index).item;
	}
	//endregion

	//region remove 方法
	public boolean remove(Object o){
		Node temp=first;
		if(o==null){
			for(int i=0;i<size;i++){
				if(temp.item==null){
					unlink(i);
					return true;
				}
				temp=temp.next;
			}
		}else{
			for(int i=0;i<size;i++){
				if (temp.item.equals(o)) {
					unlink(i);
					return true;
				}
				temp=temp.next;
			}
		}
		return false;
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
			size--;
			return temp.item;
		}
		return unlinkLast(temp);
	}
	//endregion

	//region size 方法
	public int size(){
		return this.size;
	}
	//endregion

	//region toString方法
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
	//endregion
	
	//region 私有成员
	private void linkBefore(int index,Object o){
		Node pred=node(index-1);
		Node newNode=new Node(o, node(index));
		pred.next=newNode;
		size++;
	}
	
	private void unlink(int index){
		Node before =node(index-1);
		Node target = before.next;
		Node after = target.next;
		before.next=after;
		size--;
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
	//endregion
}
