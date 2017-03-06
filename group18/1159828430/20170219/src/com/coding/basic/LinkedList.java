package com.coding.basic;

import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

/**
 * @author Hipple
 * @Time：2017年2月21日 下午8:00:21
 * @version 1.0
 */
public class LinkedList implements List {
    
    //头结点
    private Node first;
    
    //尾结点
    private Node last;
    
    //元素数量
    private int size = 0;
    
    //无参构造器
    public LinkedList(){
    }
    
    public boolean add(Object o){
        linkLast(o);
        return true;
    }
    
    public void add(int index , Object o){
    	checkPositionIndex(index);
    	if (index == size) {
			linkLast(o);
		} else {
			linkBefore(o, node(index));
		}
    }
    
    public Object remove(int index){
    	checkElementIndex(index);
        return unlink(node(index));
    }
    
    public Object get(int index){
    	checkElementIndex(index);
    	return node(index).data;
    }
    
    public void addFirst(Object o){
    	linkFirst(o);
    }
    
    public void addLast(Object o){
        linkLast(o);
    }
    
    public Object removeFirst(){
    	final Node f = first;
    	if (f == null) {
    		throw new NoSuchElementException();
    	}
        return unlinkFirst(f);
    }
    
    public Object removeLast(){
    	final Node l = last;
    	if (l == null) {
    		throw new NoSuchElementException();
    	}
        return unlinkLast(l);
    }
    
    public int size(){
        return size;
    }
    
    //检查是否为空
    public boolean isEmpty(){
    	return size == 0;
    }
    
    //获取头节点
    public Object getFirst() {
        final Node f = first;
        if (f == null)
            throw new NoSuchElementException();
        return f.data;
    }
    
    public Iterator iterator(){
        return new LinkedListIterator();
    }
    
    //头部增加节点
    private void linkFirst(Object data){
        final Node f = first;//f存储老的头部节点待用
        final Node newNode = new Node(null, data, first);//后项指针指向first，前项指针null
        first = newNode;//将新节点变为头部节点
        if (f == null) {//头节点为null则代表链表为空，那么新节点也是既是头结点也是尾结点
            last = newNode;
        } else {//老的头部节点前项指针指向新节点
            f.previous = newNode;
        }
        size++;
    }
    
    //尾部增加节点
    private void linkLast(Object data){
        final Node l = last;//l存储老的尾部节点待用
        final Node newNode = new Node(last, data, null);//前项指针指向last，后项指针null
        last = newNode;//将新节点变为尾部节点
        if (l == null) {//尾节点为null则代表链表为空，那么新节点也是既是头结点也是尾结点
            first = newNode;
        } else {//老的尾部节点后项指针指向新节点
            l.next = newNode;
        }
        size++;
    }
    
    //指定index插入节点
    private void linkBefore(Object o, Node oldNode){
    	final Node pred = oldNode.previous;
    	final Node newNode = new Node(pred, o, oldNode);
    	oldNode.previous = newNode;//旧节点前项指针指向新节点
    	if (pred == null) {//pred为null代表oldNode为头节点
			first = newNode;
		} else {
			pred.next = newNode;
		}
    	size++;
    	
    }
    
    //删除头部节点并返回节点值
    private Object unlinkFirst(Node f){
    	final Object element = f.data;//保存头节点的值
    	final Node next = f.next;
    	f.data = null;//GC自动回收
    	f.next = null;
    	first = next;//将头节点的下一节点变为头节点
    	if (next == null) {//如果next为空，则代表f同时为尾节点，此时整个链表为空
    		last = null;
    	} else {
    		next.previous = null;
    	}
    	size--;
    	return element;
    }
    
    //删除尾部节点并返回该节点的值
    private Object unlinkLast(Node l){
    	final Object element = l.data;//保存尾节点的值
    	final Node prev = l.previous;
    	l.previous = null;
    	l.data = null;//GC自动回收
    	last = prev;//将尾节点的上一节点变为尾节点
    	if (prev == null) {//如果prev为空，则代表l同时为头节点，此时整个链表为空
    		first = null;
    	} else {
    		prev.next = null;
    	}
    	size--;
    	return element;
    }
    
    //删除指定节点
    private Object unlink(Node x){
    	final Object element = x.data;
    	final Node prev = x.previous;
    	final Node next = x.next;
    	if (prev == null) {//prev为空代表要删除的是头节点
    		unlinkFirst(x);
		} else {//prev后项指针指向next
			prev.next = next;
			x.previous = null;
		}
    	if (next == null) {//next为空代表要删除的是尾节点
    		unlinkLast(x);
		} else {//next前项指针指向prev
			next.previous = prev;
			x.next = null;
		}
    	x.data = null;
    	size--;
    	return element;
    }
    
    //查找结点
    private Node node(int index){
    	if (index < (size>>1)) {//判断循环方向
    		Node x = first;
    		for (int i = 0; i < index; i++) {
    			x = x.next;
    		}
    		return x;
    	} else {
    		Node x = last;
    		for (int i = size - 1; i > index; i--) {
    			x = x.previous;
    		}
    		return x;
    	}
    }
    
    //检查下标是否合法
    private void checkElementIndex(int index){
    	if (!isElementIndex(index)) {
    		throw new IndexOutOfBoundsException("Index: "+index+", Size: "+size);
    	}
    }
    
    private void checkPositionIndex(int index){
    	if (!isPositionIndex(index)) {
    		throw new IndexOutOfBoundsException("Index: "+index+", Size: "+size);
    	}
    }
    
    //检查该参数是否为现有元素的索引。
    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    //检查参数是否是迭代器或添加操作的有效位置的索引
    private boolean isPositionIndex(int index) {
        return index >= 0 && index <= size;
    }
    
    //迭代器
    private class LinkedListIterator implements Iterator{
    	private Node lastReturned = null;
        private Node next;
        private int nextIndex;

        public boolean hasNext() {
            return nextIndex < size;
        }

        public Object next() {
            if (!hasNext())
                throw new NoSuchElementException();

            lastReturned = next;
            next = next.next;
            nextIndex++;
            return lastReturned.data;
        }
        
        public void remove() {
            if (lastReturned == null)
                throw new IllegalStateException();

            Node lastNext = lastReturned.next;
            unlink(lastReturned);
            if (next == lastReturned)
                next = lastNext;
            else
                nextIndex--;
            lastReturned = null;
        }
    }
    
    //节点对象
    private static class Node{
        Object data;
        Node next;
        Node previous;
        Node(Node previous, Object data, Node next) {
             this.data = data;
             this.next = next;
             this.previous = previous;
        }
    }
}