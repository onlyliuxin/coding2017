package com.coding.basic;

public class LinkedList implements List {
	
	private Node head;
	private int size;
    //链表的头元素  
    private Node first;  
    //链表的尾元素  
    private Node end; 
    
    private int modcount;
    
	public void add(Object o){
		add(size(), o);  
	}
	public void add(int index , Object o){
		addBefore(getNode(index),0);  
	}
	public Object get(int index){
		return getNode(index).data;  
	}

	public Object remove(int index) {
		Node n = getNode(index);
		n.prev.next = n.next;
		n.next.prev = n.prev;
		size--;
		modcount++;
		return n.data;
	}
	
	public int size(){
		return this.size;
	}
	
	public void addFirst(Object o){
		Node node = getNode(0);
		node.data = o;
		node.next = head;
		head = node;
		size++;
		modcount++;
	}
	public void addLast(Object o){
		add(size(), o);  
	}
	public Object removeFirst(){
		return remove(head.next);
	}
	public Object removeLast(){
		return remove(head.prev);
	}
	public Iterator iterator(){
		return  new LinkedListIterator();
	}
	
	//执行添加  
    private void addBefore (Node p, Object o) {  
        Node newNode = new Node(o, p.prev, p);  
        newNode.prev.next = newNode;  
        p.prev = newNode;  
        modcount++;
    }  
    
    //查找节点  
    private Node getNode(int idx) {  
        Node p;  
          
        if(idx <0 || idx > size()) {  
            throw new IndexOutOfBoundsException();  
        }  
          
        if(idx < size()/2) {  
            p = first.next;  
            for (int i = 0;i < idx;i++) {  
                p = p.next;  
            }  
        }else {  
            p = end;  
            for (int i = size();i>idx;i--) {  
                p = p.prev;  
            }  
        }  
        return p;  
    }  
	
    public boolean remove(Object o) {
        if (o==null) {
            for (Node e = head.next; e != head; e = e.next) {
                if (e.data==null) {
                    remove(e);
                    return true;
                }
            }
        } else {
        	 for (Node e = head.next; e != head; e = e.next) {
                if (o.equals(e.data)) {
                    remove(e);
                    return true;
                }
            }
        }
        return false;
    }
    
    
	private static class Node {
		Object data;
		Node next;
		Node prev;

		public Node(Object data, Node next, Node prev) {
			this.data = data;
			this.prev = prev;
			this.next = next;
		}
	}
	
	private class LinkedListIterator implements Iterator {

		private Node current = first.next;
		private int expectedModCount = modcount;
		private boolean okToRemove = false;

		@Override
		public boolean hasNext() {
			return current != end;
		}

		@Override
		public Object next() {
			Object nextData = current.data;
			current = current.next;
			okToRemove = true;
			return nextData;
		}

	}
}
