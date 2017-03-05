package com.coding.basic;
import java.util.NoSuchElementException;

public class LinkedList implements List {
	
	private Node head;
	private int size=0;
/**
 * 增加节点	
 */
	public void add(Object o){
		if(size==0){
			head=new Node();
			head.data=o;
			head.next=null;
			size++;
			}
		else{
			addLast(o);
			}
			
	}
/**
 * 在index(0~size)处添加元素
 */
	public void add(int index , Object o){
		
		if(index<0||index>size){
			System.out.println("index超出范围"+index);
			return;
			}
		if(index==0)addFirst( o);
		else if(index==size)addLast(o);
		else{
			Node ptr=head;
			for(int i=1;i<index;i++){
				ptr=ptr.next;
			}
			Node node=new Node();
			node.data=o;
			node.next=ptr.next;
			ptr.next=node;		
		}
		size++;
	}
/**
 * 获得index(0~size-1)的元素
 */
	public Object get(int index){
		if(index<0||index>=size)return null;
		else{
			Node ptr=head;
			for(int i=0;i<index;i++){
				ptr=head.next;
			}
			return ptr;
		}
	}
/**
 * 移除index(0~size-1)的元素
 */
	public Object remove(int index){
		if(index<0||index>=size)return null;
		else if(index==0)return removeFirst();
		else if(index==size-1)return removeLast();
		else{
			Node ptr=head;
			for(int i=1;i<index;i++){
				ptr=ptr.next;
				}
			Node node=ptr.next;
			ptr.next=node.next;
			size--;	
			return node.data;
			}	
	}
	
	public int size(){
		return size;
	}
	
	public void addFirst(Object o){
		Node ptr=head;
		head=new Node();
		head.data=o;
		head.next=ptr;	
		size++;
	}
	public void addLast(Object o){
		Node ptr=head;
		for(int i=1;i<size;i++){
			ptr=ptr.next;
		}
		Node node=new Node();
		node.data=o;
		node.next=null;	
		ptr.next=node;
		size++;
		
	}
	public Object removeFirst(){
		Node ptr=head.next;
		head.next=null;
		Object o=head.data;
		head=ptr;
		size--;
		return o;
	}
	public Object removeLast(){
		Node ptr=head;
		for(int i=1;i<size-1;i++){
			ptr=ptr.next;
		}
		Node node=ptr.next;
		ptr.next=null;
		size--;
		return node.data;
	}
	public Iterator iterator(){
		return new ListItr();
	}
    private class ListItr implements Iterator {
    
        private Node next=null;
        private int nextIndex;

        ListItr() {
            next = head;
            nextIndex = 0;
        }

        public boolean hasNext() {
            return nextIndex < size;
        }

        public Object next() {
            if (!hasNext())
                throw new NoSuchElementException();

            Node lastReturned = next;
            next = next.next;
            nextIndex++;
            return lastReturned.data;
        }
    }
	
	private static  class Node{
		Object data;
		Node next;	
	}
	public static void main(String args[]){
	LinkedList list=new LinkedList();
	list.add(0);
	list.add(1);
	list.add(2);
	list.add(3);
	list.add(4);
	list.add(5);
	list.add(6);
//	list.add(7,9);
//	System.out.println(list.remove(5));
//	System.out.println(list.remove(5));
//	System.out.println(list.remove(6));
//	System.out.println(list.size());
//	Node ptr=list.head;
//	do{
//		System.out.print(ptr.data);
//		ptr=ptr.next;
//		}while(ptr!=null);
	Iterator itr=list.iterator();
	while (itr.hasNext()) {
		System.out.println(itr.next());
		
	}
	}
}
