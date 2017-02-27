package com.coding.basic;

public class LinkedList implements List {
	
	private Node head;
    private Node tail;
    private Node last_but_one;
	private int size = 0;

    public LinkedList() {
        tail = new Node();
        tail.data = null;
        tail.next = null;

        head = new Node();
        head.next = null;
    }

    private void add_elementbyindex(int index , Object o) {
        Node new_node = new Node();
        if (index == 0) {
            if (size > 0) {
                new_node.data = head.data;
            } else {
//                System.out.println("first new node is null");
                new_node.data = null;
                head.next = null;
            }
            head.data = o;
        } else {
            new_node.data = o;
        }

        int count = 0;
        Node k = head;
        while (count < index - 1) {
//            System.out.printf("add count:%s, k is:%s \n",count,k.data);
            k = k.next;
            ++count;
        }
        new_node.next = k.next;
        k.next = new_node;
        ++size;
    }

    private Object remove_elementbyindex(int index) {
        int count = 0;
        Object remove_ob = null;
        Node k = head;
        while (count < index - 1) {
//            System.out.printf("remove count:%s, k is:%s \n",count,k.data);
            k = k.next;
            ++count;
        }

        if (index == 0) {
            remove_ob = head.data;
            head.data = head.next.data;
            if (size == 1) {
                head.data = null;
            }
            head.next = head.next.next;
        } else if ((index - 1) == size) {
//            System.out.printf("index:%s == size, count:%s, k is:%s \n",index,count,k.data);
            remove_ob = k.data;
            k.next = null;
        } else {
            if (size == 1) {
                k.data = null;
            }
            remove_ob = k.next.data;
            k.next = k.next.next;
        }
        --size;
        return remove_ob;
    }

	public void add(Object o){
        add_elementbyindex(size(),o);
	}

	public void add(int index , Object o){
	    // if index larger than size , then we set index equal to size;
	    if (index > size) {
            throw new IndexOutOfBoundsException("index out of bounds");
        }
        add_elementbyindex(index,o);

	}
	public Object get(int index){
	    if (index > size) {
            throw new IndexOutOfBoundsException("get index out of bounds");
        }
	    Node k = head;
	    int count = 0;
	    while (count < index) {
//            System.out.printf("get count:%s, k is:%s \n",count,k.data);
            k = k.next;
            ++count;
        }
//        System.out.printf("get count:%s, k is:%s \n",count,k.data);
		return k.data;
	}
	public Object remove(int index){
	    if (index < 0 || index > size || size == 0) {
            return null;
        }
	    return remove_elementbyindex(index);
	}
	
	public int size(){
		return size;
	}
	
	public void addFirst(Object o){
	    add_elementbyindex(0,o);
	}
	public void addLast(Object o){
        add_elementbyindex(size,o);
	}
	public Object removeFirst(){
        if (size == 0) {
            return null;
        }
		return remove_elementbyindex(0);
	}
	public Object removeLast(){
	    if (size == 0) {
            return null;
        }
		return remove_elementbyindex(size);
	}
	public Iterator iterator(){
	    Iterator_ip iter = new Iterator_ip(head);
		return iter;
	}
	
	private static  class Node{
		Object data;
		Node next;
	}

	private class Iterator_ip implements Iterator {
        private int index = 0;
        private Node node_iter;

        public Iterator_ip(Node node_iter) {
            this.node_iter = node_iter;
        }

        @Override
        public boolean hasNext() {
            return node_iter.next != null;
        }

        @Override
        public Object next() {
            if (index > size) {
//                throw new IndexOutOfBoundsException("iterator next out of bounds");
                return null;
            }
            Object now_data = node_iter.data;
            node_iter = node_iter.next;
            ++index;
            return now_data;
        }
    }
}
